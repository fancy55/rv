package com.qly.mall.controller;

import com.qly.mall.model.RvUser;
import com.qly.mall.service.RVUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("RvUser")
@RequestMapping("rv")
public class RvUserController {
    @Autowired
    RVUserService rvUserService;

    @PostMapping("edit")
    @ApiOperation("更新旅人信息")
    public Integer UpdateRvUser(@RequestBody @ApiParam(name="RvUser",value="rvUser",required=true)RvUser rvUser,
                                @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return rvUserService.UpdateRvUser(rvUser, userId);
    }

    @PostMapping("create")
    @ApiOperation("创建旅人信息")
    public Integer CreateRvUser(@RequestBody @ApiParam(name="RvUser",value="rvUser",required=true)RvUser rvUser,
                                @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return rvUserService.CreateRvUser(rvUser, userId);
    }

    @PostMapping("delete")
    @ApiOperation("删除旅人信息")
    public Integer DeleteRvUser(@RequestBody @ApiParam(name="RvUser",value="rvUser",required=true)RvUser rvUser,
                                @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return rvUserService.DeleteRvUser(rvUser, userId);
    }

    @GetMapping("get")
    @ApiOperation("获取旅人信息")
    public List<RvUser> GetRvUserByUserId(@RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return rvUserService.GetRvUsersByUserId(userId);
    }
}
