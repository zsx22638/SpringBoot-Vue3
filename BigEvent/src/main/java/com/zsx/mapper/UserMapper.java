package com.zsx.mapper;

import com.zsx.pojo.Result;
import com.zsx.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    //通过用户名查询用户信息
    @Select("select * from user where username = #{username}")
    User findUserByName(String username);

    //保存用户信息
    @Insert("insert into user (username,password,nickname,email,user_pic,create_time,update_time)"
            + " values (#{username},#{password},#{nickname},#{email},#{userPic},#{createTime},#{updateTime})")
    int saveUser(User user);

    //通过id查询用户信息
    @Select("select * from user where id = #{id}")
    User findUserById(Integer id);

    //更新用户信息
    @Update("update user set nickname=#{nickname}, email=#{email},  user_pic=#{userPic}, update_time=#{updateTime}" + " where id=#{id}")
    int updateUser(User user);

    //更新用户头像
    @Update("update user set user_pic=#{avatar}, update_time=#{updateTime} where id=#{id}")
    int updateAvatar(String avatar, String updateTime,Integer id);

    //更新用户密码
    @Update("update user set password=#{newPassword}, update_time=#{updateTime} where id=#{id}")
    int updatePassword(String newPassword, String updateTime, Integer id);
}
