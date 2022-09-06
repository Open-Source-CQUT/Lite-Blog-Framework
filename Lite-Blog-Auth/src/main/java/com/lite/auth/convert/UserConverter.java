package com.lite.auth.convert;

import com.lite.auth.convert.rules.GenderRule;
import com.lite.auth.dto.UserNormalDto;
import com.lite.auth.entity.User;
import com.lite.auth.vo.UserTokenVo;
import com.lite.auth.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {GenderRule.class})
public interface UserConverter {

    UserConverter USER_MAPPER = Mappers.getMapper(UserConverter.class);

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


    User dtoToEntity(UserNormalDto dto);

    /**
     * 实体转换成dto时，忽略掉 deleted，createTime，updateTime 字段
     *
     * @param user 实体
     * @return dto
     */
    @Mappings({})
    UserNormalDto entityToDto(User user);

   @Mappings({})
    User voToEntity(UserVo userVo);

    @Mappings({
            @Mapping(source = "password", target = "password", ignore = true),
            @Mapping(source = "gender", target = "gender"),
    })
    UserVo entityToVo(User user);

    @Mappings({
            @Mapping(source = "gender", target = "gender"),
            @Mapping(target = "loginTime", expression = "java(com.lite.common.utils.DateUtils.formatNow())"),
            @Mapping(target = "uuid", expression = "java(com.lite.common.utils.JwtUtil.getUUID())")
    })
    UserTokenVo entityToTokenVo(User user);

}
