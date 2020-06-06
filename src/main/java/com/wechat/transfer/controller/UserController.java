package com.wechat.transfer.controller;

import com.wechat.transfer.service.OrderService;
import com.wechat.transfer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @RequestMapping("/seller")
    public boolean setSeller(Integer userId) {
        return userService.setSeller(userId);
    }

}
