package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.RvUserMapper;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.RvUser;
import com.qly.mall.util.CheckParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RVUserService {
    static final Logger logger = LoggerFactory.getLogger(OrdersService.class);

    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    RvUserMapper rvUserMapper;
    @Autowired
    CheckParamUtil checkParamUtil;

    public Integer CreateRvUser(RvUser rvUser, Integer user_id){
        if(CheckParamRvUser(rvUser, user_id) == 1)return 1;
        Integer addStatus = rvUserMapper.AddRvUser(rvUser);
        Integer offset = rvUserMapper.FindRvUserNumByUserId(rvUser);
        rvUser.setOffset(offset);
        Integer updateStatus = rvUserMapper.UpdateRvUserOffset(rvUser);
        return (addStatus==1 && updateStatus==1)?1:0;
    }

    public void CheckParam(RvUser rvUser, Integer user_id) {
        checkParamUtil.CheckParamUserId(user_id);
        if(rvUser.getUserId() == null || userInfoMapper.FindUserByUserId(rvUser.getUserId()) == null){
            logger.error("userId:" + user_id + "的用户无法操作旅人信息");
            throw new ErrorException(ErrorNo.USER_NOT_EXIST.code(), ErrorNo.USER_NOT_EXIST.msg());
        }
        if(rvUser.getRealName() == null || rvUser.getRvIdCard() == null){
            logger.error("用户无法操作空旅人信息");
            throw new ErrorException(ErrorNo.RV_USER_INFO_ERROR.code(), ErrorNo.RV_USER_INFO_ERROR.msg());
        }
    }

    public Integer CheckParamRvUser(RvUser rvUser, Integer user_id){
        CheckParam(rvUser, user_id);
        RvUser had_rvUser = rvUserMapper.FindRvUserByIdCard(rvUser);
        if(had_rvUser != null){
            if(had_rvUser.getStatus() == 1) {
                logger.error("已经存在此旅人信息" + rvUser + "，不能重复创建");
                throw new ErrorException(ErrorNo.RV_USER_HAS_EXISTING.code(), ErrorNo.RV_USER_HAS_EXISTING.msg());
            }else if(had_rvUser.getStatus() == 0){
                logger.info("已删除的旅人信息重新创建" + rvUser);
                rvUserMapper.UpdateRvUser(rvUser);
                return 1;
            }else{
                logger.error("旅人信息状态参数错误");
                throw new ErrorException(ErrorNo.RV_USER_INFO_ERROR.code(), ErrorNo.RV_USER_INFO_ERROR.msg());
            }
        }
        logger.info(user_id + "进行操作(创建旅人)：" + rvUser);
        return 0;
    }

    public void CheckParamUpdateOrDelete(RvUser rvUser, Integer user_id) {
        CheckParam(rvUser, user_id);
        RvUser had_rvUser = rvUserMapper.FindRvUserByIdCard(rvUser);
        if(had_rvUser == null || had_rvUser.getStatus() == 0){
            logger.error("没有此旅人信息" + rvUser);
            throw new ErrorException(ErrorNo.RV_USER_NOT_EXISTING.code(), ErrorNo.RV_USER_NOT_EXISTING.msg());
        }
        logger.info(user_id + "进行操作：" + rvUser);
    }

    public Integer UpdateRvUser(RvUser rvUser, Integer user_id){
        CheckParamUpdateOrDelete(rvUser, user_id);
        return rvUserMapper.EditRvUser(rvUser);
    }

    public Integer DeleteRvUser(RvUser rvUser, Integer user_id){
        CheckParamUpdateOrDelete(rvUser, user_id);
        return rvUserMapper.DeleteRvUser(rvUser);
    }

    public List<RvUser> GetRvUsersByUserId(Integer user_id){
        checkParamUtil.CheckParamUserId(user_id);
        RvUser[] rvUser = rvUserMapper.FindRvUsersByUserId(user_id);
        List<RvUser> rvUserList = new ArrayList<>();
        for(RvUser user:rvUser){
//            RvUser packUser = new RvUser();
//            packUser.setRealName(user.getRealName());
//            packUser.setIdCard(user.getIdCard());
//            packUser.setOffset(user.getOffset());
            rvUserList.add(user);
        }
        return rvUserList;
    }
}
