package com.qly.mall.mapper;

import com.qly.mall.model.Refunds;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RefundsMapper {
    @Insert("insert into refunds(refund_id,order_id,user_id,status,refund_amount,refund_mode,refund_type,refund_reason,refund_time,create_time,update_time,version) values(#{refund_id},#{order_id},#{user_id},#{status},#{refund_amount},#{refund_mode},#{refund_type},#{refund_reason},#{refund_time},#{create_time},#{update_time},0)")
    Integer CreateRefund(Refunds refunds);

    @Select("select * from refunds where refund_id = #{refundId}")
    Refunds FindRefundByRefundId(Integer refundId);

    @Select("select * from refunds where order_id = #{orderId}")
    Refunds FindRefundByOrderId(Integer orderId);

    @Select("select * from refunds where user_id = #{userId}")
    Refunds[] FindRefundByUserId(Integer userId);

    @Update("update refunds set status=#{status} where refund_id=#{refundId}")
    Integer UpdateRefunds(Integer refundId);
}
