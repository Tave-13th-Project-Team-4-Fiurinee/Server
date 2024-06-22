package com.example.fiurinee.domain.image.controller.api;

import com.example.fiurinee.domain.image.dto.ImageRequestDTO;
import com.example.fiurinee.domain.image.dto.ImageResponseDTO;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URL;
import java.util.Map;

@Tag(name = "Image", description = "프로필 이미지 관리 API")
public interface ImageApi {
    @Operation(
            summary = "프로필 이미지 조회",
            description = "사용자의 프로필 이미지를 조회합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Parameter(
            in = ParameterIn.HEADER,
            name = "Authorization", required = true,
            schema = @Schema(type = "string"),
            description = "Bearer [Access 토큰]"
    )
    @ApiResponse(responseCode = "200", description = "프로필 이미지 조회 성공")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @GetMapping("/member/{id}/image")
    ResponseEntity<ImageResponseDTO> getImage(@PathVariable Long id);

    @Operation(
            summary = "프로필 이미지 수정",
            description = "사용자의 프로필 이미지를 수정합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Parameter(
            in = ParameterIn.HEADER,
            name = "Authorization", required = true,
            schema = @Schema(type = "string"),
            description = "Bearer [Access 토큰]"
    )
    @ApiResponse(responseCode = "204", description = "프로필 이미지 수정 성공")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @PutMapping("/member/{id}/image")
    ResponseEntity<Void> updateImage(@PathVariable Long id, @RequestBody ImageRequestDTO imageRequest);
}
