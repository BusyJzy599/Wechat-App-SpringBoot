package com.wechat.transfer.controller;

import com.wechat.transfer.dao.TransferInfoDao;
import com.wechat.transfer.service.GoodsService;
import com.wechat.transfer.service.MyWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MyWareService myWareService;
    @Autowired
    private TransferInfoDao transferInfoDao;
    @RequestMapping("/data")
    public Map<String, Object>  getGoodsPie(){
        Map<String, Object>out=new HashMap<>();
        out.put("pie",transferInfoDao.getGoodsPie());
        out.put("days",transferInfoDao.getGoodsDays());
        out.put("sort",transferInfoDao.getGoodsSort());
        return out;
    }

}
