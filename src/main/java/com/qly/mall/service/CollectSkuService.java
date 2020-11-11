package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.CollectSkuMapper;
import com.qly.mall.mapper.GoodsSkuMapper;
import com.qly.mall.model.CollectSku;
import com.qly.mall.model.GoodsSku;
import com.qly.mall.util.CheckParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectSkuService {
    static final Logger logger = LoggerFactory.getLogger(CollectSkuService.class);

    @Autowired
    CheckParamUtil checkParamUtil;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    CollectSkuMapper collectSkuMapper;

    public Integer CollectorGoodsSku(Integer skuId, Integer userId){
        checkParamUtil.CheckParamUserId(userId);
        Integer status = CheckSkuId(skuId, userId);
        if(status == -1)
            return collectSkuMapper.CollectorGoodsSku(skuId, userId);
        return collectSkuMapper.UpdateCollectorGoodsSku(skuId, userId);
    }

    public Integer CancelCollectorGoodsSku(Integer skuId, Integer userId){
        checkParamUtil.CheckParamUserId(userId);
        Integer status = CheckSkuId(skuId, userId);
        if(status == 1)
            return collectSkuMapper.CancelCollectorGoodsSku(skuId, userId);
        return -1;
    }

    public Integer CheckSkuId(Integer skuId, Integer userId){
        GoodsSku goodsSku = goodsSkuMapper.FindSkuGoodsBySkuId(skuId);
        if(goodsSku == null){
            logger.error("商品sku" + skuId + "不存在");
            throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
        }
        CollectSku collectSku = collectSkuMapper.FindCollectBySkuIdAndUserId(skuId, userId);
        if(collectSku!=null)
            return collectSku.getStatus();
        return -1;
    }
}
