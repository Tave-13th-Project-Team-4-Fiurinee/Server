package com.example.fiurinee.domain.preferList.service;

import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.preferList.entity.PreferList;
import com.example.fiurinee.domain.preferList.repository.PreferListRepository;
import com.example.fiurinee.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class PreferListService {

    private final PreferListRepository preferListRepository;

    @Transactional
    public void save(PreferList preferList){
        preferListRepository.save(preferList);
    }

    public PreferList findByMemberAndOrder(Member member, Long order){
        PreferList preferList = preferListRepository.findByMemberAndPreferOrder(member, order).orElseThrow(
                () -> new CustomException("일치하는 유저 혹은 order가 존재하지 않습니다."));

        return preferList;
    }

    @Transactional
    public void delete(PreferList preferList){
        preferListRepository.delete(preferList);
    }
}
