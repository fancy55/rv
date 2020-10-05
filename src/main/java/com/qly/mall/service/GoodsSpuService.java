package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.GoodsSpuMapper;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.GoodsSpu;
import com.qly.mall.model.UserInfo;
import com.qly.mall.util.RandomUtil;
import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GoodsSpuService {
    static final Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    @Autowired
    GoodsSpuMapper goodsSpuMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RandomUtil randomUtil;

    public Integer CreateGoodsSpu(GoodsSpu goodsSpu, Integer user_id){
        CheckParam(goodsSpu, user_id);
        Integer spuId = randomUtil.getId("goodsSpuMapper");
        goodsSpu.setSpuId(spuId);
        goodsSpu.setCreateTime(Time.now());
        goodsSpu.setUpdateTime(Time.now());
        return goodsSpuMapper.AddSpu(goodsSpu);
    }

    public void CheckParam(GoodsSpu goodsSpu, Integer user_id){
        if(user_id == null) {
            logger.error(user_id + "参数userId错误");
            throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
        }
        UserInfo user = userInfoMapper.FindUserByUserId(user_id);
        if(user == null){
            logger.error(user_id + "用户userId不存在");
            throw new ErrorException(ErrorNo.USER_NOT_EXIST.code(), ErrorNo.USER_NOT_EXIST.msg());
        }
        if(!user.getUserStatus().equals(UserInfo.UserType.SUPER)){
            logger.error(user_id + "用户userId没有权限");
            throw new ErrorException(ErrorNo.USER_NOT_PERMISSION.code(), ErrorNo.USER_NOT_PERMISSION.msg());
        }
        if(goodsSpu.getPrice() < 0){
            logger.error("创建价格为" + goodsSpu.getPrice() + "，不应该小于0");
            throw new ErrorException(ErrorNo.USER_NOT_PERMISSION.code(), ErrorNo.USER_NOT_PERMISSION.msg());
        }
    }
}
