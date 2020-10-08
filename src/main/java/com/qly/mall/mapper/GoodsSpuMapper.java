package com.qly.mall.mapper;

import com.qly.mall.model.GoodsSpu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GoodsSpuMapper {
    @Select("select * from goods_spu where spuId = #{spuId}")
    GoodsSpu FindSpuGoodsBySpuId(Integer spuId);

    @Insert("insert into goods_spu(spu_id,spu_name,price,banner,desc,start_pos,destination_pos,sites,special,route,tips,cate,create_time,update_time,version) values(#{spu_id},#{spu_name},#{price},#{banner},#{desc},#{start_pos},#{destination_pos},#{sites},#{special},#{route},#{tips},#{cate},#{create_time},#{update_time},0)")
    Integer AddSpu(GoodsSpu goodsSpu);

    @Update("update goods_spu set price=#{price},banner=#{banner},desc=#{desc},start_pos=#{start_pos},destination_pos=#{destination_pos},sites=#{sites},special=#{special},route=#{route},tips=#{tips},update_time=#{update_time},version=version+1 where spu_id=#{spu_id}")
    Integer UpdateSpu(GoodsSpu goodsSpu);

    @Update("update goods_spu set price=#{price},update_time=#{update_time},version=version+1 where spu_id=#{spu_id}")
    Integer EditSpuPrice(GoodsSpu goodsSpu);

    @Update("update goods_spu set banner=#{banner},update_time=#{update_time},version=version+1 where spu_id=#{spu_id}")
    Integer EditSpuBanner(GoodsSpu goodsSpu);

    @Update("update goods_spu set desc=#{desc},update_time=#{update_time},version=version+1 where spu_id=#{spu_id}")
    Integer EditSpuDesc(GoodsSpu goodsSpu);

    @Update("update goods_spu set start_pos=#{start_pos},update_time=#{update_time},version=version+1 where spu_id=#{spu_id}")
    Integer EditSpuStartPos(GoodsSpu goodsSpu);

    @Update("update goods_spu set destination_pos=#{destination_pos},update_time=#{update_time},version=version+1 where spu_id=#{spu_id}")
    Integer EditSpuDestinationPos(GoodsSpu goodsSpu);

    @Update("update goods_spu set sites=#{sites},update_time=#{update_time},version=version+1 where spu_id=#{spu_id}")
    Integer EditSpuSites(GoodsSpu goodsSpu);

    @Update("update goods_spu set special=#{special},update_time=#{update_time},version=version+1 where spu_id=#{spu_id}")
    Integer EditSpuSpecial(GoodsSpu goodsSpu);

    @Update("update goods_spu set route=#{route},update_time=#{update_time},version=version+1 where spu_id=#{spu_id}")
    Integer EditSpuRoute(GoodsSpu goodsSpu);

    @Update("update goods_spu set tips=#{tips},update_time=#{update_time},version=version+1 where spu_id=#{spu_id}")
    Integer EditSpuTips(GoodsSpu goodsSpu);

    @Update("update goods_spu set cate=#{cate},update_time=#{update_time},version=version+1 where spu_id=#{spu_id}")
    Integer EditSpuCate(GoodsSpu goodsSpu);

    @Select("select * from goods_spu where spu_id=#{spuId}")
    GoodsSpu GetGoodsSpuBySpuId(Integer spuId);
}
