package com.qly.mall.mapper;

import com.qly.mall.model.GoodsSku;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GoodsSkuMapper {
    @Select("select * from goods_sku where skuId = #{skuId}")
    GoodsSku FindSkuGoodsBySkuId(Integer skuId);

    @Select("select * from goods_sku where spuId = #{spuId}")
    GoodsSku[] FindSkuGoodsBySpuId(Integer spuId);

    @Insert("insert into goods_sku(sku_id,sku_name,spu_id,spu_name,origin_price,current_price,status,rv_car_size,rv_car_type,rv_car_color,banner,sale_start,sale_end,create_time,update_time,version) values(#{sku_id},#{sku_name},#{spu_id},#{spu_name},#{origin_price},#{current_price},#{status},#{rv_car_size},#{rv_car_type},#{rv_car_color},#{banner},#{sale_start},#{sale_end},#{create_time},#{update_time},0)")
    Integer AddSku(GoodsSku goodsSku);

    @Update("update goods_sku set origin_price=#{origin_price},current_price=#{current_price},rv_car_size=#{rv_car_size},rv_car_type=#{rv_car_type},rv_car_color=#{rv_car_color},banner=#{banner},sale_start=#{sale_start},sale_end=#{sale_end},update_time=#{update_time},version=version+1 where sku_id=#{sku_id}")
    Integer UpdateSpu(GoodsSku goodsSku);

    @Update("update goods_sku set origin_price=#{origin_price},update_time=#{update_time},version=version+1 where sku_id=#{sku_id}")
    Integer EditSkuOriginPrice(GoodsSku goodsSku);

    @Update("update goods_sku set current_price=#{current_price},update_time=#{update_time},version=version+1 where sku_id=#{sku_id}")
    Integer EditSkuCurrentPrice(GoodsSku goodsSku);

    @Update("update goods_sku set status=#{status},update_time=#{update_time},version=version+1 where sku_id=#{sku_id}")
    Integer EditSkuStatus(GoodsSku goodsSku);

    @Update("update goods_sku set rv_car_size=#{rv_car_size},update_time=#{update_time},version=version+1 where sku_id=#{sku_id}")
    Integer EditSkuRvCarSize(GoodsSku goodsSku);

    @Update("update goods_sku set rv_car_type=#{rv_car_type},update_time=#{update_time},version=version+1 where sku_id=#{sku_id}")
    Integer EditSkuRvCarType(GoodsSku goodsSku);

    @Update("update goods_sku set rv_car_color=#{rv_car_color},update_time=#{update_time},version=version+1 where sku_id=#{sku_id}")
    Integer EditSkuRvCarColor(GoodsSku goodsSku);

    @Update("update goods_sku set banner=#{banner},update_time=#{update_time},version=version+1 where sku_id=#{sku_id}")
    Integer EditSkuBanner(GoodsSku goodsSku);

    @Update("update goods_sku set sale_start=#{sale_start},update_time=#{update_time},version=version+1 where sku_id=#{sku_id}")
    Integer EditSkuSaleStart(GoodsSku goodsSku);

    @Update("update goods_sku set sale_end=#{sale_end},update_time=#{update_time},version=version+1 where sku_id=#{sku_id}")
    Integer EditSkuSaleEnd(GoodsSku goodsSku);
}
