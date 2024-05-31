package com.example.fiurinee.domain.oauth2.service;

import com.example.fiurinee.domain.oauth2.dto.GoogleDto;
import com.example.fiurinee.domain.oauth2.dto.GoogleInfoDto;
import com.example.fiurinee.domain.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class Oauth2Service {
    @Value("${spring.google.client_id}")
    String clientId;

    @Value("${spring.google.client_secret}")
    String clientSecret;

    @Transactional
    @RequestMapping(value="/api/v1/oauth2/google", method = RequestMethod.GET)
    public String getGoogleInfo(String authCode){

        RestTemplate restTemplate = new RestTemplate();

        GoogleDto.GoogleRequest googleOAuthRequestParam = GoogleDto.GoogleRequest
                .builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .code(authCode)
                .redirectUri("http://localhost:8080/login/oauth2/code/google")
                .grantType("authorization_code")
                .build();

        ResponseEntity<GoogleDto.GoogleResponse> response = restTemplate.postForEntity("https://oauth2.googleapis.com/token",
                googleOAuthRequestParam, GoogleDto.GoogleResponse.class);

        String jwtToken=response.getBody().getId_token();
        ResponseEntity<GoogleInfoDto> infoResponse = restTemplate.getForEntity("https://oauth2.googleapis.com/tokeninfo?id_token=" + jwtToken, GoogleInfoDto.class);

        String email=infoResponse.getBody().getEmail();

        log.info("이메일 "+ email);
        return email;
    }
}


