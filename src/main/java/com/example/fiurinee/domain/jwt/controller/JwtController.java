package com.example.fiurinee.domain.jwt.controller;

import com.example.fiurinee.domain.jwt.controller.api.JwtApi;
import com.example.fiurinee.domain.jwt.dto.RefreshDto;
import com.example.fiurinee.domain.jwt.utils.JwtConstants;
import com.example.fiurinee.domain.jwt.utils.JwtUtils;
import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.member.service.MemberService;
import com.example.fiurinee.global.exception.CustomException;
import com.example.fiurinee.global.redis.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JwtController implements JwtApi {

    private final MemberService memberService;
    private final RedisUtil redisUtil;

    @GetMapping("/member/{id}/refresh")
    public ResponseEntity<?> refresh(@PathVariable("id") Long id, @RequestHeader("Authorization") String authHeader) {
        Member byId = memberService.findById(id);

        String refreshToken = JwtUtils.getTokenFromHeader(authHeader);
        Map<String, Object> claims = JwtUtils.validateToken(refreshToken);

        Object o = redisUtil.get(byId.getEmail());

        if (o == null) {
            throw new CustomException("해당 유저 캐시에 refreshToken이 존재하지 않습니다.");
        }
        String redisRefreshToken = o.toString();

        if (redisRefreshToken.equals(refreshToken)) {
            redisUtil.delete(byId.getEmail());

            String newAccessToken = JwtUtils.generateToken(claims, JwtConstants.ACCESS_EXP_TIME);
            String newRefreshToken = JwtUtils.generateToken(claims, JwtConstants.REFRESH_EXP_TIME);

            redisUtil.set(byId.getEmail(), newRefreshToken,60*24);

            return ResponseEntity.ok(new RefreshDto(newAccessToken, newRefreshToken));
        }

        return ResponseEntity.notFound().build();
    }
}
