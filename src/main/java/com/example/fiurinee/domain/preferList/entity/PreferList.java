package com.example.fiurinee.domain.preferList.entity;

import com.example.fiurinee.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PreferList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferListId;

    private Long preferOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
}
