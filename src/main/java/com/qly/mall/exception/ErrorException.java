package com.qly.mall.exception;

import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class ErrorException extends RuntimeException{
    private String ErrorCode;
    private String ErrorMsg;

    public ErrorException(){}

    public ErrorException(String ErrorCode, String ErrorMsg){
        this.ErrorCode = ErrorCode;
        this.ErrorMsg = ErrorMsg;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
