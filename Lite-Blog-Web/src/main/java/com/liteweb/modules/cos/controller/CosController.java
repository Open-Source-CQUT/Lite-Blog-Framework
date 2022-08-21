package com.liteweb.modules.cos.controller;

import com.liteweb.modules.auth.exception.AuthException;
import com.liteweb.modules.common.dto.ResultResponse;
import com.liteweb.modules.cos.exception.CosFileException;
import com.liteweb.modules.cos.service.CosService;
import com.liteweb.modules.cos.vo.FileVo;
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
    public ResultResponse<FileVo> uploadAvatar(@RequestParam("avatar") @NotNull MultipartFile file) throws CosFileException, AuthException {

        //TODO 上传头像
        return cosService.uploadAvatar(file);
    }

    @PutMapping("/upload/public")
    public ResultResponse<FileVo> uploadPublicFile(@RequestParam("file") @NotNull MultipartFile file) throws CosFileException {

        //TODO 上传public文件
        return cosService.uploadPublicFile(file);
    }


    @PutMapping("/upload/private")
    public ResultResponse<FileVo> uploadPrivateFile(@RequestParam("file") @NotNull MultipartFile file) throws CosFileException {

        //TODO 上传private文件
        return cosService.uploadPrivateFile(file);
    }

    @GetMapping("/download/preSigned")
    public ResultResponse<FileVo> getPreSignedDownloadUrl(@RequestParam @NotBlank String url) throws CosFileException {
        return cosService.getPreSignedDownLoadUrl(url);
    }

}
