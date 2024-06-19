package com.example.fiurinee.domain.anniversary.controller;

import com.example.fiurinee.domain.anniversary.controller.api.AnniversaryApi;
import com.example.fiurinee.domain.anniversary.dto.AnniversaryRequestDTO;
import com.example.fiurinee.domain.anniversary.entity.Anniversary;
import com.example.fiurinee.domain.anniversary.service.AnniversaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AnniversaryController implements AnniversaryApi {
    @Autowired
    private AnniversaryService anniversaryService;

    @Override
    public ResponseEntity<Anniversary> addAnniversary(@PathVariable Long id, @RequestBody AnniversaryRequestDTO request) {
        Anniversary anniversary = anniversaryService.addAnniversary(id, request);
        return ResponseEntity.ok(anniversary);
    }

    @Override
    public ResponseEntity<Anniversary> updateAnniversary(@PathVariable Long id, @PathVariable Long anniversaryId, @RequestBody AnniversaryRequestDTO request) {
        Anniversary anniversary = anniversaryService.updateAnniversary(id, anniversaryId, request);
        return ResponseEntity.ok(anniversary);
    }

    @Override
    public ResponseEntity<Void> deleteAnniversary(@PathVariable Long id, @PathVariable Long anniversaryId) {
        anniversaryService.deleteAnniversary(id, anniversaryId);
        return ResponseEntity.noContent().build();
    }
}
