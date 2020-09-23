package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("子订单")
public class SubOrders {
    Integer id;
    Integer user_id;
    Integer order_id;
    Integer sub_order_id;
    Integer spu_id;
    String spu_name;
    Integer sku_id;
    String sku_name;
    String rv_ser_info;
    String encryp_rv_user_info;
    Integer origin_price;
    Integer pay_price;
    SubOrderStatus subOrderStatus;
    Date create_time;
    Date update_time;
    Integer version;
}

class SubOrderStatus{
    Integer SOFT_DELETE = 0;
    Integer NORMAL = 1;
    Integer EXCEPTION = 2;
}