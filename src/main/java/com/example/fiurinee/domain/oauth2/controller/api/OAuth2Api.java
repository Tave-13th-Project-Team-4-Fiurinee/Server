package com.example.fiurinee.domain.oauth2.controller.api;

import com.example.fiurinee.domain.matchingFlower.dto.HarmonyRecentDto;
import com.example.fiurinee.domain.oauth2.dto.KakaoLogoutDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Tag(name = "로그아웃, 회원 탈퇴", description = "로그아웃, 회원 탈퇴 하는 API")
public interface OAuth2Api {
    @Operation(
            summary = "로그아웃 api 입니다",
            description = "로그아웃 이에요",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Parameter(
            in = ParameterIn.HEADER,
            name = "Authorization", required = true,
            schema = @Schema(type = "string"),
            description = "Bearer [Access 토큰]"
    )
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @GetMapping("/member/{id}/logout")
    public ResponseEntity<KakaoLogoutDto> logoutKakao(@Parameter(description = "유저 아이디") @PathVariable("id") Long id, @RequestHeader("Authorization") String accessToken);

    @Operation(
            summary = "회원 탈퇴",
            description = "회원 탈퇴 입니다..",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Parameter(
            in = ParameterIn.HEADER,
            name = "Authorization", required = true,
            schema = @Schema(type = "string"),
            description = "Bearer [Access 토큰]"
    )
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @GetMapping("/member/{id}/resign")
    public ResponseEntity<?> resignKakao(@Parameter(description = "유저 아이디") @PathVariable("id") Long id);

}
