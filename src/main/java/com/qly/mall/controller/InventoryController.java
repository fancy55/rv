package com.qly.mall.controller;

import com.qly.mall.model.GoodsSpu;
import com.qly.mall.model.Inventory;
import com.qly.mall.service.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("库存")
@RequestMapping("inv")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @PostMapping("edit/status")
    @ApiOperation("更新sku商品库存状态")
    public Integer UpdateGoodsSpuStatus(@RequestBody @ApiParam(name="Inventory",value="inventory",required=true)Inventory inventory,
                                        @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return inventoryService.UpdateGoodsStatus(inventory, userId);
    }
}
