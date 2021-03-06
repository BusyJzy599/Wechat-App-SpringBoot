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
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -1840831686851699943L;
    private Integer id;
    private String name;
    private String avatar;
    private Integer type;
    private Date createTime;
    private Date lastLoginTime;
}
