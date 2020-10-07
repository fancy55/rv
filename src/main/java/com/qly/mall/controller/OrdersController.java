package com.qly.mall.controller;

import com.qly.mall.model.Orders;
import com.qly.mall.model.SubOrders;
import com.qly.mall.service.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("订单")
@RequestMapping("orders")
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @PostMapping("create")
    @ApiOperation("创建订单&子订单")
    public Integer CreateOrder(@RequestBody @ApiParam(name="Orders",value="订单",required=true) Orders orders,
                              @RequestBody @ApiParam(name="SubOrders",value="子订单",required=true) SubOrders[] subOrders,
                               @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return ordersService.CreateOrder(orders, subOrders, userId);
    }

    @GetMapping("get/order")
    @ApiOperation("根据订单查询订单")
    public Orders GetOrderByOrderId(@RequestParam @ApiParam(name="Integer",value="orderId",required=true)Integer orderId,
                                    @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return ordersService.GetOrderByOrderId(orderId, userId);
    }

    @GetMapping("get/suborder")
    @ApiOperation("根据订单查询子订单")
    public SubOrders[] GetSubOrderByOrderId(@RequestParam @ApiParam(name="Integer",value="orderId",required=true)Integer orderId,
                                      @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return ordersService.GetSubOrderByOrderId(orderId, userId);
    }

    @GetMapping("get/suborder/suborder")
    @ApiOperation("根据子订单查询子订单")
    public SubOrders GetSubOrderBySubOrderId(@RequestParam @ApiParam(name="Integer",value="subOrderId",required=true)Integer subOrderId,
                                            @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return ordersService.GetSubOrderBySubOrderId(subOrderId, userId);
    }
}
