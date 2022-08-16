package com.liteweb.controller;

import com.liteweb.dto.global.ResultResponse;
import com.liteweb.utils.tool.ResultResponseUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义的ErrorController比advice更广泛，advice只能捕获控制层的异常，而ErrorController可以捕获到所有的异常，且重定向到/error
 */
@RestController
public class LiteBlogExceptionController implements ErrorController {

    private static final String STATUS_CODE = "javax.servlet.error.status_code";

    private static final String ERROR_MESSAGE = "javax.servlet.error.message";


    @RequestMapping("/error")
    public ResultResponse<String> error(HttpServletRequest request) {

        Integer status = (Integer) request.getAttribute(STATUS_CODE);

        String msg = (String) request.getAttribute(ERROR_MESSAGE);

        return ResultResponseUtils.error(status, msg);

    }

}
