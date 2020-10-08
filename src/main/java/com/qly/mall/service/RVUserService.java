package com.qly.mall.service;

import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.mapper.RvUserMapper;
import com.qly.mall.mapper.UserInfoMapper;
import com.qly.mall.model.RvUser;
import com.qly.mall.model.UserInfo;
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

    public Integer CreateRvUser(RvUser rvUser, Integer user_id){
        CreateCheckParam(rvUser, user_id);
        Integer addStatus = rvUserMapper.AddRvUser(rvUser);
        Integer updateStatus = rvUserMapper.UpdateRvUserNum(rvUser);
        return (addStatus.equals(updateStatus) && updateStatus==1)?1 : 0;
    }

    public void CheckParam(RvUser rvUser, Integer user_id){
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

    public void CreateCheckParam(RvUser rvUser, Integer user_id){
        CheckParam(rvUser, user_id);
        RvUser had_rvUser = rvUserMapper.FindRvUserByIdCard(rvUser);
        if(had_rvUser != null && had_rvUser.getStatus() != 0){
            logger.error("已经存在此旅人信息" + rvUser + "，不能重复创建");
            throw new ErrorException(ErrorNo.RV_USER_HAS_EXISTING.code(), ErrorNo.RV_USER_HAS_EXISTING.msg());
        }
        logger.info(user_id + "进行操作(创建旅客)：" + rvUser);
    }

    public void UpdateOrDeleteCheckParam(RvUser rvUser, Integer user_id) {
        CheckParam(rvUser, user_id);
        RvUser had_rvUser = rvUserMapper.FindRvUserByIdCard(rvUser);
        if(had_rvUser == null || had_rvUser.getStatus() == 0){
            logger.error("没有此旅人信息" + rvUser);
            throw new ErrorException(ErrorNo.RV_USER_NOT_EXISTING.code(), ErrorNo.RV_USER_NOT_EXISTING.msg());
        }
        logger.info(user_id + "进行操作：" + rvUser);
    }

    public Integer UpdateRvUser(RvUser rvUser, Integer user_id){
        UpdateOrDeleteCheckParam(rvUser, user_id);
        return rvUserMapper.EditRvUser(rvUser);
    }

    public Integer DeleteRvUser(RvUser rvUser, Integer user_id){
        UpdateOrDeleteCheckParam(rvUser, user_id);
        return rvUserMapper.DeleteRvUser(rvUser);
    }

    public List<RvUser> GetRvUsersByUserId(Integer user_id){
        CheckParam(null, user_id);
        RvUser[] rvUser = rvUserMapper.FindRvUsersByUserId(user_id);
        List<RvUser> rvUserList = new ArrayList<>();
        for(RvUser user:rvUser){
            RvUser packUser = new RvUser();
            packUser.setRealName(user.getRealName());
            packUser.setIdCard(user.getEncrpyIdCard());
            rvUserList.add(packUser);
        }
        return rvUserList;
    }
}
