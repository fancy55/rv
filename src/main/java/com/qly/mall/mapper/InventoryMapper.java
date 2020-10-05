package com.qly.mall.mapper;

import com.qly.mall.model.Inventory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface InventoryMapper {
    @Insert("insert into inventory(spu_id,spu_name,sku_id,sku_name,status,inv,cap,create_time,update_time,version) values(#{spu_id},#{spu_name},#{sku_id},#{sku_name},2,#{inv},#{cap},#{create_time},#{update_time},0)")
    Integer AddInventory(Inventory inventory);

    @Update("update inventory set status=#{status},update_time=#{update_time},version=version+1 where sku_id=#{sku_id}")
    Integer EditInventoryStatus(Inventory inventory);

    @Update("update inventory set inv=#{inv},update_time=#{update_time},version=version+1 where sku_id=#{sku_id}")
    Integer EditInventoryInv(Inventory inventory);

    @Update("update inventory set cap=#{cap},update_time=#{update_time},version=version+1 where sku_id=#{sku_id}")
    Integer EditInventoryCap(Inventory inventory);

    @Update("select * from inventory where sku_id=#{sku_id}")
    Inventory FindInventoryBySkuId(Integer sku_id);

    @Update("select * from inventory where spu_id=#{spu_id}")
    Inventory[] FindInventoryBySpuId(Integer spu_id);
}
