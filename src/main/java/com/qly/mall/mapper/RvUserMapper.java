package com.qly.mall.mapper;

import com.qly.mall.model.RvUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RvUserMapper {
    @Insert("insert into rv_user_info(user_id,rv_id_card,real_name,create_time,update_time,version,status,offset,phone) values(#{userId},#{rvIdCard},#{realName},now(),now(),0,1,0,#{phone})")
    Integer AddRvUser(RvUser rvUser);

    @Update("update rv_user_info set real_name=#{realName},rv_id_card=#{rvIdCard},update_time=now(),version=version+1 where user_id=#{userId} and offset=#{offset}")
    Integer EditRvUser(RvUser rvUser);

    @Update("update rv_user_info set real_name=#{realName},update_time=now(),version=version+1,status=1 where user_id=#{userId} and rv_id_card=#{rvIdCard}")
    Integer UpdateRvUser(RvUser rvUser);

    @Update("update rv_user_info set status=0 where user_id=#{userId} and rv_id_card=#{rvIdCard}")
    Integer DeleteRvUser(RvUser rvUser);//软删除

    @Update("update rv_user_info set offset=#{offset} where user_id=#{userId} and rv_id_card=#{rvIdCard}")
    Integer UpdateRvUserOffset(RvUser rvUser);

    @Select("select * from rv_user_info where user_id=#{userId} and rv_id_card=#{rvIdCard}")
    RvUser FindRvUserByIdCard(RvUser rvUser);

    @Select("select * from rv_user_info where user_id=#{userId} and status=1 order by create_time desc")
    RvUser[] FindRvUsersByUserId(Integer userId);

    @Select("select count(rv_id_card) from rv_user_info where user_id=#{userId}")
    Integer FindRvUserNumByUserId(RvUser rvUser);
}
