package com.example.fiurinee.domain.anniversary.controller.api;

import com.example.fiurinee.domain.anniversary.dto.AnniversaryRequestDTO;
import com.example.fiurinee.domain.anniversary.dto.AnniversaryResponseDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @ApiResponse(responseCode = "200", description = "기념일 추가 성공")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    @PostMapping("/member/{id}/anniversary")
    ResponseEntity<Anniversary> addAnniversary(@Parameter(name = "id", description = "멤버아이디")@PathVariable Long id, @RequestBody AnniversaryRequestDTO request);

    @Operation(
            summary = "기념일 수정",
            description = "사용자의 기념일을 수정합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Parameter(
            in = ParameterIn.HEADER,
            name = "Authorization", required = true,
            schema = @Schema(type = "string"),
            description = "Bearer [Access 토큰]"
    )
    @ApiResponse(responseCode = "200", description = "기념일 수정 성공")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @ApiResponse(responseCode = "404", description = "기념일 또는 사용자를 찾을 수 없음")
    @PutMapping("/member/{id}/anniversary/{anniversaryId}")
    ResponseEntity<Anniversary> updateAnniversary(@PathVariable Long id, @PathVariable Long anniversaryId, @RequestBody AnniversaryRequestDTO request);

    @Operation(
            summary = "기념일 삭제",
            description = "사용자의 기념일을 삭제합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Parameter(
            in = ParameterIn.HEADER,
            name = "Authorization", required = true,
            schema = @Schema(type = "string"),
            description = "Bearer [Access 토큰]"
    )
    @ApiResponse(responseCode = "204", description = "기념일 삭제 성공")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @ApiResponse(responseCode = "404", description = "기념일 또는 사용자를 찾을 수 없음")
    @DeleteMapping("/member/{id}/anniversary/{anniversaryId}")
    ResponseEntity<Void> deleteAnniversary(@PathVariable Long id, @PathVariable Long anniversaryId);

    @Operation(
            summary = "D-Day가 0인 기념일 조회",
            description = "D-Day가 0인 사용자의 기념일을 조회합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Parameter(
            in = ParameterIn.HEADER,
            name = "Authorization", required = true,
            schema = @Schema(type = "string"),
            description = "Bearer [Access 토큰]"
    )
    @ApiResponse(responseCode = "200", description = "D-Day가 0인 기념일 조회 성공")
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @GetMapping("/member/{id}/anniversary/zero-day")
    ResponseEntity<List<AnniversaryResponseDTO>> getZeroDayAnniversaries(@PathVariable Long id);

}
