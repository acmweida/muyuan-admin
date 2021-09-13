package com.muyuan.member.infrastructure.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtConfig {

    @Value("${jwt.signingKey}")
    private String signingKey;
}