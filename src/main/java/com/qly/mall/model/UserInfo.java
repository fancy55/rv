package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

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
    String Status; //0:软删除，1：正常，2：异常
}