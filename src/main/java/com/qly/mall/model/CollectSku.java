package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("收藏商品sku")
public class CollectSku {
    Integer skuId;
    Integer userId;
    Integer status;
    Date createTime;
    Date updateTime;
    Integer version;

    public static class CollectStatus{
        static public Integer CANCEL = 0; //取消收藏
        static public Integer COLLECT = 1; //收藏成功
    }
}
