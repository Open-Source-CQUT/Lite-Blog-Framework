package com.liteweb.modules.cos.convert;

import com.liteweb.modules.cos.entity.File;
import com.liteweb.modules.cos.vo.FileVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileConverter {

    FileVo entityToVo(File file);
}
