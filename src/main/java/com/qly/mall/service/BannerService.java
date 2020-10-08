package com.qly.mall.service;

import com.qly.mall.config.PhotoConfig;
import com.qly.mall.exception.ErrorException;
import com.qly.mall.exception.ErrorNo;
import com.qly.mall.util.CheckParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class BannerService {
    static final Logger logger = LoggerFactory.getLogger(BannerService.class);
    Map<Integer, String> map = new HashMap<>();

    @Autowired
    PhotoConfig photoConfig;
    @Autowired
    CheckParamUtil checkParamUtil;

    public Map<Integer, String> GetBannerURL(@RequestParam("file") MultipartFile files, Integer userId){
        checkParamUtil.CheckParamUserId(userId);
        String uuid = UUID.randomUUID().toString();
        try {
            File f = new File(photoConfig.getPath()+"/"+uuid+photoConfig.getType());
            files.transferTo(f); //把 MultipartFile 转换成 File // getOriginalFilename()得到原来的文件名在客户机的文件系统名称
            map.put(0, photoConfig.getUrl() +"/"+uuid+photoConfig.getType());
        }catch (IOException e){
            throw new ErrorException(ErrorNo.CREATE_Banner_FAIL.code(), ErrorNo.CREATE_Banner_FAIL.msg());
        }
        logger.info(userId + "创建banner成功");
        return map;
    }
}
