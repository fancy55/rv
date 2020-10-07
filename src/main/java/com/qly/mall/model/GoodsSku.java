package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("商品sku")
public class GoodsSku {
    Integer id;
    Integer skuId;
    String skuName;
    Integer spuId;
    String spuName;
    Integer originPrice;
    Integer currentPrice;
    Long createTime;
    Long updateTime;
    Integer version;
    Integer skuStatus;
    Integer rvCarSize;
    Integer rvCarColor;
    Integer rvCarType;
    Long saleStart;
    Long saleEnd;
    String banner;

    public static class SkuStatus{
        static public Integer ON_SHELF = 1;
        static public Integer OFF_SHELF = 2;
    }

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