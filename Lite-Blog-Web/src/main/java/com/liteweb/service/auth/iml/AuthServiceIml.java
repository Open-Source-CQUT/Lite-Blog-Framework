package com.liteweb.service.auth.iml;

import com.liteweb.convert.auth.UserConverter;
import com.liteweb.dao.auth.AuthMapper;
import com.liteweb.dto.auth.UserNormalDto;
import com.liteweb.dto.global.ResultResponse;
import com.liteweb.entity.auth.User;
import com.liteweb.service.auth.AuthService;
import com.liteweb.utils.tool.ResultResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceIml implements AuthService {

    @Autowired
    UserConverter userConverter;

    @Autowired
    AuthMapper authMapper;

    @Override
    public ResultResponse<Boolean> login(String mail, String password) {
        return null;
    }

    @Override
    public ResultResponse<Boolean> register(UserNormalDto userNormalDto) {

        //转换成实体类
        User newUser = userConverter.dtoToEntity(userNormalDto);

        if (!authMapper.insertUser(newUser))
            return ResultResponseUtils.success(false,"注册失败");

        return ResultResponseUtils.success(true,"注册成功");
    }
}
