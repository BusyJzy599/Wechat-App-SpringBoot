package com.wechat.transfer.service;

import com.wechat.transfer.dao.mapper.LogisticMapper;
import com.wechat.transfer.dao.mapper.OrderMapper;
import com.wechat.transfer.entity.Logistic;
import com.wechat.transfer.entity.Order;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private LogisticMapper logisticMapper;

    /**
     * 获取订单信息
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> getOrderInfo(Integer userId) {
        List<Map<String, Object>> maps = new ArrayList<>();
        Example example = new Example(Order.class);
        example.createCriteria().andEqualTo("userId", userId);
        List<Order> orders = orderMapper.selectByExample(example);
        //获取订单id
        ArrayList<Integer> orderId = new ArrayList<>();
        orders.forEach((v) -> {
            orderId.add(v.getId());
        });
        //获取各订单物流信息
        Map<Integer, List> map = new HashMap<>();
        orderId.forEach((v) -> {
            Example example1 = new Example(Logistic.class);
            example1.createCriteria().andEqualTo("id", v);
            List<Logistic> logistics = logisticMapper.selectByExample(example1);
            /*-------------------解析数据-------------------------*/
            List<Map<String, Object>> mapList = new ArrayList<>();
            logistics.forEach((v1) -> {
                mapList.add(new HashMap<String, Object>() {{
                    put("type", v1.getType());
                    put("content", v1.getContent());
                    put("time", v1.getCreateTime().getTime());
                }});
            });
            map.put(v, mapList);
        });
        //装配信息
        orders.forEach((v) -> {
            try {
                Map<String, Object> describe = BeanUtils.describe(v);
                describe.put("logistics", map.get(v.getId()));
                describe.put("create_time", v.getCreateTime().getTime());
                describe.put("change_time", v.getChangeTime().getTime());
                maps.add(describe);
            } catch (Exception e) {
            }
        });

        return maps;
    }
}
