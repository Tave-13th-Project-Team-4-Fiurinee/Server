package com.example.fiurinee.domain.inputMessage.service;

import com.example.fiurinee.domain.inputMessage.entity.InputMessage;
import com.example.fiurinee.domain.inputMessage.repository.InputMessageRepository;
import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.member.repository.MemberRepository;
import com.example.fiurinee.domain.member.service.MemberService;
import com.example.fiurinee.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class InputMessageService {

    private final MemberRepository memberRepository;
    private final InputMessageRepository inputMessageRepository;

    @Transactional
    public boolean saveInputMessage(Long id, String message){
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new CustomException("존재하지 않는 회원 아이디 입니다."));

        InputMessage inputMessage = InputMessage.builder()
                .content(message)
                .createAt(timestamp)
                .prefer(false)
                .member(member)
                .build();

        inputMessageRepository.save(inputMessage);

        return true;
    }

}
