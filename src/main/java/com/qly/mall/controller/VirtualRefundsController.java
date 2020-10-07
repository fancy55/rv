package com.qly.mall.controller;

import com.qly.mall.model.Refunds;
import com.qly.mall.service.RefundsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("虚拟退款")
@RequestMapping("refunds")
public class VirtualRefundsController {
    @Autowired
    RefundsService refundsService;

    @PostMapping("part")
    @ApiOperation("部分退款")
    public Integer PartRefunds(@RequestBody @ApiParam(name="Refunds",value="refunds",required=true) Refunds refunds,
                               @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return refundsService.PartRefunds(refunds, userId);
    }

    @PostMapping("all")
    @ApiOperation("全部退款")
    public Integer AllRefunds(@RequestBody @ApiParam(name="Refunds",value="refunds",required=true)Refunds refunds,
                              @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return refundsService.AllRefunds(refunds, userId);
    }
}
