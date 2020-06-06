package com.wechat.transfer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WareHouse implements Serializable {
    private Integer id;
    private String name;
    private String address;
    private String location;
    private String phone;
    private Integer wareSize;
}
