package com.qly.mall.mapper;

import com.qly.mall.model.GoodsSku;
import com.qly.mall.model.GoodsSpu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GoodsSpuMapper {
    @Select("select * from goods_spu where spu_id = #{spuId}")
    GoodsSpu FindSpuGoodsBySpuId(Integer spuId);

    @Select("select spu_id from goods_spu where start_pos = #{start}")
    Integer[] FindSpuGoodsByStart(String start);

    @Select("select spu_id from goods_spu where destination_pos = #{destination}")
    Integer[] FindSpuGoodsByDestination(String destination);

    @Insert("insert into goods_spu(spu_id,spu_name,price,banner,`desc`,start_pos,destination_pos,sites,special,route,tips,cate,create_time,update_time,version,status,`date`) values(#{spuId},#{spuName},#{price},#{banner},#{desc},#{startPos},#{destinationPos},#{sites},#{special},#{route},#{tips},#{cate},now(),now(),0,0,#{date})")
    Integer AddSpu(GoodsSpu goodsSpu);

    @Update("update goods_spu set price=#{price},banner=#{banner},`desc`=#{desc},start_pos=#{startPos},destination_pos=#{destinationPos},sites=#{sites},special=#{special},route=#{route},tips=#{tips},update_time=now(),version=version+1 where spu_id=#{spuId}")
    Integer UpdateSpu(GoodsSpu goodsSpu);

    @Update("update goods_spu set price=#{price},update_time=now(),version=version+1 where spu_id=#{spuId}")
    Integer EditSpuPrice(GoodsSpu goodsSpu);

    @Update("update goods_spu set banner=#{banner},update_time=now(),version=version+1 where spu_id=#{spuId}")
    Integer EditSpuBanner(GoodsSpu goodsSpu);

    @Update("update goods_spu set desc=#{desc},update_time=now(),version=version+1 where spu_id=#{spuId}")
    Integer EditSpuDesc(GoodsSpu goodsSpu);

    @Update("update goods_spu set start_pos=#{startPos},update_time=now(),version=version+1 where spu_id=#{spuId}")
    Integer EditSpuStartPos(GoodsSpu goodsSpu);

    @Update("update goods_spu set destination_pos=#{destinationPos},update_time=now(),version=version+1 where spu_id=#{spuId}")
    Integer EditSpuDestinationPos(GoodsSpu goodsSpu);

    @Update("update goods_spu set sites=#{sites},update_time=now(),version=version+1 where spu_id=#{spuId}")
    Integer EditSpuSites(GoodsSpu goodsSpu);

    @Update("update goods_spu set special=#{special},update_time=now(),version=version+1 where spu_id=#{spuId}")
    Integer EditSpuSpecial(GoodsSpu goodsSpu);

    @Update("update goods_spu set route=#{route},update_time=now(),version=version+1 where spu_id=#{spuId}")
    Integer EditSpuRoute(GoodsSpu goodsSpu);

    @Update("update goods_spu set tips=#{tips},update_time=now(),version=version+1 where spu_id=#{spuId}")
    Integer EditSpuTips(GoodsSpu goodsSpu);

    @Update("update goods_spu set cate=#{cate},update_time=now(),version=version+1 where spu_id=#{spuId}")
    Integer EditSpuCate(GoodsSpu goodsSpu);

    @Select("select * from goods_spu where spu_id=#{spuId}")
    GoodsSpu GetGoodsSpuBySpuId(Integer spuId);

    @Select("select * from goods_spu limit #{offset},20")
    GoodsSpu[] GetGoodsSpuByOffset(Integer offset);

    @Select("select * from goods_spu where date = #{date}")
    GoodsSpu[] GetGoodsSpuByDate(Integer date);
}
