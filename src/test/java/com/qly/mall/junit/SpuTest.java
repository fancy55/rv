package com.qly.mall.junit;

import com.qly.mall.model.GoodsSpu;
import com.qly.mall.service.GoodsSpuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

public class SpuTest extends JunitBaseTest{
    @Autowired
    GoodsSpuService goodsSpuService;

    @Test
    @Transactional   //标明此方法需使用事务
    @Rollback(false)  //标明使用完此方法后事务不回滚,true时为回滚
    public void test(){
//        System.out.println("===========单元测试-spu=============");
//        GoodsSpu goodsSpu = new GoodsSpu();
//        goodsSpu.setBanner();
//        goodsSpu.setCateType();
//        goodsSpu.setStartPos();
//        goodsSpu.setDestinationPos();
//        goodsSpu.setPrice();
//        goodsSpu.setRoute();
//        goodsSpu.setSpecial();
//        goodsSpu.setSites();
//        goodsSpu.setTips();
//        goodsSpuService.CreateGoodsSpu(goodsSpu, null);
//
//        goodsSpuService.
    }
}
