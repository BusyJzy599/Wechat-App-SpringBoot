package com.wechat.transfer.dao.mapper;

import com.wechat.transfer.entity.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


@Component
public interface UserMapper extends Mapper<User>, MySqlMapper<User> {
}