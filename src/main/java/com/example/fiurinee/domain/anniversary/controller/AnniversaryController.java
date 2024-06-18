package com.example.fiurinee.domain.anniversary.controller;

import com.example.fiurinee.domain.anniversary.controller.api.AnniversaryApi;
import com.example.fiurinee.domain.anniversary.entity.Anniversary;
import com.example.fiurinee.domain.anniversary.service.AnniversaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
public class AnniversaryController implements AnniversaryApi {
    @Autowired
    private AnniversaryService anniversaryService;

    @Override
    public ResponseEntity<Anniversary> addAnniversary(@PathVariable Long id, @RequestBody Map<String, String> request) {
        LocalDate date = LocalDate.parse(request.get("date"));
        String type = request.get("type");
        Anniversary anniversary = anniversaryService.addAnniversary(id, date, type);
        return ResponseEntity.ok(anniversary);
    }
}
