package com.example.shopping.member.controller;

import com.example.shopping.common.RestResult;
import com.example.shopping.member.domain.Response.MemberResponse;
import com.example.shopping.member.domain.dto.*;
import com.example.shopping.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/insert")
    public ResponseEntity<RestResult<Object>> memberInsert(@RequestBody MemberInsertDto memberInsertDto){
        return memberService.memberInsert(memberInsertDto);
    }

    @GetMapping
    public List<MemberResponse> findAll(){
        return memberService.findAll();
    }

    @PostMapping("/login")
    public MemberLoginResponse login(@RequestBody MemberLoginDto memberLoginDto) {
        return memberService.memberLogin(memberLoginDto);
    }

    @PutMapping("/update/{id}")
    public void memberUpdate(@RequestBody MemberUpdateDto memberUpdateDto, @PathVariable("id")String id){
        memberService.memberUpdate(memberUpdateDto, id);
    }



//    ## point ##
//    @PostMapping("/insert/point")
//    public void pointInsert(@RequestBody PointInsertDto pointInsertDto){
//        memberService.pointInsert(pointInsertDto);
//    }
//
////    ## grade ##
//    @PostMapping("insert/grade")
//    public void gradeInsert(@RequestBody GradeInsertDto gradeInsertDto) {memberService.gradeInsert(gradeInsertDto);}

}
