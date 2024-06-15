package com.example.fiurinee.global.api.service;

import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.oauth2.dto.KakaoLogoutDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Transactional
public class CallApiService {

    private final WebClient webClient;

    public CallApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public static ClientResponse checkKakaoToken(String kakaoAccessToken) {

        WebClient webClient = WebClient.builder().build();

        return webClient
                .get()
                .uri("https://kapi.kakao.com/v1/user/access_token_info")
                .header("Authorization", "Bearer " + kakaoAccessToken)
                .exchange()
                .block();
    }

    public static KakaoLogoutDto logoutKakao(Member member, String kakaoAccessToken) {

        WebClient webClient = WebClient.builder().build();

        return webClient
                .post()
                .uri("https://kapi.kakao.com/v1/user/logout")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization", "Bearer " + kakaoAccessToken)
                .retrieve()
                .bodyToMono(KakaoLogoutDto.class)
                .block();



    }

    public static KakaoLogoutDto resignKakao(String kakaoAccessToken) {

        WebClient webClient = WebClient.builder().build();

        return webClient
                .post()
                .uri("https://kapi.kakao.com/v1/user/unlink")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization","Bearer "+kakaoAccessToken)
                .retrieve()
                .bodyToMono(KakaoLogoutDto.class)
                .block();

    }
}
