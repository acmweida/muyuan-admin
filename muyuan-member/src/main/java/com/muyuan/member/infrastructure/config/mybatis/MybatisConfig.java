package com.muyuan.member.infrastructure.config.mybatis;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan("com.muyuan.member.infrastructure.persistence.dao")
public class MybatisConfig {

    @Bean
    public DataSource dataSource(MemberJdbcConfig jdbcConfig) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(jdbcConfig.getDriverClassName());
        dataSource.setJdbcUrl(jdbcConfig.getUrl());
        dataSource.setUsername(jdbcConfig.getUsername());
        dataSource.setPassword(jdbcConfig.getPassword());
        dataSource.setMaximumPoolSize(32);
        dataSource.setMinimumIdle(8);
        return dataSource;
    }
}
