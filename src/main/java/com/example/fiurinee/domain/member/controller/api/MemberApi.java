package com.example.fiurinee.domain.member.controller.api;

import com.example.fiurinee.domain.member.dto.MemberResponseDTO;
import com.example.fiurinee.domain.member.entity.Member;
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

@Tag(name = "Member", description = "멤버 조회 API")
public interface MemberApi {
    @Operation(
            summary = "멤버 조회",
            description = "ID로 멤버를 조회합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @Parameter(
            in = ParameterIn.HEADER,
            name = "Authorization", required = true,
            schema = @Schema(type = "string"),
            description = "Bearer [Access 토큰]"
    )
    @ApiResponse(responseCode = "200", description = "멤버 조회 성공",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Member.class)))
    @ApiResponse(responseCode = "401", description = "인증 실패")
    @ApiResponse(responseCode = "404", description = "멤버를 찾을 수 없음")
    @GetMapping("/member/{id}")
    ResponseEntity<MemberResponseDTO> getMemberById(@PathVariable("id") Long id);
}
