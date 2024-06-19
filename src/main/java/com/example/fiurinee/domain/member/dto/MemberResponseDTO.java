package com.example.fiurinee.domain.member.dto;

import com.example.fiurinee.domain.member.entity.Member;
import lombok.*;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDTO {
    private Long id;
    private String email;
    private String name;
    private int profileImage;
    private List<Map<String, Object>> anniversaries;

    public static MemberResponseDTO of(Member member) {
        return MemberResponseDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .profileImage(member.getProfileImage())
                .anniversaries(member.getAnniversaries().stream()
                        .map(anniversary -> Map.<String, Object>of(
                                "id", anniversary.getId(),
                                "anniversaryDate", anniversary.getAnniversaryDate().toString(),
                                "type", anniversary.getType().name()
                        ))
                        .collect(Collectors.toList()))
                .build();
    }
}
