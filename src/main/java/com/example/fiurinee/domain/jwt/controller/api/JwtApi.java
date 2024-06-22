package com.example.fiurinee.domain.jwt.controller.api;

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

@Tag(name = "액세스 토큰 재발급", description = "액세스 토큰 기한 만료 됐을 때 호출 하는 API")
public interface JwtApi {
    @Operation(
            summary = "액세스 토큰 만료 시 토큰 재발급을 위한 api",
            description = "여기서는 액세스 토큰을 헤더에 담아서 보내면 안되고 리프레쉬 토큰을 헤더에 담아서 보내야합니다. 그럼 반화 값으로 새로운 액세스 토큰과 리프레쉬 토큰을 드립니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Parameter(
            in = ParameterIn.HEADER,
            name = "Authorization", required = true,
            schema = @Schema(type = "string"),
            description = "Bearer [Refresh 토큰]"
    )
    @ApiResponse(responseCode = "200", description = "성공")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @GetMapping("/member/{id}/refresh")
    public ResponseEntity<?> refresh(@Parameter(description = "유저 아이디") @PathVariable("id") Long id, @Parameter(description = "액세스 토큰 아니에요 리프레쉬 토큰 다른 곳에 저장시키고 있다가 여기에 담아서 보내줘야합니다.") @RequestHeader("Authorization") String authHeader);

}
