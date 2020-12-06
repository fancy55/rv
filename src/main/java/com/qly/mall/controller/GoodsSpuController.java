package com.qly.mall.controller;

import com.qly.mall.model.GoodsSku;
import com.qly.mall.model.GoodsSpu;
import com.qly.mall.model.Orders;
import com.qly.mall.service.GoodsSkuService;
import com.qly.mall.service.GoodsSpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api("spu")
@RequestMapping("spu")
public class GoodsSpuController {
    @Autowired
    GoodsSpuService goodsSpuService;
    @Autowired
    GoodsSkuService goodsSkuService;

    @PostMapping("create")
    @ApiOperation("创建spu商品")
    public Integer CreateGoodsSpu(@RequestBody @ApiParam(name="GoodsSpu",value="spu商品",required=true)GoodsSpu goodsSpu,
                               @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return goodsSpuService.CreateGoodsSpu(goodsSpu, userId);
    }

    @PostMapping("update")
    @ApiOperation("更新spu商品")
    public Integer UpdateGoodsSpu(@RequestBody @ApiParam(name="GoodsSpu",value="更新的spu商品",required=true)GoodsSpu newGoodsSpu,
                                  @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return goodsSpuService.UpdateGoodsSpu(newGoodsSpu, userId);
    }

    @GetMapping("get/spuId/spu")
    @ApiOperation("根据spuId查询spu")
    public GoodsSpu GetGoodsSpuBySpuId(@RequestParam @ApiParam(name="spuId",value="spuId",required=true)Integer spuId,
                                     @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return goodsSpuService.GetGoodsSpuBySpuId(spuId, userId);
    }

    @GetMapping("get/spuId/sku")
    @ApiOperation("根据spuId查询sku")
    public GoodsSku[] GetGoodsSkuBySpuId(@RequestParam @ApiParam(name="spuId",value="spuId",required=true)Integer spuId,
                                         @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return goodsSkuService.GetGoodsSkuBySpuId(spuId, userId);
    }

    @GetMapping("get/spugoods/offset")
    @ApiOperation("根据偏移20查询spu商品")
    public GoodsSpu[] GetGoodsSpuByOffset(@RequestParam @ApiParam(name="offset",value="offset",required=true)Integer offset,
                                          @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return goodsSpuService.GetGoodsSpuByOffset(offset, userId);
    }

    @GetMapping("get/goods/date")
    @ApiOperation("根据出游时间长短查询商品")
    public List<GoodsSpu[]> GetGoodsSpuByDate(@RequestParam @ApiParam(name="date",value="date",required=true)String date,
                                              @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return goodsSpuService.GetGoodsSpuByDate(date, userId);
    }

    @GetMapping("get/goods/start")
    @ApiOperation("根据出游出发点查询商品")
    public List<GoodsSpu> GetGoodsSpuByStart(@RequestParam @ApiParam(name="start",value="start",required=true)String start,
                                               @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return goodsSpuService.GetGoodsSpuByStart(start, userId);
    }

    @GetMapping("get/goods/desctination")
    @ApiOperation("根据出游目的点查询商品")
    public List<GoodsSpu> GetGoodsSpuByDesctination(@RequestParam @ApiParam(name="destination",value="destination",required=true)String destination,
                                                      @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return goodsSpuService.GetGoodsSpuByDesctination(destination, userId);
    }
}
