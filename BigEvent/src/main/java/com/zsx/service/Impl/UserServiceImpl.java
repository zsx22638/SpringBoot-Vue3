package com.zsx.service.Impl;

import com.zsx.mapper.UserMapper;
import com.zsx.pojo.Result;
import com.zsx.pojo.User;
import com.zsx.service.UserService;
import com.zsx.utils.Md5Util;
import com.zsx.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public int saveUser(User user) {
        return userMapper.saveUser(user);
    }



    @Override
    public int updateUser(User user) {
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateUser(user);
    }

    @Override
    public int updateAvatar(String avatar) {
        String updateTime = String.valueOf(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        return userMapper.updateAvatar(avatar,updateTime,id);
    }

    @Override
    public int updatePwd(String oldPwd, String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        User user = userMapper.findUserById(id);
        System.out.println("user:"+user);
        if (user == null){
            return 0;
        }
        //根据获取的用户信息进行判断
        if (user.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            String updateTime = String.valueOf(LocalDateTime.now());
            return userMapper.updatePassword(Md5Util.getMD5String(newPwd),updateTime,id);
        }

        return 0;
    }


}
