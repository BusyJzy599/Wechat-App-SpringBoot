package com.wechat.transfer.dao.mapper;

import com.wechat.transfer.entity.MyWare;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Component
public interface MyWareMapper extends Mapper<MyWare>, MySqlMapper<MyWare> {
}
