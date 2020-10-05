package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("商品spu")
public class GoodsSpu {
    Integer spuId;
    Integer spuName;
    String startPos;
    String destinationPos;
    String sites;
    Integer price;
    String special;
    String route;
    String tips;
    Long createTime;
    Long updateTime;
    Integer version;
    Integer cateType;
    String banner;

    public static class CateType{
        static public Integer VIRTUAL;
        static public Integer ENTITY;
    }
}
