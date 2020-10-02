package com.qly.mall.mapper;

import com.qly.mall.model.GoodsSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface GoodsSkuMapper {
    @Select("select * from goods_sku where skuId = #{skuId}")
    GoodsSku FindSkuGoodsBySkuId(Integer skuId);
}
