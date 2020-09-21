package com.qly.mall.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;

/**
 * 数据传输
 */
@Builder
@Data
public class ResultBody {
    private String code; //响应码
    private String msg;  //响应消息
    private Object res;  //响应结果

    public static ResultBody success(Object data){
        return ResultBody.builder()
                .code(ErrorNo.SUCCESS.code())
                .msg(ErrorNo.SUCCESS.msg())
                .res(data)
                .build();
    }

    public static ResultBody fail(BaseErrorInterface b){
        return ResultBody.builder()
                .code(b.code())
                .msg(b.msg())
                .res(null)
                .build();
    }

    public static ResultBody fail(String code, String msg){
        return ResultBody.builder()
                .code(code)
                .msg(msg)
                .res(null)
                .build();
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
