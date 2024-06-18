package com.example.fiurinee.domain.anniversary.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnniversaryRequestDTO {
    private LocalDate date;
    private String type;
}
