package com.example.fiurinee.domain.anniversary.controller.api;

import com.example.fiurinee.domain.anniversary.entity.Anniversary;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Tag(name = "Anniversary", description = "기념일 관리 API")
public interface AnniversaryApi {
    @Operation(
            summary = "기념일 추가",
            description = "사용자의 기념일을 추가합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Parameter(
            in = ParameterIn.HEADER,
            name = "Authorization", required = true,
            schema = @Schema(type = "string"),
            description = "Bearer [Access 토큰]"
    )
    @ApiResponse(responseCode = "200", description = "기념일 추가 성공",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Anniversary.class)))
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    @PostMapping("/member/{id}/anniversary")
    ResponseEntity<Anniversary> addAnniversary(@PathVariable Long id, @RequestBody Map<String, String> request);
}
