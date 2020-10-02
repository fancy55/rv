package com.qly.mall.controller;

import com.qly.mall.model.UserInfo;
import com.qly.mall.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("用户")
@RequestMapping("user")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @PostMapping("register")
    @ApiOperation(value="手机号注册",notes="注册时头像为默认")
    public Integer Register(@RequestBody @ApiParam(name="UserInfo",value="手机号+密码",required=true) UserInfo userInfo,
                            @RequestParam @ApiParam(name="Integer",value="用户类型",required=true)Integer type) {
        return userInfoService.Register(userInfo, type);
    }

    @PostMapping("login")
    @ApiOperation("手机号+密码登录")
    public Integer Login(@RequestBody @ApiParam(name="UserInfo",value="手机号+密码",required=true) UserInfo userInfo) {
        return userInfoService.LoginByPhoneAndPassword(userInfo);
    }

    @PostMapping("alter/password")
    @ApiOperation("修改密码")
    public Integer UpdatePassword(@RequestBody @ApiParam(name="UserInfo",value="手机号+密码",required=true) UserInfo userInfo,
                                  @RequestParam @ApiParam(name="String",value="新密码",required=true)String newPassword) {
        return userInfoService.AlterPassword(userInfo, newPassword);
    }
}
