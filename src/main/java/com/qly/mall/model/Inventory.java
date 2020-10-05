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
    Integer status; //0:软删除、1：上架、2：下架
    Integer inv;
    Integer cap;
    Long createTime;
    Long updateTime;
    Integer version;

    public static class inventoryStatus{
        static public Integer DELETED = 0;
        static public Integer ON_SHELF = 1;
        static public Integer OFF_SHELF = 2;
    }
}
