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
    PayType payType;

    public static class OrderStatus{
        static public Integer SOFT_DELETE = 0;
        static public Integer NORMAL = 1;
        static public Integer EXCEPTION = 2;
    }

    public static class PayType{
        static public Integer SOFT_DELETE = 0;
        static public Integer NORMAL = 1;
        static public Integer EXCEPTION = 2;
    }
}