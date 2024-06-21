package com.example.fiurinee.domain.inputMessage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MentDto {

    private String ment;
    private int month;

    public MentDto(){}

    public MentDto(String ment, int month) {
        this.ment = ment;
        this.month = month;
    }
}
