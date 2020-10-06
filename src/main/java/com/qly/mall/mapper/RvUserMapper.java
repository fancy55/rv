package com.qly.mall.mapper;

import com.qly.mall.model.RvUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RvUserMapper {
    @Insert("insert into rv_user_info(user_id,rv_id_card,encrpy_rv_id_card,real_name,create_time,update_time,version,status) values(#{user_id},#{rv_id_card},#{encrpy_rv_id_card},#{real_name},#{create_time},#{update_time},#{version},status=#{status})")
    Integer AddRvUser(RvUser rvUser);

    @Update("update rv_user_info set real_name=#{real_name},rv_id_card=#{rv_id_card},encrpy_rv_id_card=#{encrpy_rv_id_card},update_time=#{update_time},version=version+1 where user_id=#{user_id} and num=#{num}")
    Integer EditRvUser(RvUser rvUser);

    @Update("update rv_user_info set status=0 where user_id=#{user_id}")
    Integer DeleteRvUser(RvUser rvUser);//软删除

    @Update("update rv_user_info set num=num+1 where user_id=#{user_id}")
    Integer UpdateRvUserNum(RvUser rvUser);

    @Select("select * from rv_user_info where userId=#{userId} and encrpy_rv_id_card=#{encrpy_rv_id_card}")
    RvUser FindRvUserByIdCard(RvUser rvUser);

    @Select("select * from rv_user_info where userId=#{userId} desc by create_time")
    RvUser[] FindRvUsersByUserId(Integer userId);
}
