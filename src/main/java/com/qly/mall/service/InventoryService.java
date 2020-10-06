package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.InventoryMapper;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.GoodsSku;
import com.qly.mall.model.GoodsSpu;
import com.qly.mall.model.Inventory;
import com.qly.mall.model.UserInfo;
import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InventoryService {
    static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    @Autowired
    InventoryMapper inventoryMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    public Integer UpdateGoodsStatus(Inventory inventory, Integer user_id){
        CheckParam(inventory, user_id);
        inventory.setUpdateTime(Time.now());
        return inventoryMapper.EditInventoryStatus(inventory);
    }

    public void CheckParam(Inventory inventory, Integer user_id){
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
        logger.info(user_id + "进行操作：" + inventory);
    }
}
