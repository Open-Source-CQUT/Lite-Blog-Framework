package com.liteweb.modules.auth.dao;

import com.liteweb.modules.auth.entity.User;

import java.util.Optional;

public interface AuthMapper {

    Optional<User> getUser(String mail);

    Boolean insertUser(User user);

    Boolean updateUserPassword(String mail, String newPassword);

    Boolean updateUserInfo(User user);
}
