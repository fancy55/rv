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
                                  @RequestParam @ApiParam(name="cap",value="cap",required=true)Integer cap,
                                  @RequestParam @ApiParam(name="inv",value="inv",required=true)Integer inv,
                                  @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        Inventory inventory = new Inventory();
        inventory.setSpuId(goodsSku.getSpuId());
        inventory.setSpuName(goodsSku.getSpuName());
        inventory.setSkuName(goodsSku.getSkuName());
        inventory.setCap(cap);
        inventory.setInv(inv);
        inventory.setStatus(2);
        return goodsSkuService.CreateGoodsSku(goodsSku, inventory, userId);
    }

    @PostMapping("update")
    @ApiOperation("更新sku商品")
    public Integer UpdateGoodsSku(@RequestBody @ApiParam(name="GoodsSku",value="更新的sku商品",required=true)GoodsSku newGoodsSku,
                                  @RequestParam @ApiParam(name="cap",value="add_cap",required=true)Integer addCap,
                                  @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return goodsSkuService.UpdateGoodsSku(newGoodsSku, userId);
    }

    @GetMapping("get/skuId/sku")
    @ApiOperation("根据skuId查询sku")
    public GoodsSku GetGoodsSkuBySkuId(@RequestParam @ApiParam(name="skuId",value="skuId",required=true)Integer skuId,
                                       @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return goodsSkuService.GetGoodsSkuBySkuId(skuId, userId);
    }
}
