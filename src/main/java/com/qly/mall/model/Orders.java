package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("订单")
public class Orders {
    Integer id;
    Integer user_id;
    Integer order_id;
    Integer product_num;
    Integer total_price;
    Integer pay_price;
    String pay_info;
    Date create_time;
    Date update_time;
    Date pay_time;
    Date close_time;
    Integer version;
    OrderStatus status;
    PayType pay_type;
}

class OrderStatus{
    Integer SOFT_DELETE = 0;
    Integer NORMAL = 1;
    Integer EXCEPTION = 2;
}

class PayType{
    Integer SOFT_DELETE = 0;
    Integer NORMAL = 1;
    Integer EXCEPTION = 2;
}