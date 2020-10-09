package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("用户信息")
public class UserInfo {
    Integer userId;
    String phone;
    String wechat;
    String password;
    String nickName;
    Date createTime;
    Date updateTime;
    Integer status;
    Integer type;
    String photo;

    public static class UserType{
        static public Integer NORMAL = 0; //普通用户
        static public Integer SUPER = 1; //超级管理员
        static public Integer VIP = 2; //VIP用户
    }

    public static class UserStatus{
        static public Integer SOFT_DELETE = 0; //已注销
        static public Integer NORMAL = 1; //正常
        static public Integer EXCEPTION = 2; //异常
    }
}