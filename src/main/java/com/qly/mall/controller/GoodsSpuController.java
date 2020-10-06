package com.qly.mall.controller;

import com.qly.mall.model.GoodsSku;
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
    public Integer CreateGoodsSpu(@RequestBody @ApiParam(name="GoodsSpu",value="spu商品",required=true)GoodsSpu goodsSpu,
                               @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return goodsSpuService.CreateGoodsSpu(goodsSpu, userId);
    }

    @PostMapping("update")
    @ApiOperation("更新spu商品")
    public Integer UpdateGoodsSpu(@RequestBody @ApiParam(name="GoodsSpu",value="更新的spu商品",required=true)GoodsSpu newGoodsSpu,
                                  @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return goodsSpuService.UpdateGoodsSpu(newGoodsSpu, userId);
    }

    @PostMapping("edit/status")
    @ApiOperation("更新spu商品状态")
    public Integer UpdateGoodsSpuStatus(@RequestBody @ApiParam(name="GoodsSpu",value="spu商品",required=true)GoodsSpu goodsSpu,
                                        @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return goodsSpuService.UpdateGoodsSpuStatus(goodsSpu, userId);
    }
}
