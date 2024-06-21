package com.example.fiurinee.domain.matchingFlower.controller.api;

import com.example.fiurinee.domain.matchingFlower.dto.HarmonyRecentDto;
import com.example.fiurinee.domain.recommendFlower.dto.RecommendFlowerDto;
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

import java.util.List;

@Tag(name = "어울리는 꽃 조회", description = "유저가 추천 받았던 꽃과 어울리는 꽃의 정보를 조회 하는 API")
public interface MatchingFlowerApi {
    @Operation(
            summary = "마이페이지 들어갔을 때 최근 추천 받은 꽃과 어울리는 꽃 정보 반환 시킵니다",
            description = "최근에 추천 받은 3가지와 어울리는 꽃 6개, 즉 각각 2개씩 반환 시켜줍니다, order가 같은게 두 개씩 있을 거에요 order가 1이라고 치면 추천받은 꽃의 order가 1인 놈과 어울리는 꽃이라고 생각하시면 됩니다.",
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
    @GetMapping("/{id}/harmony/recent")
    public ResponseEntity<List<HarmonyRecentDto>> harmonyRecent(@Parameter(description = "유저 아이디") @PathVariable("id") Long id);

    @Operation(
            summary = "마이페이지에서 자세히 보기 눌렀을 때 추천 받은 꽃과 어울리는 모든 꽃 조회",
            description = "추천 받았던 꽃과 어울리는 꽃 정보 전부 다 보여드립니다.",
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
    @GetMapping("/{id}/harmony")
    public ResponseEntity<List<HarmonyRecentDto>> harmony(@Parameter(description = "유저 아이디") @PathVariable("id") Long id);

    @Operation(
            summary = "유저가 찜 목록만 보기 들어갔을 때 이거 호출하면 됩니다.",
            description = "찜한 꽃 목록에서 어울리는 꽃 정보 반환 해요",
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
    @GetMapping("/{id}/prefer/harmony")
    public ResponseEntity<List<HarmonyRecentDto>> preferHarmony(@Parameter(description = "유저 아이디") @PathVariable("id") Long id);
}
