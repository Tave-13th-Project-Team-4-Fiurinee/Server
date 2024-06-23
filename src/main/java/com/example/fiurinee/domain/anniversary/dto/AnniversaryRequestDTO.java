package com.example.fiurinee.domain.anniversary.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnniversaryRequestDTO {
    @Size(max = 8)
    private String name;
    private LocalDate date;
    private String type;
}
