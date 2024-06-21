package com.example.fiurinee.domain.anniversary.controller;

import com.example.fiurinee.domain.anniversary.controller.api.AnniversaryApi;
import com.example.fiurinee.domain.anniversary.dto.AnniversaryRequestDTO;
import com.example.fiurinee.domain.anniversary.dto.AnniversaryResponseDTO;
import com.example.fiurinee.domain.anniversary.entity.Anniversary;
import com.example.fiurinee.domain.anniversary.service.AnniversaryService;
import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class AnniversaryController implements AnniversaryApi {
    @Autowired
    private AnniversaryService anniversaryService;

    @Autowired
    private MemberService memberService;

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

    @Override
    public ResponseEntity<List<AnniversaryResponseDTO>> getZeroDayAnniversaries(@PathVariable Long id) {
        Member member = memberService.findById(id);
        List<Anniversary> anniversaries = member.getAnniversaries();

        List<AnniversaryResponseDTO> zeroDayAnniversaries = anniversaryService.getDDayZeroAnniversaries(anniversaries);

        return ResponseEntity.ok(zeroDayAnniversaries);
    }


}
