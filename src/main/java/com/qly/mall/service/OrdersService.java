package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.GoodsSkuMapper;
import com.qly.mall.mapper.OrdersMapper;
import com.qly.mall.mapper.SubOrdersMapper;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.GoodsSku;
import com.qly.mall.model.Orders;
import com.qly.mall.model.SubOrders;
import com.qly.mall.util.RandomUtil;
import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class OrdersService {
    static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

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

    public Integer CreateOrder(Orders orders, SubOrders[] subOrders){
        CheckParam(orders.getUserId(), orders.getOrderId(), subOrders);
        //TODO:幂等记账
        if(ordersMapper.FindOrderByOrderId(orders.getOrderId()) != null){
            return orders.getOrderId();
        }
        //TODO:redis计数器限流

        Integer order_id = randomUtil.getId("ordersMapper");
        orders.setUserId(order_id);

        if(ordersMapper.CreateOrder(orders) == 1){
            logger.info(order_id + "创建orderId成功");
            for(int i = 0;i < subOrders.length; i++){
                Integer sub_order_id = randomUtil.getId("subOrdersMapper");
                subOrders[i].setSubOrderId(sub_order_id);
                subOrders[i].setOrderId(order_id);
                if(subOrdersMapper.CreateSubOrder(subOrders[i]) == 1){
                    logger.info(subOrders[i].getSubOrderId() + "创建subOrderId成功");
                }else{
                    logger.info(subOrders[i].getSubOrderId() + "创建subOrderId失败");
                    throw new  ErrorException(ErrorNo.CREATE_SUBORDER_FAIL.code(), ErrorNo.CREATE_SUBORDER_FAIL.msg());
                }
            }
        }else{
            logger.info(order_id + "创建orderId失败");
            throw new  ErrorException(ErrorNo.CREATE_ORDER_FAIL.code(), ErrorNo.CREATE_ORDER_FAIL.msg());
        }
        return order_id;
    }

    public void CheckParam(Integer user_id, Integer orderId, SubOrders[] subOrders){
        if(user_id == null) {
            logger.error(user_id + "参数userId错误");
            throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
        }
        if(userInfoMapper.FindUserByUserId(user_id) == null){
            logger.error(user_id + "用户userId不存在");
            throw new ErrorException(ErrorNo.USER_NOT_EXIST.code(), ErrorNo.USER_NOT_EXIST.msg());
        }
        //对子订单中商品进行检查（价格、上架状态、是否存在、出售时间）
        for (SubOrders subOrder : subOrders) {
            GoodsSku skuGoods = goodsSkuMapper.FindSkuGoodsBySkuId(subOrder.getSkuId());
            if (skuGoods == null) {
                logger.error(subOrder.getSubOrderId() + "子订单中商品不存在" + subOrder.getSkuId());
                throw new ErrorException(ErrorNo.SKU_EXCEPTION.code(), ErrorNo.SKU_EXCEPTION.msg());
            }
            if (skuGoods.getSkuStatus() != GoodsSku.SkuStatus.ON_SHELF) {
                logger.error(subOrder.getSubOrderId() + "子订单中商品已下架" + subOrder.getSkuId());
                throw new ErrorException(ErrorNo.SKU_EXCEPTION.code(), ErrorNo.SKU_EXCEPTION.msg());
            }
            if (skuGoods.getSaleEnd() > Time.now() || skuGoods.getSaleStart() < Time.now()) {
                logger.error(subOrder.getSubOrderId() + "子订单中商品没有正在出售中" + subOrder.getSkuId());
                throw new ErrorException(ErrorNo.SKU_EXCEPTION.code(), ErrorNo.SKU_EXCEPTION.msg());
            }
            if (subOrder.getPayPrice() < 0) {
                logger.error(subOrder.getSubOrderId() + "子订单中用户支付价格<0");
                throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
            }
        }

    }
}
