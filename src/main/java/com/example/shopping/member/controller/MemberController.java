package com.example.shopping.member.controller;

import com.example.shopping.member.domain.Response.MemberResponse;
import com.example.shopping.member.domain.dto.MemberInsertDto;
import com.example.shopping.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/insert")
    public void memberInsert(@RequestBody MemberInsertDto memberInsertDto){
        memberService.memberInsert(memberInsertDto);
    }

    @GetMapping
    public List<MemberResponse> findAll(){
        return memberService.findAll();
    }
}
