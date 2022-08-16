package com.liteweb.dao.auth;

import com.liteweb.entity.auth.User;

import java.util.Optional;

public interface AuthMapper {

    Optional<User> getUser(String mail);

    Boolean insertUser(User user);

    Boolean updateUserPassword(String mail, String newPassword);
}
