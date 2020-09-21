package com.qly.mall.util;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.util.HashMap;
import java.util.Map;

public class SmsUtil {
    final String host = "https://feginesms.market.alicloudapi.com";
    final String path = "/codeNotice";
    final String method = "GET";
    final String appcode = "a3b7c145592f4464a5c8fdfe74ba5d0b";

    public void sms(String phone,String code) {  //ok
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("param", code);
        querys.put("phone", phone);
        querys.put("sign", "1");
        querys.put("skin", "1");
        try {
            HttpResponse response = HttpUtil.doGet(host, path, method, headers, querys);
            //System.out.println(response.toString());如不输出json, 请打开这行代码，打印调试头部状态码。
            //状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
            String ans = EntityUtils.toString(response.getEntity());
            //System.out.println(ans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
