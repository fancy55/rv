package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Transactional
public class UserInfoService {
    static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    UserInfoMapper userInfoMapper;

    public Integer Register(UserInfo userInfo){
        CheckParamRegister(userInfo);
        Integer user_id = getUserId();
        if(userInfoMapper.Register(userInfo) == 1) {
            logger.info(userInfo.getPhone() + "注册成功");
        }else{
            logger.info(userInfo.getPhone() + "注册失败");
            throw new  ErrorException(ErrorNo.REGISTER_FAIL.code(), ErrorNo.REGISTER_FAIL.msg());
        }
        return user_id;
    }

    public Integer LoginByPhoneAndPassword(UserInfo userInfo){
        CheckParam(userInfo);
        Integer user_id = userInfoMapper.LoginWithPhone(userInfo.getPhone(),userInfo.getPassword());
        if(user_id == null){
            logger.info("登录失败");
            throw new  ErrorException(ErrorNo.LOGIN_FAIL.code(), ErrorNo.LOGIN_FAIL.msg());
        }else{
            logger.info("登录成功");
        }
        return user_id;
    }

    public Integer AlterPassword(UserInfo userInfo, String newPassword){
        CheckParamPhoneAndPassword(userInfo);
        Integer user_id = userInfoMapper.UpdatePasswordByPhone(userInfo.getPhone(), newPassword);
        if(user_id == 0){
            logger.info("更新密码失败");
            throw new  ErrorException(ErrorNo.UPDATE_PASSWORD_FAIL.code(), ErrorNo.UPDATE_PASSWORD_FAIL.msg());
        }else{
            logger.info("更新密码成功");
        }
        return user_id;
    }

    public void CheckParam(UserInfo userInfo){
        if(userInfo.getPhone() == null || userInfo.getPhone().length() != 11) {
            logger.error(userInfo.getPhone() + "手机号码为空或格式不正确");
            throw new ErrorException(ErrorNo.PHONE_EMPTY_OR_FORMAT_ERROR.code(), ErrorNo.PHONE_EMPTY_OR_FORMAT_ERROR.msg());
        }
        if(userInfo.getPassword() == null || userInfo.getPassword().length() < 8 || userInfo.getPassword().length() >20){
            logger.error(userInfo.getPhone() + "密码为空或格式不正确");
            throw new ErrorException(ErrorNo.PASSWORD_EMPTY_OR_FORMAT_ERROR.code(), ErrorNo.PASSWORD_EMPTY_OR_FORMAT_ERROR.msg());
        }
    }

    public void CheckParamPhone(UserInfo userInfo){
        CheckParam(userInfo);
        if(userInfoMapper.FindUserIdByPhone(userInfo.getPhone()) == null){
            logger.error(userInfo.getPhone() + "手机号不存在");
            throw new ErrorException(ErrorNo.PHONE_HAVE_REGISTERED.code(), ErrorNo.PHONE_HAVE_REGISTERED.msg());
        }
    }

    public void CheckParamRegister(UserInfo userInfo){
        CheckParam(userInfo);
        if(userInfoMapper.FindUserIdByPhone(userInfo.getPhone()) != null){
            logger.error(userInfo.getPhone() + "手机号已注册");
            throw new ErrorException(ErrorNo.PHONE_HAVE_REGISTERED.code(), ErrorNo.PHONE_HAVE_REGISTERED.msg());
        }
    }

    public void CheckParamPhoneAndPassword(UserInfo userInfo){
        CheckParam(userInfo);
        if(userInfoMapper.LoginWithPhone(userInfo.getPhone(), userInfo.getPassword()) == null){
            logger.error(userInfo.getPhone() + "用户不存在");
            throw new ErrorException(ErrorNo.USER_NOT_EXIST.code(), ErrorNo.USER_NOT_EXIST.msg());
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
