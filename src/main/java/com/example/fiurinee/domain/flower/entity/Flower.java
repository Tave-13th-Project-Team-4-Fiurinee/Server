package com.example.fiurinee.domain.flower.entity;

import com.example.fiurinee.domain.recommendFlower.entity.RecommendFlower;
import jakarta.persistence.*;
import lombok.*;

import java.net.URL;
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
    private int period;
    private String flowerLanguage;
    private URL image;
    private String explain;

    @OneToMany(mappedBy = "flower", cascade = CascadeType.ALL)
    private List<RecommendFlower> recommendFlowers;
}
