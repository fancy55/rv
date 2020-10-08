package com.qly.mall.util;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

@Component
public class RandomUtil {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    SubOrdersMapper subOrdersMapper;
    @Autowired
    GoodsSpuMapper goodsSpuMapper;
    @Autowired
    GoodsSkuMapper goodsSkuMapper;
    @Autowired
    RefundsMapper refundsMapper;
    @Autowired
    SubRefundsMapper subRefundsMapper;

    public Integer getId(String choose){
        Random random = new Random();
        Integer id = null;
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
            id = Integer.parseInt(new String(stringBuffer));
            switch (choose) {
                case "userInfoMapper":
                    if (userInfoMapper.FindUserByUserId(id) == null)
                        stringBuffer = null;
                    break;
                case "subOrdersMapper":
                    if(subOrdersMapper.FindSubOrderBySubOrderId(id) == null)
                        stringBuffer = null;
                    break;
                case "ordersMapper":
                    if(ordersMapper.FindOrderByOrderId(id) == null)
                        stringBuffer = null;
                    break;
                case "subRefundsMapper":
                    if(subRefundsMapper.FindSubRefundsBySubRefundId(id) == null)
                        stringBuffer = null;
                    break;
                case "refundsMapper":
                    if(refundsMapper.FindRefundByRefundId(id) == null)
                        stringBuffer = null;
                    break;
                case "goodsSpuMapper":
                    if(goodsSpuMapper.FindSpuGoodsBySpuId(id) == null)
                        stringBuffer = null;
                    break;
                case "goodsSkuMapper":
                    if(goodsSkuMapper.FindSkuGoodsBySkuId(id) == null)
                        stringBuffer = null;
                    break;
                default:
                    throw new ErrorException(ErrorNo.GET_ID_FAIL.code(), ErrorNo.GET_ID_FAIL.msg());
            }
        }
        return id;
    }
}
