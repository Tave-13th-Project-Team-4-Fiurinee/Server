package com.example.fiurinee.domain.flower.entity;

import com.example.fiurinee.domain.recommendFlower.entity.RecommendFlower;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Flower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flowerId;

    private String name;
    private String period;
    private String flowerLanguage;
    private String image;
    private String explain;

    @OneToMany(mappedBy = "flower", cascade = CascadeType.ALL)
    private List<RecommendFlower> recommendFlowers;
}
