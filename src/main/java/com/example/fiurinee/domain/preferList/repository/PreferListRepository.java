package com.example.fiurinee.domain.preferList.repository;

import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.preferList.entity.PreferList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferListRepository extends JpaRepository<PreferList, Long> {

    Optional<PreferList> findByMemberAndPreferOrder(Member member,Long order);
}
