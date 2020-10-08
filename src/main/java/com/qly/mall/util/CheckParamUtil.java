package com.qly.mall.util;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.GoodsSkuMapper;
import com.qly.mall.mapper.InventoryMapper;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckParamUtil {
    static final Logger logger = LoggerFactory.getLogger(CheckParamUtil.class);

    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RandomUtil randomUtil;
    @Autowired
    InventoryMapper inventoryMapper;

    public Integer CheckParamPhone(String phone){
        Integer userId1 = userInfoMapper.FindUserIdByPhone(phone);
        if(userId1 == null){
            logger.error(phone + "用户phone不存在");
            throw new ErrorException(ErrorNo.USER_NOT_EXIST.code(), ErrorNo.USER_NOT_EXIST.msg());
        }
        return userId1;
    }

    public void CheckParamUserId(Integer user_id) {
        if (user_id == null) {
            logger.error(user_id + "参数userId错误");
            throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
        }
        UserInfo user = userInfoMapper.FindUserByUserId(user_id);
        if (userInfoMapper.FindUserByUserId(user_id) == null) {
            logger.error(user_id + "用户userId不存在");
            throw new ErrorException(ErrorNo.USER_NOT_EXIST.code(), ErrorNo.USER_NOT_EXIST.msg());
        }
        if (!user.getUserType().equals(UserInfo.UserType.SUPER)) {
            logger.error(user_id + "用户userId没有权限");
            throw new ErrorException(ErrorNo.USER_NOT_PERMISSION.code(), ErrorNo.USER_NOT_PERMISSION.msg());
        }
    }
}
