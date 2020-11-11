package com.qly.mall.controller;

import com.qly.mall.model.*;
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
                               @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        SubOrders[] subOrders = new SubOrders[orders.getProductNum()];
        String[] rvUsers = orders.getPayInfo().split(",");
        for(int i = 0;i < subOrders.length; i++){
            subOrders[i] = new SubOrders();
            subOrders[i].setOriginPrice(orders.getTotalPrice()/3);
            subOrders[i].setPayPrice(orders.getPayPrice()/3);
            subOrders[i].setSkuId(orders.getSkuId());
            subOrders[i].setSkuName(orders.getSkuName());
            subOrders[i].setSpuId(orders.getSpuId());
            subOrders[i].setSpuName(orders.getSpuName());
            subOrders[i].setRvUserInfo(rvUsers[i]);
            subOrders[i].setUserId(orders.getUserId());
        }
        return ordersService.CreateOrder(orders, subOrders, userId);
    }

    @GetMapping("get/order")
    @ApiOperation("根据订单查询订单")
    public Orders GetOrderByOrderId(@RequestParam @ApiParam(name="orderId",value="orderId",required=true)Integer orderId,
                                    @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return ordersService.GetOrderByOrderId(orderId, userId);
    }

    @GetMapping("get/suborder")
    @ApiOperation("根据订单查询子订单")
    public SubOrders[] GetSubOrderByOrderId(@RequestParam @ApiParam(name="orderId",value="orderId",required=true)Integer orderId,
                                      @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return ordersService.GetSubOrderByOrderId(orderId, userId);
    }

    @GetMapping("get/suborder/suborder")
    @ApiOperation("根据子订单查询子订单")
    public SubOrders GetSubOrderBySubOrderId(@RequestParam @ApiParam(name="subOrderId",value="subOrderId",required=true)Integer subOrderId,
                                            @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return ordersService.GetSubOrderBySubOrderId(subOrderId, userId);
    }

    @GetMapping("get/userId/suborder")
    @ApiOperation("根据userId查询子订单")
    public SubOrders[] GetSubOrderByUserId(@RequestParam @ApiParam(name="userId1",value="userId1-查询条件",required=true)Integer userId1,
                                             @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return ordersService.GetSubOrderByUserId(userId1, userId);
    }

    @GetMapping("get/userId/order")
    @ApiOperation("根据userId查询订单")
    public Orders[] GetOrderByUserId(@RequestParam @ApiParam(name="userId1",value="userId1-查询条件",required=true)Integer userId1,
                                       @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return ordersService.GetOrderByUserId(userId1, userId);
    }

    @GetMapping("get/phone/suborder")
    @ApiOperation("根据phone查询子订单")
    public SubOrders[] GetSubOrderByPhone(@RequestParam @ApiParam(name="phone",value="phone",required=true)String phone,
                                            @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return ordersService.GetSubOrderByPhone(phone, userId);
    }

    @GetMapping("get/phone/order")
    @ApiOperation("根据phone查询订单")
    public Orders[] GetRefundByPhone(@RequestParam @ApiParam(name="phone",value="phone",required=true)String phone,
                                      @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return ordersService.GetOrderByPhone(phone, userId);
    }
}
