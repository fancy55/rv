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
    String code; //响应码
    String msg;  //响应消息
    Object res;  //响应结果

    public static ResultBody success(Object data){
        return ResultBody.builder()
                .code(ErrEnum.SUCCESS.code())
                .msg(ErrEnum.SUCCESS.msg())
                .res(data)
                .build();
    }

    public static ResultBody fail(BaseErrInterface b){
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
