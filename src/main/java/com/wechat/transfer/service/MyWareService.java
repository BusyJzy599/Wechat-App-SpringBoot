package com.wechat.transfer.service;

import com.wechat.transfer.dao.mapper.MyGoodsMapper;
import com.wechat.transfer.dao.mapper.MyWareMapper;
import com.wechat.transfer.entity.MyGoods;
import com.wechat.transfer.entity.MyWare;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class MyWareService {
    @Autowired
    private MyWareMapper myWareMapper;
    @Autowired
    private MyGoodsMapper myGoodsMapper;

    /**
     * 获取用户仓库信息
     *
     * @param sellerId
     * @return
     */
    public List<Map<String, Object>> getMyWareHouse(Integer sellerId) {
        List<Map<String, Object>> maps = new ArrayList<>();
        Example example = new Example(MyWare.class);
        example.createCriteria().andEqualTo("sellerId", sellerId);
        List<MyWare> myWares = myWareMapper.selectByExample(example);

        Example example1 = new Example(MyGoods.class);
        example1.createCriteria().andEqualTo("sellerId", sellerId);
        List<MyGoods> myGoods = myGoodsMapper.selectByExample(example1);

        Map<Integer, List<Map<String, Object>>> goods = new HashMap<>();
        myGoods.forEach((value) -> {
            List<Map<String, Object>> list = goods.get(value.getWareId());
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(new HashMap<String, Object>() {{
                put("id", value.getGoodsId());
                put("number", value.getNumber());
                put("info", null);
            }});
            goods.put(value.getWareId(), list);
        });

        myWares.forEach((v) -> {

            try {
                Map<String, Object> describe = BeanUtils.describe(v);
                describe.put("ware", goods.get(v.getId()));
                describe.put("time", v.getChangeTime().getTime());
                maps.add(describe);
            } catch (Exception e) {
            }

        });
        return maps;

    }

    /**
     * 租赁新的仓库
     * @param sellerId
     * @param wareId
     * @param size
     * @param time
     * @return
     */
    public boolean hireNewWare(Integer sellerId, Integer wareId, Integer size, Long time) {
        MyWare myWare = new MyWare(wareId, sellerId, 0, size, new Date(), new Date(time));
        return myWareMapper.insert(myWare)==1;
    }
}
