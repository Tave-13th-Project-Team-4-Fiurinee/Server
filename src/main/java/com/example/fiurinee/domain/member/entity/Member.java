package com.example.fiurinee.domain.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String socialId;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String kakaoAccessToken;

    private int profileImage;

    private boolean alarm;

    @Builder
    private Member(String email, String name, String socialId, Role role, String kakaoAccessToken, int profileImage, boolean alarm) {
        this.email = email;
        this.name = name;
        this.socialId = socialId;
        this.role = role;
        this.kakaoAccessToken = kakaoAccessToken;
        this.profileImage = profileImage;
        this.alarm = alarm;
    }

    public static Member createMember(String email, String name, String socialId, Role role, String kakaoAccessToken, int profileImage, boolean alarm) {
        return new Member(email, name, socialId, role, kakaoAccessToken,profileImage, alarm);
    }

    public void updateKakaoToken(String kakaoAccessToken){
        this.kakaoAccessToken = kakaoAccessToken;
    }
}
