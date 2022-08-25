package com.lite.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lite.auth.entity.User;

import java.util.Optional;

public interface AuthMapper extends BaseMapper<User> {

    Optional<User> getUser(String mail);

    Boolean insertUser(User user);

    Boolean updateUserPassword(String mail, String newPassword);

    Boolean updateUserInfo(User user);
}
