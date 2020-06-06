package com.wechat.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Integer userId;
    private Integer sellerId;
    private Integer goodsId;
    private Integer dest;
    private Integer ware;
    private Integer status;
    private String currLocation;
    private Date createTime;
    private Date changeTime;
}
