package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("商品spu")
public class GoodsSpu {
    Integer spuId;
    String spuName;
    String startPos;
    String desc;
    String destinationPos;
    String sites;
    Integer price;
    String special;
    String route;
    String tips;
    Date createTime;
    Date updateTime;
    Integer version;
    Integer cateType;
    String banner;

    public static class CateType{
        static public Integer VIRTUAL;
        static public Integer ENTITY;
    }
}
