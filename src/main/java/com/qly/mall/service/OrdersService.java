package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.OrdersMapper;
import com.qly.mall.mapper.SubOrdersMapper;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.Orders;
import com.qly.mall.model.SubOrders;
import com.qly.mall.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

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

    public Integer CreateOrder(Orders orders, SubOrders[] subOrders){
        CheckParam(orders.getUser_id());
        Integer order_id = getOrderId();
        orders.setOrder_id(order_id);

        if(ordersMapper.CreateOrder(orders) == 1){
            logger.info(order_id + "创建订单成功");
            for(int i = 0;i < subOrders.length; i++){
                Integer sub_order_id = getSubOrderId();
                subOrders[i].setSub_order_id(sub_order_id);
                subOrders[i].setOrder_id(order_id);
                if(subOrdersMapper.CreateSubOrder(subOrders[i]) == 1){
                    logger.info(subOrders[i].getSub_order_id() + "创建子订单成功");
                }else{
                    logger.info(subOrders[i].getSub_order_id() + "创建子订单失败");
                    throw new  ErrorException(ErrorNo.CREATE_SUBORDER_FAIL.code(), ErrorNo.CREATE_SUBORDER_FAIL.msg());
                }
            }
        }else{
            logger.info(order_id + "创建订单失败");
            throw new  ErrorException(ErrorNo.CREATE_ORDER_FAIL.code(), ErrorNo.CREATE_ORDER_FAIL.msg());
        }
        return order_id;
    }

    public void CheckParam(Integer user_id){
        if(user_id == null) {
            logger.error(user_id + "参数user_id错误");
            throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
        }
        if(userInfoMapper.FindUserByUserId(user_id) == null){
            logger.error(user_id + "用户不存在");
            throw new ErrorException(ErrorNo.USER_NOT_EXIST.code(), ErrorNo.USER_NOT_EXIST.msg());
        }
        // TODO:对子订单中商品进行（价格、状态、是否存在）
    }

    public Integer getOrderId(){
        Random random = new Random();
        Integer order_id = null;
        StringBuffer stringBuffer = null;
        while (stringBuffer == null) {
            stringBuffer = new StringBuffer();
            for (int i = 0; i < 8; i++) {
                int num = random.nextInt(10);
                if(i == 0){
                    while(num == 0)num = random.nextInt(10);
                }
                stringBuffer.append(num);
            }
            order_id = Integer.parseInt(new String(stringBuffer));
            if(ordersMapper.FindOrderByOrderId(order_id) == null)
                stringBuffer = null;
        }
        return order_id;
    }

    public Integer getSubOrderId(){
        Random random = new Random();
        Integer sub_order_id = null;
        StringBuffer stringBuffer = null;
        while (stringBuffer == null) {
            stringBuffer = new StringBuffer();
            for (int i = 0; i < 8; i++) {
                int num = random.nextInt(10);
                if(i == 0){
                    while(num == 0)num = random.nextInt(10);
                }
                stringBuffer.append(num);
            }
            sub_order_id = Integer.parseInt(new String(stringBuffer));
            if(subOrdersMapper.FindSubOrderBySubOrderId(sub_order_id) == null)
                stringBuffer = null;
        }
        return sub_order_id;
    }
}
