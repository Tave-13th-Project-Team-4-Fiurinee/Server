package com.example.fiurinee.domain.anniversary.entity;

import com.example.fiurinee.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Anniversary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 8)
    private String name;

    private Timestamp anniversaryDate;

    @Enumerated(EnumType.STRING)
    private AnniversaryType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    @JsonIgnore
    private Member member;

    public void setAnniversaryDate(Timestamp anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    public void setType(AnniversaryType type) {
        this.type = type;
    }
}
