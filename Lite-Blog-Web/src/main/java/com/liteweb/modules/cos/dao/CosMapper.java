package com.liteweb.modules.cos.dao;


import com.liteweb.modules.cos.entity.File;

import java.util.Optional;

public interface CosMapper {

    Boolean insertFile(File file);

    Optional<File> getFile(String url);


}
