package com.muyuan.common.mybatis.config;

import com.muyuan.common.mybatis.jdbc.multi.JdbcConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "user.jdbc")
public class UserJdbcConfig extends JdbcConfig {

    public static final String DATASOURCE_NAME = "user";
}
