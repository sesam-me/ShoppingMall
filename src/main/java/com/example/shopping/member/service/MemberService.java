package com.example.shopping.member.service;

import com.example.shopping.member.domain.Response.MemberResponse;
import com.example.shopping.member.domain.dto.MemberInsertDto;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void memberInsert(MemberInsertDto memberInsertDto){
        Member member = Member.builder()
                .id(memberInsertDto.getId())
                .password(memberInsertDto.getPassword())
                .username(memberInsertDto.getUsername())
                .gender(memberInsertDto.isGender())
                .registrationDate(memberInsertDto.getRegistrationDate())
                .address(memberInsertDto.getAddress())

                .build();
        memberRepository.save(member);
    }
    public List<MemberResponse> findAll(){
        List<Member> all = memberRepository.findAll();
        return all.stream().map(MemberResponse::new).toList();
    }
}
