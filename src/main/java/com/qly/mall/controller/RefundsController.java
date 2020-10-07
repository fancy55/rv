package com.qly.mall.controller;

import com.qly.mall.model.Refunds;
import com.qly.mall.model.RvUser;
import com.qly.mall.model.SubRefunds;
import com.qly.mall.service.RefundsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("退款")
@RequestMapping("refunds")
public class RefundsController {
    @Autowired
    RefundsService refundsService;

    @PostMapping("oa")
    @ApiOperation("退款")
    public Integer PartRefunds(@RequestBody @ApiParam(name="Refunds",value="refunds",required=true)Refunds refunds,
                               @RequestBody @ApiParam(name="SubRefunds",value="subRefunds",required=true) SubRefunds[] subRefunds,
                               @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return refundsService.Refunds(refunds, subRefunds, userId);
    }
}
