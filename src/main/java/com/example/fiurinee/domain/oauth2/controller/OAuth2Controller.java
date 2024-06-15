package com.example.fiurinee.domain.oauth2.controller;

import com.example.fiurinee.domain.jwt.utils.JwtUtils;
import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.member.service.MemberService;
import com.example.fiurinee.domain.oauth2.dto.KakaoLogoutDto;
import com.example.fiurinee.global.api.service.CallApiService;
import com.example.fiurinee.global.redis.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OAuth2Controller {

    private final MemberService memberService;
    private final RedisUtil redisUtil;

    @GetMapping("/member/logout/{id}")
    public ResponseEntity<KakaoLogoutDto> logoutKakao(@PathVariable("id") Long id, @RequestHeader("Authorization") String accessToken){
        Member byId = memberService.findById(id);

        ClientResponse clientResponse = CallApiService.checkKakaoToken(byId.getKakaoAccessToken());

        //redis refreshtoken 테이블에서 refreshtoken 삭제 해야함.

        //redis를 통한 블랙리스트 등록
        redisUtil.setBlackList(JwtUtils.getTokenFromHeader(accessToken), "accessToken", 30);

        if(clientResponse.statusCode() == HttpStatusCode.valueOf(401)) {
            return ResponseEntity.ok(new KakaoLogoutDto(1L));
        }

        return ResponseEntity.ok(CallApiService.logoutKakao(byId, byId.getKakaoAccessToken()));
    }

    @GetMapping("/member/resign/{id}")
    public ResponseEntity<?> resignKakao(@PathVariable("id") Long id){
        Member byId = memberService.findById(id);

        ClientResponse clientResponse = CallApiService.checkKakaoToken(byId.getKakaoAccessToken());

        //만약 카카오톡 액세스 토큰이 만료됐다면
        if(clientResponse.statusCode() == HttpStatusCode.valueOf(401)) {
            return ResponseEntity.status(HttpStatusCode.valueOf(440)).build();
            //440 스테이터스 코드가 반환됐다면 카카오 로그인창으로 리다이렉트 시켜서 액세스 토큰을 재발급 받아야 함.
        }

        KakaoLogoutDto kakaoLogoutDto = CallApiService.resignKakao(byId.getKakaoAccessToken());

        memberService.deleteMember(id);

        return ResponseEntity.ok(kakaoLogoutDto);
    }
}
