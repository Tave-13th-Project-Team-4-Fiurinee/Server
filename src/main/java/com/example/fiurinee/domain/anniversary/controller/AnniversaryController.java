package com.example.fiurinee.domain.anniversary.controller;

import com.example.fiurinee.domain.anniversary.entity.Anniversary;
import com.example.fiurinee.domain.anniversary.service.AnniversaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
public class AnniversaryController {
    @Autowired
    private AnniversaryService anniversaryService;

    @PostMapping("/member/{memberId}/anniversary")
    public Anniversary addAnniversary(@PathVariable Long memberId, @RequestBody Map<String, String> request) {
        LocalDate date = LocalDate.parse(request.get("date"));
        String type = request.get("type");
        return anniversaryService.addAnniversary(memberId, date, type);
    }
}
