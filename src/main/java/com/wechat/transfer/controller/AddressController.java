package com.wechat.transfer.controller;

import com.wechat.transfer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @RequestMapping("/add")
    public boolean addAddress(String name,String phone,String address,String location,Integer type){
        return  addressService.addAddress(name,phone,address,location,type);
    }
}
