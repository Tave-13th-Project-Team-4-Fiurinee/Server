package com.example.fiurinee.domain.recommendComment.entity;

import com.example.fiurinee.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecommendComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    private String content;
    private Boolean prefer;
}

