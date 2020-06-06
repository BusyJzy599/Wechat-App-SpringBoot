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
@Table(name = "my_ware")
public class MyWare implements Serializable {
    private Integer id;
    private Integer sellerId;
    private Integer mySize;
    private Integer myAllSize;
    private Date createTime;
    private Date changeTime;


}
