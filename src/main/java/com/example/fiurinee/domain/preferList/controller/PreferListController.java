package com.example.fiurinee.domain.preferList.controller;

import com.example.fiurinee.domain.member.entity.Member;
import com.example.fiurinee.domain.member.service.MemberService;
import com.example.fiurinee.domain.preferList.controller.api.PreferListApi;
import com.example.fiurinee.domain.preferList.entity.PreferList;
import com.example.fiurinee.domain.preferList.service.PreferListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class PreferListController implements PreferListApi {

    private final MemberService memberService;
    private final PreferListService preferListService;

    @GetMapping("/{id}/{order}")
    public Boolean savePrefer(@PathVariable("id") Long id,
                                        @PathVariable("order") Long order){
        Member byId = memberService.findById(id);

        PreferList build = PreferList.builder().preferOrder(order)
                .member(byId)
                .build();
        preferListService.save(build);

        return true;

    }

    @DeleteMapping("/{id}/{order}")
    public Boolean deletePrefer(@PathVariable("id") Long id,
                              @PathVariable("order") Long order){
        Member byId = memberService.findById(id);

        preferListService.delete(preferListService.findByMemberAndOrder(byId,order));

        return true;
    }
}
