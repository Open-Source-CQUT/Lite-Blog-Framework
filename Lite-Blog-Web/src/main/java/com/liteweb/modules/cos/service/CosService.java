package com.liteweb.modules.cos.service;

import com.liteweb.modules.auth.exception.AuthException;
import com.liteweb.modules.common.dto.ResultResponse;
import com.liteweb.modules.cos.exception.CosFileException;
import com.liteweb.modules.cos.vo.FileVo;
import org.springframework.web.multipart.MultipartFile;

public interface CosService {

    ResultResponse<FileVo> uploadAvatar(MultipartFile file) throws CosFileException, AuthException;

    ResultResponse<FileVo> uploadPublicFile(MultipartFile file) throws CosFileException;

    ResultResponse<FileVo> uploadPrivateFile(MultipartFile file) throws CosFileException;

    ResultResponse<FileVo> getPreSignedDownLoadUrl(String url) throws CosFileException;

}
