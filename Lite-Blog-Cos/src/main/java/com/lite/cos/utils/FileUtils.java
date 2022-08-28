package com.lite.cos.utils;

import com.lite.auth.entity.User;
import com.lite.common.serializer.PasswordEncoder;
import com.lite.common.utils.DateUtils;
import com.lite.common.utils.JwtUtil;
import com.lite.cos.entity.File;
import com.lite.auth.vo.UserTokenVo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class FileUtils {

    public static final String SPLIT_FLAG = ".";

    public static final String BUCKET_FLAG = "bucket-name";

    //通过文件名来获取文件后缀
    public static String getFileTypeByName(String fileName) {
        if (fileName == null) return Strings.EMPTY;

        int index = fileName.lastIndexOf(SPLIT_FLAG);

        return fileName.substring(index);
    }

    //快速将一堆信息打包成一个文件实体类
    public static File wrapperEntity(
            String bucket,
            UserTokenVo userTokenVo,
            String basUrl,
            Boolean access,
            MultipartFile file) {


        //原始文件名
        String originalName = file.getOriginalFilename();

        //获取文件后缀
        String suffix = getFileTypeByName(originalName);

        //uuid，新文件名，为了防止重复
        String uuid = JwtUtil.getUUID();

        //cos对象的key
        String fileKey = String.format("%s/%s/%s", System.currentTimeMillis(), PasswordEncoder.enCode(userTokenVo.getMail()), uuid + suffix);

        //新的url
        String url = basUrl.replace(BUCKET_FLAG, bucket) + fileKey;

        //创建空白实例
        File wrapFile = new File();

        wrapFile.setFileName(fileKey);
        wrapFile.setOriginalName(originalName);
        wrapFile.setType(suffix);
        wrapFile.setBucket(bucket);
        wrapFile.setUrl(url);
        wrapFile.setUploader(userTokenVo.getId());
        wrapFile.setUploadTime(LocalDateTime.now());
        wrapFile.setAccess(access);
        wrapFile.setDeleted(false);

        return wrapFile;
    }
}
