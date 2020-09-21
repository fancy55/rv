package com.qly.mall.controller;

import com.qly.mall.util.SmsUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

public class SmsController {
    @Autowired
    private SmsUtil smsUtil;

    @GetMapping("send/{number}")
    @ApiOperation("发送验证码")
    public String cmsSend(@PathVariable String number){
        //随机产生6位验证码
        StringBuffer code = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i<6; i++){
            int j = random.nextInt(10);
            code.append(j);
        }
        smsUtil.sms(number,code.toString());
        return code.toString();
    }
}
