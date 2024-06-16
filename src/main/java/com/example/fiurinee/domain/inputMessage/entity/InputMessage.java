package com.example.fiurinee.domain.inputMessage.entity;

import com.example.fiurinee.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InputMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inputMessageId;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    private String content;
    private Timestamp createAt;
    private Boolean prefer;
}
