package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("库存")
public class Inventory {
    Integer spuId;
    String spuName;
    Integer skuId;
    String skuName;
    Integer status;
    Integer inv;
    Integer cap;
    Long createTime;
    Long updateTime;
    Integer version;
}
