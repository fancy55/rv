package com.qly.mall.mapper;

import com.qly.mall.model.CollectSku;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CollectSkuMapper {
    @Insert("insert into collect(sku_id,user_id,create_time,update_time,version,status) values(#{skuId},#{userId},now(),now(),0,1)")
    Integer CollectorGoodsSku(Integer skuId, Integer userId);

    @Update("update collect set status=1,version=version+1,update_time=now() where sku_id=#{skuId} and user_id=#{userId}")
    Integer UpdateCollectorGoodsSku(Integer skuId, Integer userId);

    @Update("update collect set status=0,version=version+1,update_time=now() where sku_id=#{skuId} and user_id=#{userId}")
    Integer CancelCollectorGoodsSku(Integer skuId, Integer userId);

    @Select("select * from collect where sku_id=#{skuId} and user_id=#{userId}")
    CollectSku FindCollectBySkuIdAndUserId(Integer skuId, Integer userId);
}
