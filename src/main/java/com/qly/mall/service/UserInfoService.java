package com.qly.mall.service;

import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    public void Register(UserInfo userInfo){
        userInfoMapper.Register(userInfo);
    }
}
