package com.lite.cos.service;

import com.lite.common.dto.ResultResponse;
import com.lite.cos.vo.FileVo;
import com.lite.auth.exception.AuthException;
import com.lite.cos.exception.CosFileException;
import org.springframework.web.multipart.MultipartFile;

public interface CosService {

    FileVo uploadAvatar(MultipartFile file) throws CosFileException, AuthException;

    FileVo uploadPublicFile(MultipartFile file) throws CosFileException;

    FileVo uploadPrivateFile(MultipartFile file) throws CosFileException;

    FileVo getPreSignedDownLoadUrl(String url) throws CosFileException;

}
