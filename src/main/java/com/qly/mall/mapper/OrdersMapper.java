package com.qly.mall.mapper;

import com.qly.mall.model.GoodsSpu;
import com.qly.mall.model.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface OrdersMapper {
    @Insert("insert into orders(user_id,order_id,pay_type,status,product_num,total_price,pay_price,pay_info,create_time,update_time,version) values(#{userId},#{orderId},#{payType},#{status},#{productNum},#{totalPrice},#{payPrice},#{payInfo},now(),now(),0)")
    Integer CreateOrder(Orders orders);

    @Select("select user_id from orders where order_id = #{orderId}")
    Integer FindOrderByOrderId(Integer orderId);

    @Update("update orders set status=#{status},close_time=now(),update_time=now(),version=version+1 where order_id=#{orderId}")
    Integer UpdateOrder(Orders orders);

    @Update("update orders set status=#{status},update_time=now(),version=version+1 where order_id=#{orderId}")
    Integer UpdateOrderStatus(Integer status, Integer orderId);

    @Select("select * from orders where order_id = #{orderId}")
    Orders FindOrdersByOrderId(Integer orderId);

    @Select("select status from orders where order_id = #{orderId}")
    Integer FindOrdersStatusByOrderId(Integer orderId);

    @Select("select * from orders where user_id = #{userId} order by create_time desc")
    Orders[] FindOrderByUserId(Integer userId);

    @Select("select * from orders limit #{offset},20")
    Orders[] GetOrdersByOffset(Integer offset);
}
