package com.example.fiurinee.domain.recommendFlower.entity;

import com.example.fiurinee.domain.flower.entity.Flower;
import com.example.fiurinee.domain.matchingFlower.entity.MatchingFlower;
import com.example.fiurinee.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecommendFlower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendFlowerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flower_id")
    private Flower flower;

    private Boolean prefer;

    @OneToMany(mappedBy = "recommendFlower", cascade = CascadeType.ALL)
    private List<MatchingFlower> matchingFlowers;
}
