package com.lite.api.controller.admin.error;

import com.lite.common.dto.ResultResponse;
import com.lite.common.i18n.SystemMessages;
import com.lite.common.utils.ResultResponseUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 自定义的ErrorController比advice更广泛，advice只能捕获控制层的异常，而ErrorController可以捕获到所有的异常，且重定向到/error
 */
@RestController
public class LiteBlogExceptionController implements ErrorController {

    private static final String STATUS_CODE = "javax.servlet.error.status_code";

    private static final String ERROR_MESSAGE = "javax.servlet.error.message";

    private static final String EXCEPTION_MESSAGE = "javax.servlet.error.exception";


    @RequestMapping("/error")
    public ResultResponse<String> error(HttpServletRequest request) {

        //获取状态码
        Integer status = (Integer) request.getAttribute(STATUS_CODE);

        //获取错误信息
        String msg = (String) request.getAttribute(ERROR_MESSAGE);

        //获取异常信息
        Object exceptionTrace = request.getAttribute(EXCEPTION_MESSAGE);

        //如果没有错误信息就替换异常信息
        if (Strings.isBlank(msg) && !Objects.isNull(exceptionTrace))
            msg = exceptionTrace.toString();

        //如果还不行就取状态码的默认信息
        if (Strings.isBlank(msg)) {
            msg = SystemMessages.getStatusDefaultMsg(status);
        }

        return ResultResponseUtils.error(status, msg);

    }

}
