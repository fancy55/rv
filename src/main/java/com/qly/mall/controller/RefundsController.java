package com.qly.mall.controller;

import com.qly.mall.model.*;
import com.qly.mall.service.RefundsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("退款")
@RequestMapping("refunds")
public class RefundsController {
    @Autowired
    RefundsService refundsService;

    @PostMapping("oa")
    @ApiOperation("退款")
    public Integer PartRefunds(@RequestBody @ApiParam(name="Refunds",value="refunds",required=true)Refunds refunds,
                               @RequestBody @ApiParam(name="SubRefunds",value="subRefunds",required=true) SubRefunds[] subRefunds,
                               @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return refundsService.Refunds(refunds, subRefunds, userId);
    }

    @GetMapping("get/order/refund")
    @ApiOperation("根据订单查询退款")
    public Refunds GetRefundByOrderId(@RequestParam @ApiParam(name="Integer",value="orderId",required=true)Integer orderId,
                                    @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return refundsService.GetRefundByOrderId(orderId, userId);
    }

    @GetMapping("get/order/subrefund")
    @ApiOperation("根据订单查询子退款")
    public SubRefunds[] GetSubRefundByOrderId(@RequestParam @ApiParam(name="Integer",value="orderId",required=true)Integer orderId,
                                            @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return refundsService.GetSubRefundByOrderId(orderId, userId);
    }

    @GetMapping("get/refund/refund")
    @ApiOperation("根据退款查询退款")
    public Refunds GetRefundByRefundId(@RequestParam @ApiParam(name="Integer",value="refundId",required=true)Integer refundId,
                                             @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return refundsService.GetRefundByRefundId(refundId, userId);
    }

    @GetMapping("get/refund/subrefund")
    @ApiOperation("根据退款查询子退款")
    public SubRefunds[] GetSubRefundByRefundId(@RequestParam @ApiParam(name="Integer",value="refundId",required=true)Integer refundId,
                                             @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return refundsService.GetSubRefundByRefundIdd(refundId, userId);
    }

    @GetMapping("get/subrefund/subrefund")
    @ApiOperation("根据子退款查询子退款")
    public SubRefunds GetSubRefundBySubRefundId(@RequestParam @ApiParam(name="Integer",value="subRefundId",required=true)Integer subRefundId,
                                             @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return refundsService.GetSubRefundBySubRefundId(subRefundId, userId);
    }

    @GetMapping("get/userId/subrefund")
    @ApiOperation("根据userId查询子退款")
    public SubRefunds[] GetSubRefundByUserId(@RequestParam @ApiParam(name="Integer",value="userId1-查询条件",required=true)Integer userId1,
                                             @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return refundsService.GetSubRefundByUserId(userId1, userId);
    }

    @GetMapping("get/userId/refund")
    @ApiOperation("根据userId查询退款")
    public Refunds[] GetRefundByUserId(@RequestParam @ApiParam(name="Integer",value="userId1-查询条件",required=true)Integer userId1,
                                       @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return refundsService.GetRefundByUserId(userId1, userId);
    }

    @GetMapping("get/phone/subrefund")
    @ApiOperation("根据phone查询子退款")
    public SubRefunds[] GetSubRefundByPhone(@RequestParam @ApiParam(name="String",value="phone",required=true)String phone,
                                            @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return refundsService.GetSubRefundByPhone(phone, userId);
    }

    @GetMapping("get/phone/refund")
    @ApiOperation("根据phone查询退款")
    public Refunds[] GetRefundByPhone(@RequestParam @ApiParam(name="String",value="phone",required=true)String phone,
                                      @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return refundsService.GetRefundByPhone(phone, userId);
    }
}
