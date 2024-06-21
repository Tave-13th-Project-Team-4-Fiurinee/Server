package com.example.fiurinee.domain.flower.controller.api;

import com.example.fiurinee.domain.flower.dto.FlowerResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Tag(name = "Flower", description = "꽃 관련 API")
@RequestMapping("/main")
public interface FlowerApi {
    @Operation(
            summary = "시즌 꽃 조회",
            description = "현재 시즌 꽃을 조회합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "시즌 꽃 조회 성공",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Map.class)))
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @GetMapping("/season")
    ResponseEntity<List<FlowerResponseDTO>> getSeasonFlowers();

    @Operation(
            summary = "오늘의 꽃 조회",
            description = "오늘의 꽃을 조회합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponse(responseCode = "200", description = "오늘의 꽃 조회 성공",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Map.class)))
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @GetMapping("/today")
    ResponseEntity<FlowerResponseDTO> getTodayFlower();
}
