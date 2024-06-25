package com.example.fiurinee.domain.jwt.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConstants {

    public static String key;
    public static final int ACCESS_EXP_TIME = 30;
    public static final int REFRESH_EXP_TIME = 60 * 24;
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_TYPE = "Bearer ";

    @Value("${JWT_SECRET_KEY}")
    private String jwtSecretKey;

    @PostConstruct
    public void init() {
        key = this.jwtSecretKey;
    }
}
