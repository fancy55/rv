package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.UserInfo;
import com.qly.mall.util.AESUtil;
import com.qly.mall.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class UserInfoService {
    static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);
    static final String key = "qinleyiTestEncodeFancy";

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RandomUtil randomUtil;
    @Autowired
    AESUtil aesUtil;

    public Integer Register(UserInfo userInfo, Integer type){
        if(type < 0 || type > 2)type = 0;
        CheckParamRegister(userInfo, type);
        Integer user_id = randomUtil.getId("userInfoMapper");
        userInfo.setUserId(user_id);
        userInfo.setType(type);
        if(userInfo.getNickName() == null)userInfo.setNickName("用户"+user_id);
        userInfo.setStatus(UserInfo.UserStatus.NORMAL);
        userInfo.setPhoto("http://47.104.191.228:8089/photo/leave.png");//默认头像
        //密码加密
        byte[] encrypt = aesUtil.encrypt(userInfo.getPassword(), key);
        String encrypPassword = aesUtil.parseByte2HexStr(encrypt);
        userInfo.setPassword(encrypPassword);
        if(userInfoMapper.Register(userInfo) == 1) {
            logger.info(userInfo.getPhone() + "注册成功");
        }else{
            logger.info(userInfo.getPhone() + "注册失败");
            throw new ErrorException(ErrorNo.REGISTER_FAIL.code(), ErrorNo.REGISTER_FAIL.msg());
        }
        return user_id;
    }

    public Integer LoginByPhoneAndPassword(UserInfo userInfo){
        CheckParam(userInfo);
        byte[] encrypt = aesUtil.encrypt(userInfo.getPassword(), key);
        Integer user_id = userInfoMapper.LoginWithPhone(userInfo.getPhone(), aesUtil.parseByte2HexStr(encrypt));
        if(user_id == null){
            logger.info(userInfo.getPhone()+"登录失败");
            throw new  ErrorException(ErrorNo.LOGIN_FAIL.code(), ErrorNo.LOGIN_FAIL.msg());
        }else{
            logger.info(userInfo.getPhone()+"登录成功");
        }
        return user_id;
    }

    public Integer AlterPassword(UserInfo userInfo, String newPassword){
        CheckParamPhoneAndPassword(userInfo);
        CheckParamNewPassword(userInfo, newPassword);
        byte[] encrypt = aesUtil.encrypt(newPassword, key);
        Integer user_id = userInfoMapper.UpdatePasswordByPhone(userInfo.getPhone(), aesUtil.parseByte2HexStr(encrypt));
        if(user_id == 0){
            logger.info("更新密码失败");
            throw new  ErrorException(ErrorNo.UPDATE_PASSWORD_FAIL.code(), ErrorNo.UPDATE_PASSWORD_FAIL.msg());
        }else{
            logger.info("更新密码成功");
        }
        return user_id;
    }

    public Integer ForgetPassword(UserInfo userInfo, String newPassword){
        CheckParamPhone(userInfo);
        CheckParamNewPassword(userInfo, newPassword);
        byte[] encrypt = aesUtil.encrypt(newPassword, key);
        Integer user_id = userInfoMapper.UpdatePasswordByPhone(userInfo.getPhone(), aesUtil.parseByte2HexStr(encrypt));
        if(user_id == 0){
            logger.info("更新密码失败");
            throw new  ErrorException(ErrorNo.UPDATE_PASSWORD_FAIL.code(), ErrorNo.UPDATE_PASSWORD_FAIL.msg());
        }else{
            logger.info("更新密码成功");
        }
        return user_id;
    }

    public void CheckParamPhone(UserInfo userInfo){
        if(userInfo.getPhone() == null || userInfo.getPhone().length() != 11) {
            logger.error(userInfo.getPhone() + "手机号码为空或格式不正确");
            throw new ErrorException(ErrorNo.PHONE_EMPTY_OR_FORMAT_ERROR.code(), ErrorNo.PHONE_EMPTY_OR_FORMAT_ERROR.msg());
        }
        if(userInfoMapper.FindUserIdByPhone(userInfo.getPhone()) == null){
            logger.error(userInfo.getPhone() + "手机号不存在");
            throw new ErrorException(ErrorNo.USER_NOT_EXIST.code(), ErrorNo.USER_NOT_EXIST.msg());
        }
    }

    public void CheckParam(UserInfo userInfo){
        CheckParamPhone(userInfo);
        if(userInfo.getPassword() == null || userInfo.getPassword().length() < 8 || userInfo.getPassword().length() >20){
            logger.error(userInfo.getPhone() + "密码为空或格式不正确");
            throw new ErrorException(ErrorNo.PASSWORD_EMPTY_OR_FORMAT_ERROR.code(), ErrorNo.PASSWORD_EMPTY_OR_FORMAT_ERROR.msg());
        }
    }

    public void CheckParamNewPassword(UserInfo userInfo, String newPassword){
        if(newPassword == null || newPassword.length() < 8 || newPassword.length() >20){
            logger.error(userInfo.getPhone() + "密码为空或格式不正确");
            throw new ErrorException(ErrorNo.PASSWORD_EMPTY_OR_FORMAT_ERROR.code(), ErrorNo.PASSWORD_EMPTY_OR_FORMAT_ERROR.msg());
        }
    }

    public void CheckParamRegister(UserInfo userInfo, Integer type){
        CheckParam(userInfo);
        if(userInfoMapper.FindUserIdByPhone(userInfo.getPhone()) != null){
            logger.error(userInfo.getPhone() + "手机号已注册");
            throw new ErrorException(ErrorNo.PHONE_HAVE_REGISTERED.code(), ErrorNo.PHONE_HAVE_REGISTERED.msg());
        }
        /*if(type < 0 || type > 3){
            logger.error(userInfo.getPhone() + "用户类型参数错误");
            throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
        }*/
    }

    public void CheckParamPhoneAndPassword(UserInfo userInfo){
        CheckParam(userInfo);
        byte[] encrypt = aesUtil.encrypt(userInfo.getPassword(), key);
        if(userInfoMapper.LoginWithPhone(userInfo.getPhone(), aesUtil.parseByte2HexStr(encrypt)) == null){
            logger.error(userInfo.getPhone() + "用户不存在");
            throw new ErrorException(ErrorNo.USER_NOT_EXIST.code(), ErrorNo.USER_NOT_EXIST.msg());
        }
    }
}
