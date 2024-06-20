package com.example.fiurinee.domain.anniversary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnniversaryResponseDTO {
    private Long id;
    private String anniversaryDate;
    private String type;
    private List<Map<String, Integer>> dDays;
}
