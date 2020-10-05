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
        static public Integer SOFT_DELETE = 0;
        static public Integer NORMAL = 1;
        static public Integer CREATING = 2; //创建中
//        static public Integer EXISTING = 3; //已创建
        static public Integer EXCEPTION = 4;
        static public Integer WAITING = 5; //待支付
        static public Integer PROCESSING = 6; //处理中
        static public Integer EXISTING = 7; //已支付
    }

    public static class PayType{
        static public Integer OA = 0; //虚拟支付
        static public Integer ZFB = 1; //支付宝
        static public Integer WX = 2; //微信
    }
}