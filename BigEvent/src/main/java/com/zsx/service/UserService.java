package com.zsx.service;

import com.zsx.pojo.Result;
import com.zsx.pojo.User;

public interface UserService {
    User findUserByName(String username);
    int saveUser(User user);

    int updateUser(User user);

    int updateAvatar(String avatar);

    int updatePwd(String oldPwd, String newPwd);
}
