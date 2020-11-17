package com.qly.mall.controller;

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
    @ApiOperation("更新sku商品状态")
    public Integer UpdateGoodsSpuStatus(@RequestBody @ApiParam(name="Inventory",value="inventory",required=true)Inventory inventory,
                                        @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return inventoryService.UpdateGoodsStatus(inventory, userId);
    }

//    @PostMapping("update")
//    @ApiOperation("更新商品库存")
//    public Integer UpdateGoodsSkuInventory(@RequestBody @ApiParam(name="Inventory",value="inventory",required=true)Inventory inventory,
//                                            @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
//        return inventoryService.UpdateGoodsSkuInventory(inventory, userId);
//    }

    @PostMapping("update/cap")
    @ApiOperation("更新商品容量")
    public Integer UpdateGoodsSkuCap(@RequestBody @ApiParam(name="Inventory",value="inventory",required=true)Inventory inventory,
                                     @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return inventoryService.UpdateGoodsSkuCap(inventory, userId);
    }

    @PostMapping("get")
    @ApiOperation("获取商品库存")
    public Inventory GetGoodsSkuInventoryBySkuId(@RequestParam @ApiParam(name="skuId",value="skuId",required=true)Integer skuId,
                                     @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return inventoryService.GetGoodsSkuInventoryBySkuId(skuId, userId);
    }
}
