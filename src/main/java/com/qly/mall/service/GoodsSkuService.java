package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.GoodsSkuMapper;
import com.qly.mall.mapper.GoodsSpuMapper;
import com.qly.mall.mapper.InventoryMapper;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.GoodsSku;
import com.qly.mall.model.Inventory;
import com.qly.mall.util.CheckParamUtil;
import com.qly.mall.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GoodsSkuService {
    static final Logger logger = LoggerFactory.getLogger(GoodsSkuService.class);

    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    GoodsSpuMapper goodsSpuMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RandomUtil randomUtil;
    @Autowired
    InventoryMapper inventoryMapper;
    @Autowired
    CheckParamUtil checkParamUtil;

    public Integer CreateGoodsSku(GoodsSku goodsSku, Inventory inventory, Integer user_id){
        CheckParam(goodsSku, user_id);
        Integer skuId = randomUtil.getId("goodsSkuMapper");
        goodsSku.setSkuId(skuId);
        inventory.setSkuId(skuId);
        Integer addSkuStatus = goodsSkuMapper.AddSku(goodsSku);
        Integer addInvStatus = inventoryMapper.AddInventory(inventory);
        return (addSkuStatus.equals(addInvStatus) && addSkuStatus==1)?1 : 0;
    }

    public void CheckParam(GoodsSku goodsSku, Integer user_id){
        checkParamUtil.CheckParamUserId(user_id);
        if(goodsSku.getCurrentPrice() >= goodsSku.getOriginPrice() || goodsSku.getOriginPrice() < 0){
            logger.error("创建价格为" + goodsSku.getOriginPrice() + "，不应该小于0");
            throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
        }
    }

    public Integer UpdateGoodsSku(GoodsSku newGoodsSku, Integer user_id){
        CheckParam(newGoodsSku, user_id);
        return goodsSkuMapper.UpdateSpu(newGoodsSku);
    }

    public GoodsSku GetGoodsSkuBySkuId(Integer skuId, Integer user_id){
        checkParamUtil.CheckParamUserId(user_id);
        GoodsSku goodsSku = goodsSkuMapper.GetGoodsSkuBySkuId(skuId);
        Inventory inventory = inventoryMapper.FindInventoryBySkuId(skuId);
        goodsSku.setInv(inventory.getInv());
        goodsSku.setCap(inventory.getCap());
        goodsSku.setStatus(inventory.getStatus());
        return goodsSku;
    }

    public GoodsSku[] GetGoodsSkuBySpuId(Integer spuId, Integer user_id){
        checkParamUtil.CheckParamUserId(user_id);
        return goodsSkuMapper.GetGoodsSkuBySpuId(spuId);
    }

    public GoodsSku[] GetGoodsSkuByOffset(Integer offset, Integer spuId, Integer user_id){
        checkParamUtil.CheckParamUserId(user_id);
        GoodsSku[] goodsSkus = goodsSkuMapper.GetGoodsSkuByOffset(spuId, offset);
        for(GoodsSku goodsSku:goodsSkus){
            Inventory inventory = inventoryMapper.FindInventoryBySkuId(goodsSku.getSkuId());
            goodsSku.setInv(inventory.getInv());
            goodsSku.setCap(inventory.getCap());
            goodsSku.setStatus(inventory.getStatus());
        }
        return goodsSkus;
    }

    public GoodsSku[] GetGoodsSkuByOffsetWithoutSpuId(Integer offset, Integer user_id){
        checkParamUtil.CheckParamUserId(user_id);
        GoodsSku[] goodsSkus = goodsSkuMapper.GetGoodsSkuByOffsetWithoutSpuId(offset);
        for(GoodsSku goodsSku:goodsSkus){
            Inventory inventory = inventoryMapper.FindInventoryBySkuId(goodsSku.getSkuId());
            goodsSku.setInv(inventory.getInv());
            goodsSku.setCap(inventory.getCap());
            goodsSku.setStatus(inventory.getStatus());
        }
        return goodsSkus;
    }
}
