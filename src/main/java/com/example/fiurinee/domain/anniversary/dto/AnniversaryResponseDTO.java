package com.example.fiurinee.domain.anniversary.dto;

import com.example.fiurinee.domain.anniversary.entity.Anniversary;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnniversaryResponseDTO {
    private Long id;
    private String anniversaryDate;
    private String type;
    private List<Map<String, Integer>> dDays;

    public static AnniversaryResponseDTO of(Anniversary anniversary, List<Map<String, Integer>> dDays) {
        return AnniversaryResponseDTO.builder()
                .id(anniversary.getId())
                .anniversaryDate(anniversary.getAnniversaryDate().toString())
                .type(anniversary.getType().name())
                .dDays(dDays)
                .build();
    }

    public static AnniversaryResponseDTO empty() {
        Map<String, Integer> nullDay = new HashMap<>();
        nullDay.put("year", null);

        List<Map<String, Integer>> dDays = new ArrayList<>();
        dDays.add(nullDay);

        return AnniversaryResponseDTO.builder()
                .id(null)
                .anniversaryDate(null)
                .type(null)
                .dDays(dDays)
                .build();
    }
}
