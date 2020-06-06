package com.wechat.transfer.dao.mapper;

import com.wechat.transfer.entity.Order;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Component
public interface OrderMapper extends Mapper<Order>, MySqlMapper<Order> {
}
