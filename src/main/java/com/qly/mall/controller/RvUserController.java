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
    @ApiOperation(value="更新旅人信息",notes="返回1更新成功")
    public Integer UpdateRvUser(@RequestBody @ApiParam(name="RvUser",value="userId+idCard+realName+offset",required=true)RvUser rvUser,
                                @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return rvUserService.UpdateRvUser(rvUser, userId);
    }

    @PostMapping("create")
    @ApiOperation(value="创建旅人信息", notes="返回加密的身份证")
    public Integer CreateRvUser(@RequestBody @ApiParam(name="RvUser",value="userId+idCard+realName",required=true)RvUser rvUser,
                                @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return rvUserService.CreateRvUser(rvUser, userId);
    }

    @PostMapping("delete")
    @ApiOperation(value="删除旅人信息",notes="返回1删除成功")
    public Integer DeleteRvUser(@RequestBody @ApiParam(name="RvUser",value="userId+idCard",required=true)RvUser rvUser,
                                @RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return rvUserService.DeleteRvUser(rvUser, userId);
    }

    @GetMapping("get")
    @ApiOperation("获取旅人信息")
    public List<RvUser> GetRvUserByUserId(@RequestParam @ApiParam(name="userId",value="userId",required=true)Integer userId){
        return rvUserService.GetRvUsersByUserId(userId);
    }
}
