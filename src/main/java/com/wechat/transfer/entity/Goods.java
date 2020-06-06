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
@Table(name = "goods")
public class Goods implements Serializable {
    /**
     * 主键
     */
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;
    private String avatar;
    private String desc;
    private Integer sellerId;
    private Date createTime;
}
