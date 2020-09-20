package com.qly.mall.exception;

import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class ErrException extends RuntimeException{
    String ECode;
    String EMsg;

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
