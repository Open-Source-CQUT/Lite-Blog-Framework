package com.lite.api.controller.publics.cos;

import com.lite.common.dto.ResultResponse;
import com.lite.auth.exception.AuthException;
import com.lite.common.i18n.SystemMessages;
import com.lite.common.utils.ResultResponseUtils;
import com.lite.cos.exception.CosFileException;
import com.lite.cos.service.CosService;
import com.lite.cos.vo.FileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/cos")
@Validated
public class CosController {


    @Autowired
    CosService cosService;

    @PutMapping("/upload/avatar")
    public ResultResponse<FileVo> uploadAvatar(@RequestParam("avatar") @NotNull MultipartFile file)
            throws CosFileException, AuthException {

        //TODO 上传头像
        return ResultResponseUtils.success(
                cosService.uploadAvatar(file), SystemMessages.get("success.cos.avatar.upload"));
    }

    @PutMapping("/upload/public")
    public ResultResponse<FileVo> uploadPublicFile(@RequestParam("file") @NotNull MultipartFile file)
            throws CosFileException {

        //TODO 上传public文件
        return ResultResponseUtils.success(
                cosService.uploadPublicFile(file), SystemMessages.get("success.cos.upload"));
    }


    @PutMapping("/upload/private")
    public ResultResponse<FileVo> uploadPrivateFile(@RequestParam("file") @NotNull MultipartFile file)
            throws CosFileException {

        //TODO 上传private文件

        return ResultResponseUtils.success(
                cosService.uploadPrivateFile(file), SystemMessages.get("success.cos.upload"));
    }

    @GetMapping("/download/preSigned")
    public ResultResponse<FileVo> getPreSignedDownloadUrl(@RequestParam @NotBlank String url)
            throws CosFileException {

        return ResultResponseUtils.success(cosService.getPreSignedDownLoadUrl(url), SystemMessages.get("success.cos.generateURL"));
    }

}
