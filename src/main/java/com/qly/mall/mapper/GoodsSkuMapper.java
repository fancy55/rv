package com.qly.mall.mapper;

import com.qly.mall.model.GoodsSku;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GoodsSkuMapper {
    @Select("select * from goods_sku where sku_id = #{skuId}")
    GoodsSku FindSkuGoodsBySkuId(Integer skuId);

    @Select("select * from goods_sku where spu_id = #{spuId}")
    GoodsSku[] FindSkuGoodsBySpuId(Integer spuId);

    @Insert("insert into goods_sku(sku_id,sku_name,spu_id,spu_name,origin_price,current_price,rv_car_size,rv_car_type,rv_car_color,banner,sale_start,sale_end,create_time,update_time,version) values(#{skuId},#{skuName},#{spuId},#{spuName},#{originPrice},#{currentPrice},#{rvCarSize},#{rvCarType},#{rvCarColor},#{banner},#{saleStart},#{saleEnd},now(),now(),0)")
    Integer AddSku(GoodsSku goodsSku);

    @Update("update goods_sku set origin_price=#{originPrice},current_price=#{currentPrice},rv_car_size=#{rvCarSize},rv_car_type=#{rvCarType},rv_car_color=#{rvCarColor},banner=#{banner},sale_start=#{saleStart},sale_end=#{saleEnd},update_time=now(),version=version+1 where sku_id=#{skuId}")
    Integer UpdateSpu(GoodsSku goodsSku);

    @Update("update goods_sku set origin_price=#{originPrice},update_time=now(),version=version+1 where sku_id=#{skuId}")
    Integer EditSkuOriginPrice(GoodsSku goodsSku);

    @Update("update goods_sku set current_price=#{currentPrice},update_time=now(),version=version+1 where sku_id=#{skuId}")
    Integer EditSkuCurrentPrice(GoodsSku goodsSku);

    @Update("update goods_sku set rv_car_size=#{rvCarSize},update_time=now(),version=version+1 where sku_id=#{skuId}")
    Integer EditSkuRvCarSize(GoodsSku goodsSku);

    @Update("update goods_sku set rv_car_type=#{rvCarType},update_time=now(),version=version+1 where sku_id=#{skuId}")
    Integer EditSkuRvCarType(GoodsSku goodsSku);

    @Update("update goods_sku set rv_car_color=#{rvCarColor},update_time=now(),version=version+1 where sku_id=#{skuId}")
    Integer EditSkuRvCarColor(GoodsSku goodsSku);

    @Update("update goods_sku set banner=#{banner},update_time=now(),version=version+1 where sku_id=#{skuId}")
    Integer EditSkuBanner(GoodsSku goodsSku);

    @Update("update goods_sku set sale_start=#{saleStart},update_time=now(),version=version+1 where sku_id=#{skuId}")
    Integer EditSkuSaleStart(GoodsSku goodsSku);

    @Update("update goods_sku set sale_end=#{saleEnd},update_time=now(),version=version+1 where sku_id=#{skuId}")
    Integer EditSkuSaleEnd(GoodsSku goodsSku);

    @Select("select * from goods_sku where spu_id=#{spuId}")
    GoodsSku[] GetGoodsSkuBySpuId(Integer spuId);

    @Select("select * from goods_sku where sku_id=#{skuId}")
    GoodsSku GetGoodsSkuBySkuId(Integer skuId);
}
