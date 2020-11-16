package com.qly.mall.mapper;

import com.qly.mall.model.Orders;
import com.qly.mall.model.SubOrders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SubOrdersMapper {
    @Insert("insert into sub_orders(sub_order_id,user_id,order_id,spu_id,spu_name,sku_id,sku_name,rv_user_info,encryp_rv_user_info,status,origin_price,pay_price,create_time,update_time,version) values(#{subOrderId},#{userId},#{orderId},#{spuId},#{spuName},#{skuId},#{skuName},#{rvUserInfo},#{encrypRvUserInfo},#{status},#{originPrice},#{payPrice},now(),now(),0)")
    Integer CreateSubOrder(SubOrders suborders);

    @Select("select user_id from sub_orders where sub_order_id = #{subOrderId}")
    Integer FindSubOrderBySubOrderId(Integer subOrderId);

    @Select("select * from sub_orders where sub_order_id = #{subOrderId}")
    SubOrders FindSubOrdersBySubOrderId(Integer subOrderId);

    @Select("select * from sub_orders where order_id = #{orderId}")
    SubOrders[] FindSubOrderByOrderId(Integer orderId);

    @Select("select * from sub_orders where user_id = #{userId}")
    SubOrders[] FindSubOrderByUserId(Integer userId);
}
