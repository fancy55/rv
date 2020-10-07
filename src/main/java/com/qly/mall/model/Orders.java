package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@ApiModel("订单")
public class Orders {
    Integer id;
    Integer userId;
    Integer orderId;
    Integer productNum;
    Integer totalPrice;
    Integer payPrice;
    String payInfo;
    Long createTime;
    Long updateTime;
    Long payTime;
    Long closeTime;
    Integer version;
    Integer orderStatus;
    Integer payType;

    public static class OrderStatus{
        static public Integer CREATED = 1; //已创建
        static public Integer PROCESSING = 2; //已确认，支付中
        static public Integer PAID = 3; //已支付
        static public Integer CANCELED = 4; //已取消
        static public Integer CLOSE = 5; //已关闭
        static public Integer REFUND_AUDIT = 6; //退款审核中
        static public Integer REFUND_PROCESS = 7; //退款中
        static public Integer REFUNDED = 8; //已退款
    }

    public static class PayType{
        static public Integer OA = 0; //虚拟支付
        static public Integer AliPay = 1; //支付宝
        static public Integer WX = 2; //微信
        static public Integer ZERO = 3;//0元单
    }
}