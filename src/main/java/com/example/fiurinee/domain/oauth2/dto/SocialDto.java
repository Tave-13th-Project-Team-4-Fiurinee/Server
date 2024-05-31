package com.example.fiurinee.domain.oauth2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SocialDto {
    private String jwtToken;
    private int member_num;
    private String accessToken;
    private String tokenType;
}
