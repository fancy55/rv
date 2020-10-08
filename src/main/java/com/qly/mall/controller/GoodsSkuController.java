package com.qly.mall.controller;

import com.qly.mall.model.GoodsSku;
import com.qly.mall.model.Inventory;
import com.qly.mall.service.GoodsSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("sku")
@RequestMapping("sku")
public class GoodsSkuController {
    @Autowired
    GoodsSkuService goodsSkuService;

    @PostMapping("create")
    @ApiOperation("创建sku商品")
    public Integer CreateGoodsSku(@RequestBody @ApiParam(name="GoodsSku",value="sku商品",required=true)GoodsSku goodsSku,
                                  @RequestBody @ApiParam(name="Inventory",value="inventory",required=true)Inventory inventory,
                                  @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return goodsSkuService.CreateGoodsSku(goodsSku, inventory, userId);
    }

    @PostMapping("update")
    @ApiOperation("更新sku商品")
    public Integer UpdateGoodsSku(@RequestBody @ApiParam(name="GoodsSku",value="更新的sku商品",required=true)GoodsSku newGoodsSku,
                                  @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return goodsSkuService.UpdateGoodsSku(newGoodsSku, userId);
    }

    @GetMapping("get/skuId/sku")
    @ApiOperation("根据skuId查询sku")
    public GoodsSku GetGoodsSkuBySkuId(@RequestParam @ApiParam(name="Integer",value="skuId",required=true)Integer skuId,
                                       @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return goodsSkuService.GetGoodsSkuBySkuId(skuId, userId);
    }
}
