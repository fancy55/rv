package com.qly.mall.mapper;

import com.qly.mall.model.UserInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserInfoMapper {
    @Insert("insert into user_info(user_id,phone,wechat,password,status,nick_name,create_time,update_time) values(#{user_id},#{phone},#{wechat},#{password},1,#{nick_name},now(),now())")
    Integer Register(UserInfo userInfo);

    @Select("select user_id from user where phone = #{phone} and password = #{password}")
    Integer LoginWithPhone(@Param("phone")String phone, @Param("password")String password);

    @Select("select user_id from user where user_id = #{user_id} and password = #{password}")
    Integer LoginWithUserId(@Param("user_id")Integer userId, @Param("password")String password);

    @Update("update user_info set status = 0 where phone = #{phone}")
    void DeleteUserWithPhone(String phone);

    @Update("update user_info set status = 0 where user_id = #{user_id}")
    void DeleteUserWithUserId(Integer user_id);

    @Select("select * from user_info where user_id = #{user_id}")
    UserInfo FindUserByUserId(Integer userId);

    @Select("select * from user_info where phone = #{phone}")
    UserInfo FindUserByPhone(String phone);

    @Select("select phone from user_info where user_id = #{user_id}")
    String FindPhoneByUserId(Integer userId);

    @Select("select user_id from user_info where phone = #{phone}")
    Integer FindUserIdByPhone(String phone);

    @Update("update user_info set password = #{password} where user_id = #{user_id}")
    void UpdatePasswordByUserId(@Param("user_id")Integer userId, @Param("password")String password);

    @Update("update user_info set password = #{password} where phone = #{phone}")
    Integer UpdatePasswordByPhone(@Param("phone")String phone, @Param("password")String password);

    @Update("update user_info set photo = #{photo} where user_id = #{user_id}")
    void UpdatePhotoByUserId(@Param("user_id")Integer userId, @Param("photo")String photo);

    @Update("update user_info set photo = #{photo} where phone = #{phone}")
    void UpdatePhotoByPhone(@Param("phone")String phone, @Param("photo")String photo);

    @Update("update user_info set nick_name = #{nick_name} where phone = #{phone}")
    void UpdateUserByPhone(UserInfo userInfo);
}
