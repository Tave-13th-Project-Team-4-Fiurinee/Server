package com.example.fiurinee.domain.jwt.dto;

import java.util.Map;

public record RefreshDto (
        String accessToken,
        String refreshToken
){
}
