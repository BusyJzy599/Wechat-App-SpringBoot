package com.wechat.transfer.dao.mapper;

import com.wechat.transfer.entity.MyGoods;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Component
public interface MyGoodsMapper extends Mapper<MyGoods>, MySqlMapper<MyGoods> {
}
