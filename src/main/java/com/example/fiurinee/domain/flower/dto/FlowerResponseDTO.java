package com.example.fiurinee.domain.flower.dto;

import com.example.fiurinee.domain.flower.entity.Flower;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlowerResponseDTO {
    private String flower;
    private String period;
    private String flower_language;
    private String explain;
    private String image;

    public static FlowerResponseDTO of(Flower flower) {
        return FlowerResponseDTO.builder()
                .flower(flower.getName())
                .period(String.format("%02d", flower.getPeriod() / 100))
                .flower_language(flower.getFlowerLanguage())
                .explain(flower.getExplain())
                .image(flower.getImage().toString())
                .build();
    }
}
