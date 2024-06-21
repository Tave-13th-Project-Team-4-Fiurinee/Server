package com.example.fiurinee.domain.preferList.controller.api;

import com.example.fiurinee.domain.matchingFlower.dto.HarmonyRecentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "찜하기, 찜 취소", description = "찜과 찜 취소 API")
public interface PreferListApi {
    @Operation(
            summary = "찜하기에요 전에 보내줬던 order 정보를 url에 담아서 보내면 돼요",
            description = "찜하기에요 전에 보내줬던 order 정보를 url에 담아서 보내면 돼요",
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
    @GetMapping("/{id}/{order}")
    public Boolean savePrefer(@Parameter(description = "유저 아이디") @PathVariable("id") Long id,
                              @Parameter(description = "꽃 정보 조회할 때 같이 보내 드렸던 order 입니다.") @PathVariable("order") Long order);

    @Operation(
            summary = "찜 취소에요",
            description = "예 그렇습니다.",
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
    @DeleteMapping("/{id}/{order}")
    public Boolean deletePrefer(@Parameter(description = "유저 아이디") @PathVariable("id") Long id,
                                @Parameter(description = "꽃 정보 조회할 때 같이 보내 드렸던 order 입니다.") @PathVariable("order") Long order);
}
