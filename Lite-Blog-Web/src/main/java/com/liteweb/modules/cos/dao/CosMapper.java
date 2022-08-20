package com.liteweb.modules.cos.dao;


import com.liteweb.modules.cos.entity.File;

public interface CosMapper {

    Boolean insertFile(File file);

    File getFile(String fileName);


}
