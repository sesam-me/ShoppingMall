package com.example.shopping.member.service;

import com.example.shopping.member.domain.Response.MemberResponse;
import com.example.shopping.member.domain.dto.GradeInsertDto;
import com.example.shopping.member.domain.dto.MemberInsertDto;
import com.example.shopping.member.domain.dto.PointInsertDto;
import com.example.shopping.member.domain.entity.Grade;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.member.domain.entity.Point;
import com.example.shopping.member.repository.GradeRepository;
import com.example.shopping.member.repository.MemberRepository;
import com.example.shopping.member.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;
    private final GradeRepository gradeRepository;

    public void memberInsert(MemberInsertDto memberInsertDto){
        // Grade 객체 생성
        Grade grade = Grade.builder()
                .gradeName(memberInsertDto.getGradeName())
                .build();

        // Point 객체 생성
        Point point = Point.builder()
                .pointBalance(memberInsertDto.getPointBalance())
                .build();

        // Grade와 Point 엔티티를 먼저 저장하여 기본 키 값을 생성
        gradeRepository.save(grade);
        pointRepository.save(point);

        // Member 객체 생성 및 데이터 설정
        Member member = Member.builder()
                .id(memberInsertDto.getId())
                .password(memberInsertDto.getPassword())
                .username(memberInsertDto.getUsername())
                .gender(memberInsertDto.isGender())
                .registrationDate(memberInsertDto.getRegistrationDate())
                .address(memberInsertDto.getAddress())
                .grade(grade) // 저장된 Grade 엔티티를 설정
                .point(point) // 저장된 Point 엔티티를 설정
                .build();

        // Member 엔티티를 저장하면 자동으로 Grade와 Point 엔티티도 저장됨
        memberRepository.save(member);
    }
    public List<MemberResponse> findAll(){
        List<Member> all = memberRepository.findAll();
        System.out.println(all);


        return all.stream().map(MemberResponse::new).toList();
    }


//    ## point ##
    public void pointInsert(PointInsertDto pointInsertDto){
        Optional<Member> findByMemberSeq = memberRepository.findById(pointInsertDto.getMemberSeq());

        Point point = Point.builder()
                .pointSeq(pointInsertDto.getPointSeq())
                .pointBalance(pointInsertDto.getPointBalance())
                .accumulationDate(pointInsertDto.getAccumulationDate())
                .usageDate(pointInsertDto.getUsageDate())
                .expirationDate(pointInsertDto.getExpirationDate())
                .build();
        pointRepository.save(point);
    }


//    ## grade ##
    public void gradeInsert(GradeInsertDto gradeInsertDto){
        Optional<Member> findByMemberSeq = memberRepository.findById(gradeInsertDto.getMemberSeq());

        Grade grade = Grade.builder()
                .gradeSeq(gradeInsertDto.getGradeSeq())
                .gradeName(gradeInsertDto.getGradeName())
                .build();

        gradeRepository.save(grade);
    }

}
