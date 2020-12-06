package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.GoodsSpuMapper;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.GoodsSku;
import com.qly.mall.model.GoodsSpu;
import com.qly.mall.model.UserInfo;
import com.qly.mall.util.CheckParamUtil;
import com.qly.mall.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GoodsSpuService {
    static final Logger logger = LoggerFactory.getLogger(GoodsSpuService.class);

    @Autowired
    GoodsSpuMapper goodsSpuMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RandomUtil randomUtil;
    @Autowired
    CheckParamUtil checkParamUtil;

    public Integer CreateGoodsSpu(GoodsSpu goodsSpu, Integer user_id){
        CheckParam(goodsSpu, user_id);
        Integer spuId = randomUtil.getId("goodsSpuMapper");
        goodsSpu.setSpuId(spuId);
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
        if(!user.getStatus().equals(UserInfo.UserType.SUPER)){
            logger.error(user_id + "用户userId没有权限");
            throw new ErrorException(ErrorNo.USER_NOT_PERMISSION.code(), ErrorNo.USER_NOT_PERMISSION.msg());
        }
        if(goodsSpu.getPrice() < 0){
            logger.error("创建价格为" + goodsSpu.getPrice() + "，不应该小于0");
            throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
        }
    }

    public Integer UpdateGoodsSpu(GoodsSpu newGoodsSpu, Integer user_id){
        CheckParam(newGoodsSpu, user_id);
        return goodsSpuMapper.UpdateSpu(newGoodsSpu);
    }

    public GoodsSpu GetGoodsSpuBySpuId(Integer spuId, Integer user_id){
        checkParamUtil.CheckParamUserId(user_id);
        return goodsSpuMapper.GetGoodsSpuBySpuId(spuId);
    }

    public GoodsSpu[] GetGoodsSpuByOffset(Integer offset, Integer user_id){
        checkParamUtil.CheckParamUserId(user_id);
        return goodsSpuMapper.GetGoodsSpuByOffset(offset);
    }

    public List<GoodsSpu[]> GetGoodsSpuByDate(String date, Integer user_id){
        checkParamUtil.CheckParamUserId(user_id);
        String[] nums = date.split("|");
        List<GoodsSpu[]> list = new ArrayList<>();
        for(int i = 0;i < nums.length; i++){
            list.add(goodsSpuMapper.GetGoodsSpuByDate(Integer.parseInt(nums[i])));
        }
        return list;
    }

    public List<GoodsSpu> GetGoodsSpuByStart(String start, Integer user_id){
        checkParamUtil.CheckParamUserId(user_id);
        Integer[] GoodsSpuIds = goodsSpuMapper.FindSpuGoodsByStart(start);
        List<GoodsSpu> list = new ArrayList<>();
        for(Integer spuId: GoodsSpuIds)
            list.add(goodsSpuMapper.FindSpuGoodsBySpuId(spuId));
        return list;
    }

    public List<GoodsSpu> GetGoodsSpuByDesctination(String destination, Integer user_id){
        checkParamUtil.CheckParamUserId(user_id);
        Integer[] GoodsSpuIds = goodsSpuMapper.FindSpuGoodsByDestination(destination);
        List<GoodsSpu> list = new ArrayList<>();
        for(Integer spuId: GoodsSpuIds)
            list.add(goodsSpuMapper.FindSpuGoodsBySpuId(spuId));
        return list;
    }
}
