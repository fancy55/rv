package com.qly.mall.mapper;

import com.qly.mall.model.Refunds;
import com.qly.mall.model.SubRefunds;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SubRefundsMapper {
    @Insert("insert into sub_refunds(refund_id,sub_refund_id,order_id,sub_order_id,status,refund_amount,refund_type,refund_reason,refund_time,create_time,update_time,version) values(#{refund_id},#{sub_refund_id},#{order_id},#{sub_order_id},#{status},#{refund_amount},#{refund_type},#{refund_reason},#{refund_time},#{create_time},#{update_time},0)")
    Integer CreateSubRefund(SubRefunds subRefunds);

    @Select("select * from sub_refunds where refund_id = #{refundId}")
    Refunds FindSubRefundByRefundId(Integer refundId);

    @Select("select * from sub_refunds where order_id = #{orderId}")
    Refunds FindSubRefundByOrderId(Integer orderId);

    @Select("select * from sub_refunds where user_id = #{userId}")
    Refunds FindSubRefundByUserId(Integer userId);

    @Select("select * from sub_refunds where sub_refund_id = #{subRefundId}")
    Refunds[] FindSubRefundsBySubRefundId(Integer subRefundId);
}
