package com.wechat.transfer.dao.mapper;

import com.wechat.transfer.entity.Logistic;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Component
public interface LogisticMapper extends Mapper<Logistic>, MySqlMapper<Logistic> {
}
