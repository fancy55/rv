package com.qly.mall.controller;

import com.qly.mall.service.BannerService;
import com.qly.mall.service.GoodsSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@Api("上传图片生成链接")
@RequestMapping("banner")
public class BannerController {
    @Autowired
    BannerService bannerService;

    @PostMapping("create")
    @ApiOperation("生成banner链接")
    public String CreateBanner(@ApiParam(name="MultipartFile",value="banner",required=true) MultipartFile file,
                                @RequestParam @ApiParam(name="Integer",value="userId",required=true)Integer userId){
        return bannerService.GetBannerURL(file, userId).get(0);
    }
}
