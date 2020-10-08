package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.OrdersMapper;
import com.qly.mall.mapper.RefundsMapper;
import com.qly.mall.mapper.SubRefundsMapper;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.Orders;
import com.qly.mall.model.Refunds;
import com.qly.mall.model.SubRefunds;
import com.qly.mall.model.UserInfo;
import com.qly.mall.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RefundsService {
    static final Logger logger = LoggerFactory.getLogger(RefundsService.class);

    @Autowired
    RefundsMapper refundsMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RandomUtil randomUtil;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    SubRefundsMapper subRefundsMapper;

    public Integer Refunds(Refunds refunds, SubRefunds[] subRefunds, Integer userId){
        CheckParam(refunds, userId);
        Integer refundsId = randomUtil.getId("refundsMapper");
        refunds.setRefundId(refundsId);
        if(refundsMapper.CreateRefund(refunds) == 1) {
            logger.info(refundsId + "创建refundId成功");
            refundsMapper.UpdateRefunds(refundsId);
            ordersMapper.UpdateOrderStatus(Orders.OrderStatus.REFUND_PROCESS, System.currentTimeMillis(), refunds.getOrderId());
            for (int i = 0; i < subRefunds.length; i++) {
                Integer subRefundsId = randomUtil.getId("subRefundsMapper");
                subRefunds[i].setSubRefundId(subRefundsId);
                if(subRefundsMapper.CreateSubRefund(subRefunds[i]) == 1){
                    logger.info(subRefunds[i].getSubOrderId() + "创建subRefundId成功");
                }else{
                    logger.info(subRefunds[i].getSubOrderId() + "创建subRefundId失败");
                    throw new  ErrorException(ErrorNo.CREATE_SUB_REFUND_FAIL.code(), ErrorNo.CREATE_SUB_REFUND_FAIL.msg());
                }
            }
            ordersMapper.UpdateOrderStatus(Orders.OrderStatus.REFUNDED, System.currentTimeMillis(), refunds.getOrderId());
        }else{
            logger.info(refundsId + "创建refundsId失败");
            throw new  ErrorException(ErrorNo.CREATE_REFUND_FAIL.code(), ErrorNo.CREATE_REFUND_FAIL.msg());
        }
        return refundsId;
    }

    public void CheckParam(Refunds refunds, Integer user_id){
        CheckParamUserId(user_id);
        if(refunds.getRefundAmount() < 0){
            logger.error("退款价格不应该小于0");
            throw new ErrorException(ErrorNo.PARAM_ERROR.code(), ErrorNo.PARAM_ERROR.msg());
        }

    }

    public void CheckParamUserId(Integer user_id){
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
    }

    public Integer CheckParamPhone(String phone){
        Integer userId1 = userInfoMapper.FindUserIdByPhone(phone);
        if(userId1 == null){
            logger.error(phone + "用户phone不存在");
            throw new ErrorException(ErrorNo.USER_NOT_EXIST.code(), ErrorNo.USER_NOT_EXIST.msg());
        }else return userId1;
    }

    public Refunds GetRefundByOrderId(Integer orderId, Integer userId){
        CheckParamUserId(userId);
        return refundsMapper.FindRefundByOrderId(orderId);
    }

    public SubRefunds[] GetSubRefundByOrderId(Integer orderId, Integer userId){
        CheckParamUserId(userId);
        return subRefundsMapper.FindSubRefundByOrderId(orderId);
    }

    public Refunds GetRefundByRefundId(Integer refundId, Integer userId){
        CheckParamUserId(userId);
        return refundsMapper.FindRefundByRefundId(refundId);
    }

    public SubRefunds[] GetSubRefundByRefundIdd(Integer refundId, Integer userId){
        CheckParamUserId(userId);
        return subRefundsMapper.FindSubRefundByRefundId(refundId);
    }

    public SubRefunds GetSubRefundBySubRefundId(Integer subRefundId, Integer userId){
        CheckParamUserId(userId);
        return subRefundsMapper.FindSubRefundBySubRefundId(subRefundId);
    }

    public SubRefunds[] GetSubRefundByUserId(Integer userId1, Integer userId){
        CheckParamUserId(userId);
        return subRefundsMapper.FindSubRefundByUserId(userId1);
    }

    public Refunds[] GetRefundByUserId(Integer userId1,Integer userId){
        CheckParamUserId(userId);
        return refundsMapper.FindRefundByUserId(userId1);
    }

    public SubRefunds[] GetSubRefundByPhone(String phone, Integer userId){
        Integer userId1 = CheckParamPhone(phone);
        CheckParamUserId(userId);
        return subRefundsMapper.FindSubRefundByUserId(userId1);
    }

    public Refunds[] GetRefundByPhone(String phone, Integer userId){
        Integer userId1 = CheckParamPhone(phone);
        CheckParamUserId(userId);
        return refundsMapper.FindRefundByUserId(userId1);
    }
}
