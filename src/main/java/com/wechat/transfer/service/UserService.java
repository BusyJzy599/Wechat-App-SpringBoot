package com.wechat.transfer.service;

import com.wechat.transfer.dao.mapper.UserMapper;
import com.wechat.transfer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    /**
     * 添加或更新用户信息
     *
     * @param name
     * @param avatar
     * @return
     */
    public List<Integer> addOrUpdate(String name, String avatar) {
        List<Integer> list = new ArrayList<>();
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name", name);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() > 0) {
            users.get(0).setLastLoginTime(new Date());
            userMapper.updateByExample(users.get(0), example);
            list.add(users.get(0).getId());
            list.add(users.get(0).getType());
        } else {
            User user = new User();
            user.setAvatar(avatar);
            user.setName(name);
            user.setType(0);
            user.setCreateTime(new Date());
            user.setLastLoginTime(new Date());
            userMapper.insert(user);
            Example example1 = new Example(User.class);
            example1.createCriteria().andEqualTo("name", name);
            User u = userMapper.selectOneByExample(example1);
            list.add(u.getId());
            list.add(0);
        }
        return list;
    }

    /**
     * 判断是否为商家
     *
     * @param sellerId
     * @return
     */
    public boolean isSeller(Integer sellerId) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id", sellerId);
        User user = userMapper.selectOneByExample(example);
        if (user == null)
            return false;
        return user.getType() == 1 ? true : false;
    }

    /**
     * 设定为商家
     *
     * @param userId
     * @return
     */
    public boolean setSeller(Integer userId) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id", userId);
        User user = userMapper.selectOneByExample(example);
        if (user == null)
            return false;
        user.setType(1);
        return userMapper.updateByPrimaryKey(user) == 1;
    }
}
