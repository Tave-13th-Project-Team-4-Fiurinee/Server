package com.example.fiurinee.domain.member.controller;

import com.example.fiurinee.domain.member.controller.api.MemberApi;
import com.example.fiurinee.domain.member.dto.MemberResponseDTO;
import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemeberController implements MemberApi {

    @Autowired
    private MemberService memberService;

    @Override
    public ResponseEntity<MemberResponseDTO> getMemberById(@PathVariable("id") Long id) {
        MemberResponseDTO memberResponseDTO = memberService.getMemberDtoById(id);
        return ResponseEntity.ok(memberResponseDTO);
    }
}
