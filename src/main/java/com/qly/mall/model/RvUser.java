package com.qly.mall.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("用户增加的旅行人信息")
public class RvUser {
    String idCard;
    String encrpyIdCard;
    String realName;
    Integer userId;
    Long create_time;
    Long update_time;
    Integer version;
}
