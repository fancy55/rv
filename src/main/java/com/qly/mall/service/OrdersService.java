package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.*;
import com.qly.mall.model.*;
import com.qly.mall.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class OrdersService {
    static final Logger logger = LoggerFactory.getLogger(OrdersService.class);

    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    SubOrdersMapper subOrdersMapper;
    @Autowired
    RandomUtil randomUtil;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    InventoryMapper inventoryMapper;
    @Autowired
    RedisTemplate redisTemplate;

    public Integer CreateOrder(Orders orders, SubOrders[] subOrders, Integer userId){
        CheckParam(userId, orders, subOrders);
        //幂等记账
        if(ordersMapper.FindOrderByOrderId(orders.getOrderId()) != null){
            return orders.getOrderId();
        }
        //TODO:redis计数器限流

        Integer order_id = randomUtil.getId("ordersMapper");
        orders.setOrderId(order_id);
        orders.setStatus(Orders.OrderStatus.CREATED);
        if(orders.getPayPrice() == 0)orders.setPayType(Orders.PayType.ZERO);
        else orders.setPayType(Orders.PayType.OA); //OA支付
        if(ordersMapper.CreateOrder(orders) == 1){
            logger.info(order_id + "创建orderId成功");
            for (SubOrders subOrder : subOrders) {
                Integer sub_order_id = randomUtil.getId("subOrdersMapper");
                subOrder.setSubOrderId(sub_order_id);
                subOrder.setOrderId(order_id);
                subOrder.setStatus(Orders.OrderStatus.CREATED);
                if (subOrdersMapper.CreateSubOrder(subOrder) == 1) {
                    logger.info(subOrder.getSubOrderId() + "创建subOrderId成功");
                    //订单设置超时时间：30min
                    ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
                    operations.set("order"+order_id.toString(), orders.getUserId(), 30, TimeUnit.MINUTES);
                } else {
                    logger.info(subOrder.getSubOrderId() + "创建subOrderId失败");
                    throw new ErrorException(ErrorNo.CREATE_SUBORDER_FAIL.code(), ErrorNo.CREATE_SUBORDER_FAIL.msg());
                }
            }
        }else{
            logger.info(order_id + "创建orderId失败");
            throw new  ErrorException(ErrorNo.CREATE_ORDER_FAIL.code(), ErrorNo.CREATE_ORDER_FAIL.msg());
        }
        return order_id;
    }

    public void CheckParamUserId(Integer user_id){
        if(user_id == null) {
            logger.error(user_id + "参数userId错误");
            throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
        }
        if(userInfoMapper.FindUserByUserId(user_id) == null){
            logger.error(user_id + "用户userId不存在");
            throw new ErrorException(ErrorNo.USER_NOT_EXIST.code(), ErrorNo.USER_NOT_EXIST.msg());
        }
    }

    public void CheckParam(Integer user_id, Orders orders, SubOrders[] subOrders){
        CheckParamUserId(user_id);
        if(!orders.getUserId().equals(user_id)){
            logger.error(user_id + "参数userId和订单中的创建人userId不一样");
            throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
        }
        //对子订单中商品进行检查（价格、上架状态、是否存在、出售时间、库存）
        for (SubOrders subOrder : subOrders) {
            if (subOrder.getPayPrice() < 0) {
                logger.error(subOrder.getSubOrderId() + "子订单中用户支付价格<0");
                throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
            }
            GoodsSku skuGoods = goodsSkuMapper.FindSkuGoodsBySkuId(subOrder.getSkuId());
            Inventory inventory = inventoryMapper.FindInventoryBySkuId(subOrder.getSkuId());
            if (skuGoods == null) {
                logger.error(subOrder.getSubOrderId() + "子订单中商品不存在" + subOrder.getSkuId());
                throw new ErrorException(ErrorNo.SKU_EXCEPTION.code(), ErrorNo.SKU_EXCEPTION.msg());
            }
            if (!inventory.getStatus().equals(Inventory.inventoryStatus.ON_SHELF)) {
                logger.error(subOrder.getSubOrderId() + "子订单中商品已下架" + subOrder.getSkuId());
                throw new ErrorException(ErrorNo.SKU_EXCEPTION.code(), ErrorNo.SKU_EXCEPTION.msg());
            }
            if (skuGoods.getSaleEnd().getTime() > System.currentTimeMillis() || skuGoods.getSaleStart().getTime() < System.currentTimeMillis()) {
                logger.error(subOrder.getSubOrderId() + "子订单中商品没有正在出售中" + subOrder.getSkuId());
                throw new ErrorException(ErrorNo.SKU_EXCEPTION.code(), ErrorNo.SKU_EXCEPTION.msg());
            }
        }
    }

    public Integer CheckParamPhone(String phone){
        Integer userId1 = userInfoMapper.FindUserIdByPhone(phone);
        if(userId1 == null){
            logger.error(phone + "用户phone不存在");
            throw new ErrorException(ErrorNo.USER_NOT_EXIST.code(), ErrorNo.USER_NOT_EXIST.msg());
        }else return userId1;
    }

    public Integer OAPayOrder(Orders orders, Integer userId){
        CheckParam(userId, orders, null);
        orders.setStatus(Orders.OrderStatus.PAID);
        return ordersMapper.UpdateOrder(orders);
    }

    public Integer CancelOrder(Orders orders, Integer userId){
        CheckParam(userId, orders, null);
        orders.setStatus(Orders.OrderStatus.CANCELED);
        return ordersMapper.UpdateOrder(orders);
    }

    public Integer CloseOrder(Integer orderId){
        Orders orders = ordersMapper.FindOrdersByOrderId(orderId);
        orders.setStatus(Orders.OrderStatus.CLOSE);
        return ordersMapper.UpdateOrder(orders);
    }

    public Orders GetOrderByOrderId(Integer orderId, Integer userId){
        CheckParamUserId(userId);
        Orders orders = ordersMapper.FindOrdersByOrderId(orderId);
        SubOrders subOrders = subOrdersMapper.FindSubOrderByOrderId(orderId)[0];
        orders.setSpuId(subOrders.getSpuId());
        orders.setSpuName(subOrders.getSpuName());
        orders.setSkuId(subOrders.getSkuId());
        orders.setSkuName(subOrders.getSkuName());
        return orders;
    }

    public SubOrders GetSubOrderBySubOrderId(Integer subOrderId, Integer userId){
        CheckParamUserId(userId);
        return subOrdersMapper.FindSubOrdersBySubOrderId(subOrderId);
    }

    public SubOrders[] GetSubOrderByOrderId(Integer orderId, Integer userId){
        CheckParamUserId(userId);
        return subOrdersMapper.FindSubOrderByOrderId(orderId);
    }

    public SubOrders[] GetSubOrderByUserId(Integer userId1, Integer userId){
        CheckParamUserId(userId);
        return subOrdersMapper.FindSubOrderByUserId(userId1);
    }

    public Orders[] GetOrderByUserId(Integer userId1,Integer userId){
        CheckParamUserId(userId);
        return ordersMapper.FindOrderByUserId(userId1);
    }

    public SubOrders[] GetSubOrderByPhone(String phone, Integer userId){
        Integer userId1 = CheckParamPhone(phone);
        CheckParamUserId(userId);
        return subOrdersMapper.FindSubOrderByUserId(userId1);
    }

    public Orders[] GetOrderByPhone(String phone, Integer userId){
        Integer userId1 = CheckParamPhone(phone);
        CheckParamUserId(userId);
        return ordersMapper.FindOrderByUserId(userId1);
    }

    public Orders GetOrderBySubOrder(Integer subOrderId, Integer userId){
        CheckParamUserId(userId);
        SubOrders subOrders = subOrdersMapper.FindSubOrdersBySubOrderId(subOrderId);
        Orders orders = ordersMapper.FindOrdersByOrderId(subOrders.getOrderId());
        orders.setSpuId(subOrders.getSpuId());
        orders.setSpuName(subOrders.getSpuName());
        return orders;
    }

    public Orders[] GetOrdersByOffset(Integer offset, Integer user_id){
        CheckParamUserId(user_id);
        Orders[] orderses =  ordersMapper.GetOrdersByOffset(offset);
        for(Orders orders:orderses){
            SubOrders[] subOrders = subOrdersMapper.FindSubOrderByOrderId(orders.getOrderId());
            orders.setSpuName(subOrders[0].getSpuName());
        }
        return orderses;
    }
}
