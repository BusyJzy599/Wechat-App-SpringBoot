package com.wechat.transfer.dao;

import com.wechat.transfer.dao.lmpl.HiveJdbcDaoImpl;
import com.wechat.transfer.entity.WareHouse;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Repository
public class TransferInfoDao extends HiveJdbcDaoImpl {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    /**
     * 获取所有的城市
     *
     * @return
     */
    public List<String> getCity() {
        ListOperations<String, String> forList = stringRedisTemplate.opsForList();
        if (forList.size("city") > 0)
            return forList.range("city", 0, forList.size("city") - 1);
        String sql = "select city from new_infor group by city";
        List<String> citys = new ArrayList<>();
        List<Map<String, Object>> maps = this.getJdbcTemplate().queryForList(sql);
        for (int i = 0; i < maps.size(); i++) {
            maps.get(i).forEach((k, v) -> {
                citys.add(v.toString());
                forList.rightPush("city", v.toString());
            });
        }
        return citys;
    }

    /**
     * 获取所有仓库信息
     *
     * @param city
     * @return
     */
    public List<List<Map<String, Object>>> getWareHouse(List<String> city) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //仓库信息
        List<List<Map<String, Object>>> wares = new ArrayList<>();
        for (int j = 0; j < city.size(); j++) {
            String value = city.get(j);
            List<Map<String, Object>> ware = new ArrayList<>();
            //如果缓存里有
            if (redisCacheTemplate.opsForList().size("city_" + j) > 0) {
                List<Serializable> range = redisCacheTemplate.opsForList().range("city_" + j, 0, 10);
                for (int k = 0; k < range.size(); k++) {
                    WareHouse w = (WareHouse) range.get(k);
                    ware.add(BeanUtils.describe(w));
                }
                wares.add(ware);
                continue;
            }
            //如果缓存内不存在则查询hive
            String sql = "select * from new_infor where city=='" + value + "'";
            List<Map<String, Object>> maps = this.getJdbcTemplate().queryForList(sql);
            for (int i = 0; i < maps.size(); i++) {
                Map<String, Object> wareHouse = maps.get(i);
                //new一个数组用于储存每个市的中转仓
                Integer id = (Integer) wareHouse.get("new_infor.id");
                String name = (String) wareHouse.get("new_infor.name");
                String address = (String) wareHouse.get("new_infor.address");
                String location = (String) wareHouse.get("new_infor.location");
                String phone = (String) wareHouse.get("new_infor.phone");
                Integer wareSize = (Integer) wareHouse.get("new_infor.size");
                WareHouse wareHouse1 = new WareHouse(id, name, address, location.substring(1, 20), phone, wareSize);
                redisCacheTemplate.opsForList().rightPush("city_" + j, wareHouse1);
                ware.add(BeanUtils.describe(wareHouse1));
            }
            wares.add(ware);
        }

        return wares;
    }

    /**
     * 获取圆盘数据
     *
     * @return
     */
    public Map<Object, Object> getGoodsPie() {
        Map<Object, Object> out = new HashMap<>();
        if (redisCacheTemplate.opsForHash().size("pie") > 0) {
            Map<Object, Object> pie = redisCacheTemplate.opsForHash().entries("pie");
            return pie;
        }
        synchronized (this) {
            String sql = "select type,count(*) as sum from order_infor group by type";
            List<Map<String, Object>> maps = this.getJdbcTemplate().queryForList(sql);
            maps.forEach((v) -> {
                out.put(v.get("type").toString(), v.get("sum"));
                redisCacheTemplate.opsForHash().put("pie", v.get("type").toString(), v.get("sum"));
            });
        }
        return out;
    }

    /**
     * 获取每日订单数量
     *
     * @return
     */
    public Map<String, List<Integer>> getGoodsDays() {
        Map<String, List<Integer>> out = new HashMap<String, List<Integer>>() {{
            put("口罩", new ArrayList<>(31));
            put("消毒液", new ArrayList<>(31));
            put("笔记本", new ArrayList<>(31));
            put("纸巾", new ArrayList<>(31));
            put("除湿袋", new ArrayList<>(31));
        }};
        if (stringRedisTemplate.opsForHash().size("days") > 0) {
            Map<Object, Object> days = stringRedisTemplate.opsForHash().entries("days");
            out.clear();
            days.forEach((k, v) -> {
                String s = k.toString();
                String s1 = v.toString();
                String[] split = s1.substring(1, s1.length() - 2).split(",");
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < split.length; i++)
                    list.add(Integer.parseInt(split[i].trim()));
                out.put(s, list);

            });
            return out;
        }
        synchronized (this) {
            String sql = "select type,day, count(*)as number from order_infor  group by type,day";
            List<Map<String, Object>> maps = this.getJdbcTemplate().queryForList(sql);
            List<Integer> list = new ArrayList<>(31);
            maps.forEach((v) -> {
                System.out.println(v);
                out.get(v.get("type").toString()).add(Integer.parseInt(v.get("number").toString()));
            });
            out.forEach((k, v) -> {
                stringRedisTemplate.opsForHash().put("days", k, v.toString());
            });
        }

        return out;
    }

    /**
     * 获取排名
     *
     * @return
     */
    public Map<String, List<Integer>> getGoodsSort() {
        Map<String, List<Integer>> out = new HashMap<String, List<Integer>>() {{
            put("口罩", new ArrayList<>(5));
            put("消毒液", new ArrayList<>(5));
            put("笔记本", new ArrayList<>(5));
            put("纸巾", new ArrayList<>(5));
            put("除湿袋", new ArrayList<>(5));
        }};
        if (stringRedisTemplate.opsForHash().size("sort") > 0) {
            Map<Object, Object> sort = stringRedisTemplate.opsForHash().entries("sort");
            out.clear();
            sort.forEach((k, v) -> {
                String s = k.toString();
                String s1 = v.toString();
                String[] split = s1.substring(1, s1.length() - 2).split(",");
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < split.length; i++)
                    list.add(Integer.parseInt(split[i].trim()));
                out.put(s, list);

            });
            return out;
        }
        synchronized (this) {
            String sql = "select b.province,a.type,count(*)as n from order_infor a right join (select province,count(*) as num from order_infor group by province order by num desc limit 5)b on b.province=a.province group by b.province,a.type ";
            List<Map<String, Object>> maps = this.getJdbcTemplate().queryForList(sql);
            maps.forEach((v) -> {
                out.get(v.get("a.type").toString()).add(Integer.parseInt(v.get("n").toString()));
            });
            out.forEach((k, v) -> {
                stringRedisTemplate.opsForHash().put("sort", k, v.toString());
            });
        }
        return out;
    }

    /**
     * 获取分析的数据
     *
     * @return
     */
    public Map<String, List<Object>> getAnalyseData() {
        ListOperations<String, Serializable> redisList = redisCacheTemplate.opsForList();
        Map<String, List<Object>> map = new HashMap<String, List<Object>>() {{
            put("city", new ArrayList<>());
            put("num", new ArrayList<>());
        }};
        if (redisList.size("analyse_city") > 0 || redisList.size("analyse_data") > 0) {
            List<Serializable> analyse_city = redisList.range("analyse_city", 0, 4);
            analyse_city.forEach((v) -> {
                map.get("city").add(v.toString());
            });
            List<Serializable> analyse_data = redisList.range("analyse_data", 0, 4);
            analyse_data.forEach((v) -> {
                map.get("num").add(v.toString());
            });
            return map;
        }
        synchronized (this) {
            String sql = "select province as city,count(*) as num from order_infor group by province order by num desc limit 5";
            List<Map<String, Object>> maps = this.getJdbcTemplate().queryForList(sql);
            maps.forEach((v) -> {
                String city = v.get("city").toString();
                String num = v.get("num").toString();
                map.get("city").add(city);
                map.get("num").add(num);
                redisList.rightPush("analyse_city", city);
                redisList.rightPush("analyse_data", Integer.parseInt(num));
            });
        }
        return map;
    }



}

