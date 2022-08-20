package com.liteweb.modules.auth.convert;

import com.liteweb.modules.auth.convert.rules.GenderRule;
import com.liteweb.modules.auth.dto.user.UserNormalDto;
import com.liteweb.modules.auth.entity.User;
import com.liteweb.modules.auth.vo.UserTokenVo;
import com.liteweb.modules.auth.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {GenderRule.class})
public interface UserConverter {

    UserConverter userMapper = Mappers.getMapper(UserConverter.class);

    @Mappings({})
    UserNormalDto voToNormalDto(UserVo userVo);

    /**
     * Dto 在转换成vo时自动去掉password字段
     *
     * @param userNormalDto DTo
     * @return vo
     */
    @Mappings({
            @Mapping(source = "password", target = "password", ignore = true),
            @Mapping(source = "gender", target = "gender"),
    })
    UserVo normalDtoToVo(UserNormalDto userNormalDto);

    /**
     * Dto转换为实体时自动注入updateTime,createdTime字段
     *
     * @param dto
     * @return
     */
    @Mappings({
            @Mapping(target = "createdTime", expression = "java(com.liteweb.utils.tool.DateUtils.formatNow())"),
            @Mapping(target = "updatedTime", expression = "java(com.liteweb.utils.tool.DateUtils.formatNow())")
    })
    User dtoToEntity(UserNormalDto dto);

    /**
     * 实体转换成dto时，忽略掉 deleted，createTime，updateTime 字段
     *
     * @param user 实体
     * @return dto
     */
    @Mappings({})
    UserNormalDto entityToDto(User user);

    @Mappings({
            @Mapping(target = "createdTime", expression = "java(com.liteweb.utils.tool.DateUtils.formatNow())"),
            @Mapping(target = "updatedTime", expression = "java(com.liteweb.utils.tool.DateUtils.formatNow())")
    })
    User voToEntity(UserVo userVo);

    @Mappings({
            @Mapping(source = "password", target = "password", ignore = true),
            @Mapping(source = "gender", target = "gender"),
    })
    UserVo entityToVo(User user);

    @Mappings({
            @Mapping(source = "gender", target = "gender"),
            @Mapping(target = "loginTime", expression = "java(com.liteweb.utils.tool.DateUtils.formatNow())"),
            @Mapping(target = "uuid", expression = "java(com.liteweb.modules.auth.utils.JwtUtil.getUUID())")
    })
    UserTokenVo entityToTokenVo(User user);

}
