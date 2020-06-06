package com.wechat.transfer.controller;

import com.wechat.transfer.dao.TransferInfoDao;
import com.wechat.transfer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/init")
public class InitController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MyWareService myWareService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private TransferInfoDao transferInfoDao;

    @RequestMapping("/login")
    public List<Integer> login(String name, String avatar) {
        return userService.addOrUpdate(name, avatar);
    }

    @RequestMapping("/user")
    public List<List<Map<String, Object>>> getUserInfo(Integer userId) {
        List<List<Map<String, Object>>> out = new ArrayList<>();
        List<Map<String, Object>> myAddress = addressService.getMyAddress(userId);
        List<Map<String, Object>> orderInfo = orderService.getOrderInfo(userId);
        List<Map<String, Object>> goods = goodsService.getGoods(userId);
        out.add(myAddress);
        out.add(orderInfo);
        out.add(goods);
        return out;
    }

    @RequestMapping("/seller")
    public List<Map<String, Object>> getSellerInfo(Integer sellerId) {
        List<List<Map<String, Object>>> out = new ArrayList<>();
        List<Map<String, Object>> myWareHouse = myWareService.getMyWareHouse(sellerId);
        return myWareHouse;
    }

    @RequestMapping("/ware")
    public Map<String, Object> getWare() {
        Map<String, Object> out = new HashMap<>();
        List<String> city = transferInfoDao.getCity();
        List<List<Map<String, Object>>> wareHouse = null;
        try {
            wareHouse = transferInfoDao.getWareHouse(city);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.put("city", city);
        out.put("ware", wareHouse);

        return out;
    }

}
