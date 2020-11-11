package com.qly.mall.controller;

import com.qly.mall.service.CollectSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("收藏")
@RequestMapping("collect")
public class CollectorSkuController {
    @Autowired
    CollectSkuService collectSkuService;

    @PostMapping("sku")
    @ApiOperation("收藏商品")
    public Integer CollectorGoodsSku(@RequestParam @ApiParam(name="skuId",value="skuId",required=true)Integer skuId,
                                     @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return collectSkuService.CollectorGoodsSku(skuId, userId);
    }

    @PostMapping("cancel")
    @ApiOperation("取消收藏商品")
    public Integer CancelCollectorGoodsSku(@RequestParam @ApiParam(name="skuId",value="skuId",required=true)Integer skuId,
                                     @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return collectSkuService.CancelCollectorGoodsSku(skuId, userId);
    }
}
