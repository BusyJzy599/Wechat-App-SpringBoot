package com.wechat.transfer.service;

import com.wechat.transfer.dao.mapper.AddressMapper;
import com.wechat.transfer.entity.Address;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AddressService {
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 获取我的地址簿信息
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> getMyAddress(Integer userId) {
        List<Map<String, Object>> maps = new ArrayList<>();
        Example example = new Example(Address.class);
        example.createCriteria().andEqualTo("userId", userId);
        List<Address> addresses = addressMapper.selectByExample(example);
        addresses.forEach((v) -> {
            try {
                maps.add(BeanUtils.describe(v));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return maps;
    }

}
