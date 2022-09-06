package com.lite.cos.service.impl;

import com.lite.common.i18n.SystemMessages;
import com.lite.cos.config.CosConfig;
import com.lite.cos.convert.FileConverter;
import com.lite.cos.entity.File;
import com.lite.cos.service.CosService;
import com.lite.cos.utils.CosUtils;
import com.lite.cos.vo.FileVo;
import com.lite.auth.exception.AuthException;
import com.lite.auth.service.AuthService;
import com.lite.auth.utils.LiteBlogContextUtils;
import com.lite.auth.vo.UserTokenVo;
import com.lite.auth.vo.UserVo;
import com.lite.cos.dao.CosMapper;
import com.lite.cos.exception.CosFileException;
import com.lite.cos.utils.FileUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.transfer.TransferManager;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Objects;


@Service
public class CosServiceImpl implements CosService {

    @Autowired
    CosConfig cosConfig;

    @Autowired
    CosMapper cosMapper;
    @Autowired
    FileConverter fileConverter;

    @Autowired
    AuthService authService;

    @Autowired
    LiteBlogContextUtils contextUtils;

    @Override
    public FileVo uploadAvatar(MultipartFile file) throws CosFileException, AuthException {

        //结果
        FileVo fileVo = this.uploadPublicFile(file);

        UserTokenVo userTokenVo = contextUtils.getLocalUserInfo();

        UserVo userVo = new UserVo();
        userVo.setMail(userTokenVo.getMail());
        userVo.setAvatar(fileVo.getUrl());

        //更新数据库中的用户头像字段
        if (!authService.updateUserInfo(userVo)) {
            throw new CosFileException(HttpStatus.INTERNAL_SERVER_ERROR.value(), SystemMessages.get("error.cos.avatar.upload"));
        }

        return fileVo;
    }

    @Override
    public FileVo uploadPublicFile(MultipartFile file) throws CosFileException {
        return doUploadService(file, true);
    }

    @Override
    public FileVo uploadPrivateFile(MultipartFile file) throws CosFileException {
        return doUploadService(file, false);
    }


    @Override
    public FileVo getPreSignedDownLoadUrl(String url) throws CosFileException {

        //数据库查找信息
        File file = cosMapper.getFile(url).orElseGet(File::new);

        //如果文件不存在
        if (Strings.isBlank(file.getFileName())) {
            throw new CosFileException(HttpStatus.BAD_REQUEST.value(), SystemMessages.get("error.cos.invalidURL"));
        }

        if (file.getAccess()) {
            throw new CosFileException(HttpStatus.BAD_REQUEST.value(), SystemMessages.get("error.cos.accessIsTrue"));
        }


        //创建COSClient
        COSClient cosClient = CosUtils.initCosClient(cosConfig);

        //生成URL
        URL downloadUrl = CosUtils.generatePreDownloadUrl(cosClient, file);

        //非空校验
        if (Objects.isNull(downloadUrl)) {
            throw new CosFileException(HttpStatus.INTERNAL_SERVER_ERROR.value(), SystemMessages.get("error.cos.generateURL"));
        }

        //设置url
        file.setUrl(downloadUrl.toString());

        //返回信息
        return fileConverter.entityToVo(file);
    }


    /**
     * @param file       待上传的文件对象
     * @param fileAccess 文件的可访问权限 true是公有访问，false是私有访问
     * @return 带有文件信息的响应体
     * @throws CosFileException cos异常，抛出将被全局异常处理器拦截
     */
    @Transactional(rollbackFor = Exception.class)
    public FileVo doUploadService(MultipartFile file, Boolean fileAccess) throws CosFileException {

        //获取用户信息
        UserTokenVo tokenVo = contextUtils.getLocalUserInfo();

        //cos传输对象
        TransferManager transferManager = CosUtils.initTransferManager(cosConfig);

        //获取桶名
        String bucket = fileAccess ? cosConfig.getPublicBucket() : cosConfig.getPrivateBucket();

        //包装对象
        File wrapFile = FileUtils.wrapperEntity(bucket,
                tokenVo, cosConfig.getBaseUrl(), fileAccess, file);

        //上传至cos，获取同步上传结果
        UploadResult uploadResult = CosUtils.uploadFile(transferManager, bucket, wrapFile.getFileName(), file);

        //上传成功则将信息包装存入数据库
        if (Strings.isBlank(uploadResult.getCrc64Ecma()) || !cosMapper.insertFile(wrapFile)) {
            throw new CosFileException(HttpStatus.INTERNAL_SERVER_ERROR.value(), SystemMessages.get("error.cos.upload"));
        }

        return fileConverter.entityToVo(wrapFile);
    }

}
