package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("商品sku")
public class GoodsSku {
    Integer skuId;
    String skuName;
    Integer spuId;
    String spuName;
    Integer originPrice;
    Integer currentPrice;
    Date createTime;
    Date updateTime;
    Integer version;
    Integer rvCarSize;
    Integer rvCarColor;
    Integer rvCarType;
    Date saleStart;
    Date saleEnd;
    String banner;
    Integer num;
    //库存状态
    Integer status; //0:软删除、1：上架、2：下架
    Integer inv;
    Integer cap;

    public static class RvCar{
        static public RvCarType carType;
        static public RvCarColor carColor;
        static public RvCarSize carSize;
    }

    public static class RvCarSize{
        static public Integer SMALL = 0;
        static public Integer MIDDLE = 1;
        static public Integer BIG = 2;
    }

    public class RvCarColor{
        public Integer WHITE = 0;
        public Integer BLACK = 1;
    }

    //房车公司
    public class RvCarType{
        public String A;
        public String B;
        public String C;
    }
}