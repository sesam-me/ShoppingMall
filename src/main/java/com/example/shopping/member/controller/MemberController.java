package com.example.shopping.member.controller;

import com.example.shopping.common.RestResult;
import com.example.shopping.member.domain.Response.MemberResponse;
import com.example.shopping.member.domain.dto.*;
import com.example.shopping.member.domain.entity.LoginHistory;
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

    @GetMapping("{id}")
    public ResponseEntity<RestResult<Object>> findById2(@PathVariable String id) {
        return memberService.findById2(id);
    }

    @PostMapping("/login")
    public ResponseEntity<RestResult<Object>> login(@RequestBody MemberLoginDto memberLoginDto) {
        return memberService.memberLogin(memberLoginDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RestResult<Object>> memberUpdate(@RequestBody MemberUpdateDto memberUpdateDto, @PathVariable("id")String id){
        return memberService.memberUpdate(memberUpdateDto, id);
    }

    @PutMapping("/charge/{id}")
    public ResponseEntity<RestResult<Object>> pointCharge(@RequestBody PointChargeDto pointChargeDto, @PathVariable("id")String id){
        return memberService.pointCharge(pointChargeDto, id);
    }

//   ID찾기
    @GetMapping("/findId/{phoneNum}")
    public ResponseEntity<RestResult<Object>> findIdByPhoneNum(@PathVariable("phoneNum")String phoneNum){
        return memberService.findByPhoneNum(phoneNum);
    }

//    PASSWORD찾기
        @GetMapping("/findPassword/{id}")
    public ResponseEntity<RestResult<Object>> findPasswordById(@PathVariable("id")String id) throws Exception {
        return memberService.findPasswordById(id);
    }

    @PostMapping ("/checkCurrentPassword/{id}")
    public ResponseEntity<RestResult<Object>> checkCurrentPassword(@RequestBody PasswordUpdateDto passwordUpdateDto, @PathVariable("id")String id){
        return memberService.checkCurrentPassword(passwordUpdateDto, id);
    }


    @GetMapping("/history/{memberSeq}")
    public List<LoginHistory> findHistory(@PathVariable Long memberSeq) {
        return memberService.findHistory(memberSeq);
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
