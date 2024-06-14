package com.example.fiurinee.domain.member.data;

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

    @Builder
    private Member(String email, String name, String socialId, Role role) {
        this.email = email;
        this.name = name;
        this.socialId = socialId;
        this.role = role;
    }

    public static Member createMember(String email, String name, String socialId, Role role) {
        return new Member(email, name, socialId, role);
    }
}
