package com.example.fiurinee.domain.recommendFlower.controller.api;

import com.example.fiurinee.domain.inputMessage.dto.RequestMentDto;
import com.example.fiurinee.domain.inputMessage.dto.ResponseHarmonyWitnMentDto;
import com.example.fiurinee.domain.inputMessage.dto.ResponseMentDto;
import com.example.fiurinee.domain.recommendFlower.dto.RecommendFlowerDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "추천 꽃 조회", description = "유저가 추천 받았던 꽃을 조회하는 API")
public interface RecommendFlowerApi {
    @Operation(
            summary = "마이페이지 들어갔을 때 최근 추천 받은 꽃 반환 시킵니다",
            description = "최근에 추천 받은 3가지만 반환 시켜주고 어울리는 꽃 정보는 다른 api에서 받아야합니다. prefer이 true인지 false인지로 찜한 건지 아닌지 판단 가능해요",
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
    @GetMapping("/{id}/recommend/recent")
    public ResponseEntity<List<RecommendFlowerDto>> responseRecommendFlowerRecent(@Parameter(description = "유저 아이디") @PathVariable("id") Long id);

    @Operation(
            summary = "마이페이지에서 자세히 보기 눌렀을 때 모든 추천 받은 꽃 조회",
            description = "추천 받았던 꽃 정보 어울리는 꽃 빼고 전부 다 보여드립니다. 최신 순이에요",
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
    @GetMapping("/{id}/recommend")
    public ResponseEntity<List<RecommendFlowerDto>> responseRecommendFlower(@Parameter(description = "유저 아이디") @PathVariable("id") Long id);

    @Operation(
            summary = "유저가 찜 목록만 보기 들어갔을 때 이거 호출하면 됩니다.",
            description = "찜한 꽃 목록에서 어울리는 꽃 제외한 나머지 모든 정보 반환 해요",
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
    @GetMapping("/{id}/prefer/recommend")
    public ResponseEntity<List<RecommendFlowerDto>> preferFlower(@Parameter(description = "유저 아이디") @PathVariable("id") Long id);
}
