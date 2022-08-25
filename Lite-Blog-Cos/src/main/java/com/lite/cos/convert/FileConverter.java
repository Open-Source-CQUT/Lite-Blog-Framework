package com.lite.cos.convert;

import com.lite.cos.entity.File;
import com.lite.cos.vo.FileVo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileConverter {

    FileVo entityToVo(File file);
}
