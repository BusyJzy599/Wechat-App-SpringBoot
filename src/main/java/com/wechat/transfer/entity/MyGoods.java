package com.wechat.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "my_goods")
public class MyGoods implements Serializable {
    private Integer goodsId;
    private Integer wareId;
    private Integer sellerId;
    private Integer number;
    private Date createTime;
    private Date changeTime;
}
