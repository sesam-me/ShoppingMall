package com.example.shopping.member.service;

import com.example.shopping.common.RestError;
import com.example.shopping.common.RestResult;
import com.example.shopping.member.domain.Response.MemberResponse;
import com.example.shopping.member.domain.dto.*;
import com.example.shopping.member.domain.entity.Grade;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.member.domain.entity.Point;
import com.example.shopping.member.repository.GradeRepository;
import com.example.shopping.member.repository.MemberRepository;
import com.example.shopping.member.repository.PointRepository;
import com.example.shopping.payment.domain.entity.Payment;
import com.example.shopping.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;
    private final GradeRepository gradeRepository;

    public ResponseEntity<RestResult<Object>> memberInsert(MemberInsertDto memberInsertDto){
        Member member2 = memberRepository.findById(memberInsertDto.getId());

        if (member2 != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("error", new RestError("duplicate_id", "이미 사용중인 ID 입니다.")));
        }

        // Grade Default 객체 생성 ( 5등급 )
        Grade grade = Grade.builder()
                .gradeSeq(memberInsertDto.getGradeSeq())
                .userId(memberInsertDto.getId())
                .gradeName(memberInsertDto.getGradeName())
                .build();

        // Point Default 객체 생성 ( 0원 )
        Point point = Point.builder()
                .pointSeq(memberInsertDto.getPointSeq())
                .userId(memberInsertDto.getId())
                .pointBalance(memberInsertDto.getPointBalance())
                .accumulationDate(memberInsertDto.getAccumulationDate())
                .usageDate(memberInsertDto.getUsageDate())
                .expirationDate(memberInsertDto.getExpirationDate())
                .build();


        // Grade와 Point 엔티티를 먼저 저장하여 기본 키 값을 생성
        gradeRepository.save(grade);
        pointRepository.save(point);

        // Member 객체 생성 및 데이터 설정
        Member member = Member.builder()
                .id(memberInsertDto.getId())
                .password(memberInsertDto.getPassword())
                .username(memberInsertDto.getUsername())
                .registrationDate(memberInsertDto.getRegistrationDate())
                .address(memberInsertDto.getAddress())
                .grade(grade) // 저장된 Grade 엔티티를 설정
                .point(point) // 저장된 Point 엔티티를 설정
                .build();

        // Member 엔티티를 저장하면 자동으로 Grade와 Point 엔티티도 저장됨
        memberRepository.save(member);
        return ResponseEntity.ok(new RestResult<>("success", "회원가입이 완료 되었습니다."));
    }

    public List<MemberResponse> findAll(){
        List<Member> all = memberRepository.findAll();
        System.out.println(all);
        return all.stream().map(MemberResponse::new).toList();
    }


//   ## point ##
//    public void pointInsert(PointInsertDto pointInsertDto){
//        Optional<Member> findByMemberSeq = memberRepository.findById(pointInsertDto.getMemberSeq());
//
//        Point point = Point.builder()
//                .pointSeq(pointInsertDto.getPointSeq())
//                .pointBalance(pointInsertDto.getPointBalance())
//                .accumulationDate(pointInsertDto.getAccumulationDate())
//                .usageDate(pointInsertDto.getUsageDate())
//                .expirationDate(pointInsertDto.getExpirationDate())
//                .build();
//        pointRepository.save(point);
//    }


//    ## grade ##
//    public void gradeInsert(GradeInsertDto gradeInsertDto){
//        Optional<Member> findByMemberSeq = memberRepository.findById(gradeInsertDto.getMemberSeq());
//
//        Grade grade = Grade.builder()
//                .gradeSeq(gradeInsertDto.getGradeSeq())
//                .gradeName(gradeInsertDto.getGradeName())
//                .build();
//
//        gradeRepository.save(grade);
//    }

    public MemberLoginResponse memberLogin(MemberLoginDto memberLoginDto) {
        Member byIdAndPass = memberRepository.findByIdAndPassword(memberLoginDto.getId(), memberLoginDto.getPassword());

        MemberLoginResponse memberLoginResponse = new MemberLoginResponse();

        if(byIdAndPass != null) {
            memberLoginResponse = MemberLoginResponse
                    .builder()
                    .address(byIdAndPass.getAddress())
                    .id(byIdAndPass.getId())
                    .deliveries(byIdAndPass.getDeliveries())
                    .username(byIdAndPass.getUsername())
                    .isLogin(true)
                    .build();
        }
        return memberLoginResponse;
    }

}
