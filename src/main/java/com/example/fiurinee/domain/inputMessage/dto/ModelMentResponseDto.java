package com.example.fiurinee.domain.inputMessage.dto;

import lombok.Getter;

@Getter
public class ModelMentResponseDto {

    private String name;
    private String flowerLanguage;

    public ModelMentResponseDto(){}

    public ModelMentResponseDto(String name, String flowerLanguage) {
        this.name = name;
        this.flowerLanguage = flowerLanguage;
    }
}
