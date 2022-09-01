package com.lite.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lite.auth.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

public interface AuthMapper extends BaseMapper<User> {

    Optional<User> getUser(String mail);

    Boolean insertUser(User user);

    Boolean updateUserPassword(@Param("mail") String mail, @Param("newPassword") String newPassword);

    Boolean updateUserInfo(User user);
}
