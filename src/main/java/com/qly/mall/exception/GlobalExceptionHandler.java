package com.qly.mall.exception;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice  //开启全局异常处理
public class GlobalExceptionHandler {
    /**
     * 自定义异常
     */
    @ExceptionHandler(value = ErrorException.class)
    @ResponseBody
    public ResultBody myException(HttpServletRequest req, ErrorException e){
        e.printStackTrace();
        return ResultBody.fail(e.getErrorCode(),e.getErrorMsg());
    }

//    /**
//     * token失效异常
//     */
//    @ExceptionHandler(value = TokenExpiredException.class)
//    @ResponseBody
//    public ResultBody expireException(HttpServletRequest req, TokenExpiredException e){
//        e.printStackTrace();
//        return ResultBody.fail(ErrorEnum.EXPIRE_TIME);
//    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultBody NuLLException(HttpServletRequest req, NullPointerException e){
        e.printStackTrace();
        return  ResultBody.fail(ErrorNo.BODY_NOT_MATCH);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public String MethodException(HttpServletRequest req, HttpRequestMethodNotSupportedException e){
        e.printStackTrace();
        return "err";
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultBody otherException(HttpServletRequest req, Exception e){
        e.printStackTrace();
        return ResultBody.fail(ErrorNo.SERVER_BUSY);
    }
}
