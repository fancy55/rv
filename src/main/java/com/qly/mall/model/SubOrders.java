package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("子订单")
public class SubOrders {
    Integer id;
    Integer userId;
    Integer orderId;
    Integer subOrderId;
    Integer spuId;
    String spuName;
    Integer skuId;
    String skuName;
    String rvUserInfo;
    String encrypRvUserInfo;
    Integer originPrice;
    Integer payPrice;
    Integer subOrderStatus;
    Date createTime;
    Date updateTime;
    Integer version;

    public static class SubOrderStatus{
        public static Integer SOFT_DELETE = 0;
        public static Integer NORMAL = 1;
        public static Integer EXCEPTION = 2;
    }
}