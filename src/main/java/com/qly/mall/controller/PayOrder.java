package com.qly.mall.controller;

import com.qly.mall.model.Orders;
import com.qly.mall.model.Refunds;
import com.qly.mall.model.SubOrders;
import com.qly.mall.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("支付")
@RequestMapping("pay")
public class PayOrder {
    @Autowired
    OrdersService ordersService;

    @PostMapping("oa")
    @ApiOperation("OA支付")
    public Integer OAPayOrder(@RequestBody @ApiParam(name="Orders",value="orders",required=true) Orders orders,
                               @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return ordersService.OAPayOrder(orders, userId);
    }

    @PostMapping("cancel")
    @ApiOperation("取消订单")
    public Integer CancelOrder(@RequestBody @ApiParam(name="Orders",value="orders",required=true) Orders orders,
                              @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return ordersService.CancelOrder(orders, userId);
    }

//    @PostMapping("close")
//    @ApiOperation("超时自动关闭")
//    public Integer CloseOrder(@RequestBody @ApiParam(name="Orders",value="orders",required=true) Orders orders,
//                               @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
//        return ordersService.CloseOrder(orders, userId);
//    }
}
