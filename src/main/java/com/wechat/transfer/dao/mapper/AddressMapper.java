package com.wechat.transfer.dao.mapper;

import com.wechat.transfer.entity.Address;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

@Component
public interface AddressMapper extends Mapper<Address>, MySqlMapper<Address> {
}
