package com.qly.mall.mapper;

import com.qly.mall.model.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrdersMapper {
    @Insert("insert into orders(user_id,order_id,pay_type,status,product_num,total_price,pay_price,pay_info,create_time,update_time,version) values(#{user_id},#{order_id},#{pay_type},#{status},#{product_num},#{total_price},#{pay_price},#{pay_info},now(),now(),0)")
    Integer CreateOrder(Orders orders);

    @Select("select user_id from orders where order_id = #{order_id}")
    Integer FindOrderByOrderId(Integer orderId);

    @Update("update orders set status=#{status},close_time=#{closeTime},update_time=#{update_time},version=version+1 where order_id=#{order_id}")
    Integer UpdateOrder(Orders orders);

    @Update("update orders set status=#{status},update_time=#{updateTime},version=version+1 where order_id=#{order_id}")
    Integer UpdateOrderStatus(Integer status, Long updateTime, Integer orderId);

    @Select("select * from orders where order_id = #{order_id}")
    Orders FindOrdersByOrderId(Integer orderId);

    @Select("select * from orders where user_id = #{userId}")
    Orders[] FindOrderByUserId(Integer userId);
}
