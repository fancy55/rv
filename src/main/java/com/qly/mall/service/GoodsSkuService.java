package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.GoodsSkuMapper;
import com.qly.mall.mapper.GoodsSpuMapper;
import com.qly.mall.mapper.InventoryMapper;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.GoodsSku;
import com.qly.mall.model.GoodsSpu;
import com.qly.mall.model.Inventory;
import com.qly.mall.model.UserInfo;
import com.qly.mall.util.RandomUtil;
import io.swagger.models.auth.In;
import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsSkuService {
    static final Logger logger = LoggerFactory.getLogger(GoodsSkuService.class);

    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RandomUtil randomUtil;
    @Autowired
    InventoryMapper inventoryMapper;

    public Integer CreateGoodsSku(GoodsSku goodsSku, Inventory inventory, Integer user_id){
        CheckParam(goodsSku, user_id);
        Integer skuId = randomUtil.getId("goodsSkuMapper");
        goodsSku.setSkuId(skuId);
        goodsSku.setCreateTime(Time.now());
        goodsSku.setUpdateTime(Time.now());
        inventory.setSkuId(skuId);
        inventory.setUpdateTime(Time.now());
        inventory.setCreateTime(Time.now());
        Integer addSkuStatus = goodsSkuMapper.AddSku(goodsSku);
        Integer addInvStatus = inventoryMapper.AddInventory(inventory);
        return (addSkuStatus.equals(addInvStatus) && addSkuStatus==1)?1 : 0;
    }

    public void CheckParam(GoodsSku goodsSku, Integer user_id){
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
        if(goodsSku.getCurrentPrice() >= goodsSku.getOriginPrice() || goodsSku.getOriginPrice() < 0){
            logger.error("创建价格为" + goodsSku.getOriginPrice() + "，不应该小于0");
            throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
        }
    }

    public Integer UpdateGoodsSku(GoodsSku newGoodsSku, Integer user_id){
        CheckParam(newGoodsSku, user_id);
        newGoodsSku.setUpdateTime(Time.now());
        return goodsSkuMapper.UpdateSpu(newGoodsSku);
    }

    public Integer UpdateGoodsSkuStatus(GoodsSku goodsSku, Integer user_id){
        CheckParam(goodsSku, user_id);
        goodsSku.setUpdateTime(Time.now());
        return goodsSkuMapper.EditSkuStatus(goodsSku);
    }
}
