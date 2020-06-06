package com.wechat.transfer.service;

import com.wechat.transfer.dao.mapper.GoodsMapper;
import com.wechat.transfer.entity.Goods;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 添加新的商品
     *
     * @param name
     * @param desc
     * @param avatar
     * @param sellerId
     */
    public void insert(String name, String desc, String avatar, Integer sellerId) {
        Goods goods = new Goods();
        goods.setName(name);
        goods.setDesc(desc);
        goods.setAvatar(avatar);
        goods.setSellerId(sellerId);
        goodsMapper.insert(goods);
    }

    /**
     * 获取我的商品列表
     *
     * @param sellerId
     * @return
     */
    public List<Map<String, Object>> getGoods(Integer sellerId) {
        List<Map<String, Object>> goodsList = new ArrayList<>();
        Example example = new Example(Goods.class);
        example.createCriteria().andEqualTo("sellerId", sellerId);
        List<Goods> list = goodsMapper.selectByExample(example);
        list.forEach((v) -> {
            try {
                goodsList.add(BeanUtils.describe(v));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return goodsList;
    }
}
