package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.UserInfo;
import com.qly.mall.util.SmsUtil;
import org.apache.http.client.HttpResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserInfoService {
    static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    UserInfoMapper userInfoMapper;

    public Integer Register(UserInfo userInfo){
        CheckParam(userInfo);
        Integer user_id = getUserId();
        userInfoMapper.Register(userInfo);
        logger.info(userInfo.getPhone()+"注册成功");
        return user_id;
    }

    public Integer LoginByPhoneAndPassword(UserInfo userInfo){
        CheckParamLogin(userInfo);
        Integer user_id = userInfoMapper.LoginWithPhone(userInfo.getPhone(),userInfo.getPassword());
        if(user_id == null){
            logger.info("登录失败-手机号错误或密码错误");
            throw new  ErrorException(ErrorNo.LOGIN_FAIL_PASSWORD.code(), ErrorNo.LOGIN_FAIL_PASSWORD.msg());
        }
        return user_id;
    }

    public void CheckParam(UserInfo userInfo){
        if(userInfo.getPhone() == null || userInfo.getPhone().length() != 11) {
            logger.error(userInfo.getPhone() + "手机号码为空或格式不正确");
            throw new ErrorException(ErrorNo.PHONE_EMPTY_OR_FORMAT_ERROR.code(), ErrorNo.PHONE_EMPTY_OR_FORMAT_ERROR.msg());
        }
        if(userInfoMapper.FindUserIdByPhone(userInfo.getPhone()) != null){
            logger.error(userInfo.getPhone() + "手机已注册，不能重复注册");
            throw new ErrorException(ErrorNo.PHONE_HAVE_REGISTERED.code(), ErrorNo.PHONE_HAVE_REGISTERED.msg());
        }
    }

    public void CheckParamLogin(UserInfo userInfo){
        CheckParam(userInfo);
        if(userInfo.getPassword() == null || userInfo.getPassword().length() < 8 || userInfo.getPassword().length() >20){
            logger.error(userInfo.getPhone() + "手机号密码为空或格式不正确");
            throw new ErrorException(ErrorNo.PASSWORD_EMPTY_OR_FORMAT_ERROR.code(), ErrorNo.PASSWORD_EMPTY_OR_FORMAT_ERROR.msg());
        }
    }

    public Integer getUserId(){
        Random random = new Random();
        Integer user_id = null;
        StringBuffer stringBuffer = null;
        while (stringBuffer == null) {
            stringBuffer = new StringBuffer();
            for (int i = 0; i < 8; i++) {
                int num = random.nextInt(10);
                if(i == 0){
                    while(num == 0)num = random.nextInt(10);
                }
                stringBuffer.append(num);
            }
            user_id = Integer.parseInt(new String(stringBuffer));
            if(userInfoMapper.FindUserByUserId(user_id) == null)
                stringBuffer = null;
        }
        return user_id;
    }
}
