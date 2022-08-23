package com.liteweb.modules.cos.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liteweb.modules.cos.entity.File;

import java.util.Optional;

public interface CosMapper extends BaseMapper<File> {

    Boolean insertFile(File file);

    Optional<File> getFile(String url);


}
