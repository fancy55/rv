package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("用户增加的旅行人信息")
public class RvUser {
    String idCard;
    String encrpyIdCard;
    String realName;
    Integer userId;
    Date createTime;
    Date updateTime;
    Integer version;
    Integer status;
    Integer num;

    static public class RvUserStatus{
        static public Integer DELETE = 0;
        static public Integer NORMAL = 1;
    }
}
