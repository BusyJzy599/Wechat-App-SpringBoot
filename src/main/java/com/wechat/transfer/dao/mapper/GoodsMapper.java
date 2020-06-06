package com.wechat.transfer.dao.mapper;

import com.wechat.transfer.entity.Goods;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Component
public interface GoodsMapper extends Mapper<Goods>, MySqlMapper<Goods> {
}
