package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("子退款")
public class SubRefunds {
    Integer refundId;
    Integer subRefundId;
    Integer orderId;
    Integer subOrderId;
    Integer status;
    Integer refundAmount;
    Integer refundType;
    Integer refundMode;
    String reasonReason;
    Date createTime;
    Date updateTime;
    Integer version;
}
