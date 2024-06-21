package com.example.fiurinee.domain.inputMessage.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ResponseHarmonyWitnMentDto {

    private String recommendMent;
    private List<ResponseHarmonyDto> harmonyFlowers;

    public ResponseHarmonyWitnMentDto(){}

    public ResponseHarmonyWitnMentDto(String recommendMent, List<ResponseHarmonyDto> harmonyFlowers) {
        this.recommendMent = recommendMent;
        this.harmonyFlowers = harmonyFlowers;
    }
}
