package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;

@Data
@ApiModel("用户信息")
public class UserInfo {
    Integer id;
    Integer user_id;
    String phone;
    String we_chat;
    String password;
    String nick_name;
    String create_time;
    String update_time;
    Integer userStatus; //0:软删除，1：正常，2：异常
    UserType userType;

    public static class UserType{
        static public Integer NORMAL = 0;
        static public Integer SUPER = 1;
        static public Integer EXCEPTION = 2;
    }

    public static class UserStatus{
        static public Integer SOFT_DELETE = 0;
        static public Integer NORMAL = 1;
        static public Integer EXCEPTION = 2;
    }
}