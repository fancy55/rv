package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("主退款")
public class Refunds {
    Integer refundId;
    Integer orderId;
    Integer userId;
    Integer status;
    Integer refundAmount;
    Integer refundType;
    Integer refundMode;
    String refundReason;
    Date refundTime;
    Date createTime;
    Date updateTime;
    Integer version;
    Integer productNum;
    String subOrderIds;

    public static class RefundStatus{
        static public Integer CREATED = 1; //已创建，待审核
        static public Integer GRANTED = 2; //审核通过
        static public Integer REJECTED = 3; //审核拒绝
        static public Integer PROCESSING = 4; //退款中
        static public Integer SUCCESS = 5; //退款成功
        static public Integer FAIL = 6; //退款失败
    }

    public static class RefundMode{
        static public Integer BACKTRACK = 1; //原路退回
        static public Integer OA = 2; //OA退款
    }

    public static class RefundType{
        static public Integer FullRefund = 1; //全额退款
        static public Integer PARTRefund = 2; //部分退款
    }
}
