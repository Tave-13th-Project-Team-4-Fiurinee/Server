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

    private Long period;

    @Column(length = 2000)
    private String flowerLanguage;

    @Column(length = 2000)
    private URL image;

    @Column(length = 2000)
    private String explain;

    @OneToMany(mappedBy = "flower", cascade = CascadeType.ALL)
    private List<RecommendFlower> recommendFlowers;

    public void setFlowerId(Long flowerId) {
        this.flowerId = flowerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    public void setFlowerLanguage(String flowerLanguage) {
        this.flowerLanguage = flowerLanguage;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public void setRecommendFlowers(List<RecommendFlower> recommendFlowers) {
        this.recommendFlowers = recommendFlowers;
    }
}
