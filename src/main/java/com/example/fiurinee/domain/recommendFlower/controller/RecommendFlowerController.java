package com.example.fiurinee.domain.recommendFlower.controller;

import com.example.fiurinee.domain.flower.entity.Flower;
import com.example.fiurinee.domain.flower.service.FlowerService;
import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.member.service.MemberService;
import com.example.fiurinee.domain.recommendComment.entity.RecommendComment;
import com.example.fiurinee.domain.recommendComment.repository.RecommendCommentRepository;
import com.example.fiurinee.domain.recommendComment.service.RecommendCommentService;
import com.example.fiurinee.domain.recommendFlower.dto.RecommendFlowerDto;
import com.example.fiurinee.domain.recommendFlower.entity.RecommendFlower;
import com.example.fiurinee.domain.recommendFlower.service.RecommendFlowerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class RecommendFlowerController {

    private final MemberService memberService;
    private final FlowerService flowerService;
    private final RecommendFlowerService recommendFlowerService;
    private final RecommendCommentService recommendCommentService;

    @GetMapping("/{id}/recommend/recent")
    public ResponseEntity<List<RecommendFlowerDto>> responseRecommendFlower(@PathVariable("id") Long id){
        Member byId = memberService.findById(id);

        List<RecommendFlowerDto> re = new ArrayList<>();

        for(long i = 1 ; i<4;i++){
            RecommendFlowerDto recommendFlowerDto = RecommendFlowerDto.of(i, byId);
            if(recommendFlowerDto == null){
            }else{
                re.add(recommendFlowerDto);
            }
        }

        return ResponseEntity.ok(re);
    }
//-----------------------------------------------------------------------------------------------------------------
//    @GetMapping("/save/test")
//    public RecommendFlower dd(){
//        RecommendFlower build = RecommendFlower.builder()
//                .flower(flowerService.findByNameAndFlowerLanguage("토마토", "완성된 아름다움"))
//                .prefer(true)
//                .member(memberService.findById(1L))
//                .build();
//
//        RecommendComment comment = RecommendComment.builder()
//                .content("이것은 추천 멘트")
//                .member(memberService.findById(1L))
//                .prefer(true)
//                .build();
//
//        recommendCommentService.saveRecommendComment(comment);
//
//
//
//        recommendFlowerService.saveRecommendFlower(build);
//        return memberService.findById(1L).getRecommendFlowers().get(0);
//    }
}
