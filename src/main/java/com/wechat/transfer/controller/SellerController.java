package com.wechat.transfer.controller;

import com.wechat.transfer.dao.TransferInfoDao;
import com.wechat.transfer.service.MyWareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private MyWareService myWareService;
    @Autowired
    private TransferInfoDao transferInfoDao;

    @RequestMapping("/analyse")
    public Map<String, List<Object>> getAnalyse() {
        Map<String, List<Object>> data = transferInfoDao.getAnalyseData();
        return data;
    }

    @RequestMapping("/hire")
    public boolean hireWare(Integer sellerId, Integer wareId, Integer size, Long time) {
        return myWareService.hireNewWare(sellerId, wareId, size, time);
    }
}
