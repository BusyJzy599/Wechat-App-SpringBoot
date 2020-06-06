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
@Table(name = "logistics")
public class Logistic implements Serializable {
    private Integer id;
    private Integer type;
    private String content;
    private Date createTime;
}
