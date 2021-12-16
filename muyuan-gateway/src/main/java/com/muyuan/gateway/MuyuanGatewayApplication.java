package com.muyuan.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.discovery.GatewayDiscoveryClientAutoConfiguration;

@SpringBootApplication(exclude = GatewayDiscoveryClientAutoConfiguration.class)
public class MuyuanGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuyuanGatewayApplication.class, args);
    }

}
