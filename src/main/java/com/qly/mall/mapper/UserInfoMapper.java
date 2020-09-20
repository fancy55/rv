package com.qly.mall.mapper;

import com.qly.mall.model.UserInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserInfoMapper {
    @Insert("insert into user_info(user_id,phone,wechat,password,status,nick_name,create_time,update_time) values(#{user_id},#{phone},#{wechat},#{password},1,#{nick_name},#{create_time},#{update_time})")
    void Register(UserInfo userInfo);

    @Select("select user_id from user where phone = #{phone} and password = #{password}")
    String LoginWithPhone(@Param("phone")String phone, @Param("password")String password);

    @Select("select user_id from user where user_id = #{user_id} and password = #{password}")
    String LoginWithUserId(@Param("user_id")String userId, @Param("password")String password);

    @Update("update user_info set status = 0 where phone = #{phone}")
    void DeleteUserWithPhone(String phone);

    @Update("update user_info set status = 0 where user_id = #{user_id}")
    void DeleteUserWithUserId(String user_id);

    @Select("select * from user where user_id = #{user_id}")
    UserInfo FindUserByUserId(String userId);

    @Select("select * from user where phone = #{phone}")
    UserInfo FindUserByPhone(String phone);

    @Update("update user_info set password = #{password} where user_id = #{user_id}")
    void UpdatePasswordByUserId(@Param("user_id")String userId, @Param("password")String password);

    @Update("update user_info set password = #{password} where phone = #{phone}")
    void UpdatePasswordByPhone(@Param("phone")String phone, @Param("password")String password);

    @Update("update user_info set photo = #{photo} where user_id = #{user_id}")
    void UpdatePhotoByUserId(@Param("user_id")String userId, @Param("photo")String photo);

    @Update("update user_info set photo = #{photo} where phone = #{phone}")
    void UpdatePhotoByPhone(@Param("phone")String phone, @Param("photo")String photo);

    @Update("update user_info set nick_name = #{nick_name} where phone = #{phone}")
    void UpdateUserByPhone(UserInfo userInfo);
}
