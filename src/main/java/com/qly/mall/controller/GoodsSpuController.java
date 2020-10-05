package com.qly.mall.controller;

import com.qly.mall.model.GoodsSpu;
import com.qly.mall.service.GoodsSpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("spu")
@RequestMapping("spu")
public class GoodsSpuController {
    @Autowired
    GoodsSpuService goodsSpuService;

    @PostMapping("create")
    @ApiOperation("创建spu商品")
    public Integer CreateGoodsSpu(@RequestBody @ApiParam(name="GoodsSpu",value="spu商品",required=true) GoodsSpu goodsSpu,
                               @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return goodsSpuService.CreateGoodsSpu(goodsSpu, userId);
    }
}
