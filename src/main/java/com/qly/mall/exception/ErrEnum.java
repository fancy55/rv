package com.qly.mall.exception;

public enum ErrEnum implements BaseErrInterface {
    //数据操作定义
    SUCCESS("200","成功"),
    BODY_NOT_MATCH("400", "空指针异常"),
    NOT_FOUND("404", "未找到资源"),
    EXPIRE_TIME("401", "用户已失效或无token或用户不存在，请重新登录"),
    INTERNAL_SERVER_ERR("500", "服务器内部错误"),
    SERVER_BUSY("503","服务器正忙，请稍后再试")
    ;

    private String resultCode;
    private String resultMsg;

    ErrEnum(String code, String msg){
        this.resultCode = code;
        this.resultMsg = msg;
    }

    @Override
    public String code() {
        return resultCode;
    }

    @Override
    public String msg() {
        return resultMsg;
    }
}
