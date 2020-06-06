package com.wechat.transfer.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 统一属性控制类
 */
@Data
@Configuration
@ConfigurationProperties(prefix = DataSourceProperties.DS, ignoreUnknownFields = false)
public class DataSourceProperties {
    final static String DS = "spring.datasource";

    private Map<String, String> mysql;

    private Map<String, String> hive;

    private Map<String, String> commonConfig;

}