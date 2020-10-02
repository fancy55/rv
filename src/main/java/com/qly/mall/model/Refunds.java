package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

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
    String reasonReason;
    Long refundTime;
    Long createTime;
    Long updateTime;
    Integer version;
}
