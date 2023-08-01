package com.example.shopping.member.service;

import com.example.shopping.common.RestError;
import com.example.shopping.common.RestResult;
import com.example.shopping.member.domain.Response.MemberLoginResponse;
import com.example.shopping.member.domain.Response.MemberResponse;
import com.example.shopping.member.domain.dto.*;
import com.example.shopping.member.domain.entity.Grade;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.member.domain.entity.Point;
import com.example.shopping.member.repository.GradeRepository;
import com.example.shopping.member.repository.MemberRepository;
import com.example.shopping.member.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;
    private final GradeRepository gradeRepository;

    public ResponseEntity<RestResult<Object>> memberInsert(MemberInsertDto memberInsertDto){
        Member findMember = memberRepository.findById(memberInsertDto.getId());

        if (findMember != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("error", new RestError("duplicate_id", "이미 사용중인 ID 입니다.")));
        }

        // Grade Default 객체 생성 ( 5등급 )
        Grade grade = Grade.builder()
                .gradeName("5등급")
                .userId(memberInsertDto.getId())
                .build();
        // Point Default 객체 생성 ( 0원 )
        Point point = Point.builder()
                .pointBalance(0)
                .id(memberInsertDto.getId())
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
                .phoneNum(memberInsertDto.getPhoneNum())
                .grade(grade) // 저장된 Grade 엔티티를 설정
                .point(point) // 저장된 Point 엔티티를 설정
                .build();
        // Member 엔티티를 저장하면 자동으로 Grade와 Point 엔티티도 저장됨
        memberRepository.save(member);
        return ResponseEntity.ok(new RestResult<>("success", "회원가입이 완료 되었습니다."));
    }

    public List<MemberResponse> findAll(){
        List<Member> all = memberRepository.findAll();
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
                    .phoneNum(byIdAndPass.getPhoneNum())
                    .isLogin(true)
                    .build();
        }
        return memberLoginResponse;
    }

//    public ResponseEntity<RestResult<Object>> memberInsert(MemberInsertDto memberInsertDto){
//        Member findMember = memberRepository.findById(memberInsertDto.getId());
//
//        if (findMember != null) {
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body(new RestResult<>("error", new RestError("duplicate_id", "이미 사용중인 ID 입니다.")));
//        }


    public ResponseEntity<RestResult<Object>> memberUpdate(MemberUpdateDto memberUpdateDto, String id){
        Member findById = memberRepository.findById(id); //  주어진 findById는 id가 Long디폴트값. -> repository에서 String id를 받는 걸 만들어서 사용.

        if(findById == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RestResult<>("error", new RestError("ID_NOT_FOUND", "ID가 존재하지 않습니다.")));
        }


        Member member = Member.builder()
                .id(findById.getId())
                .registrationDate(findById.getRegistrationDate())
                .grade(findById.getGrade())
                .payment(findById.getPayment())
                .point(findById.getPoint())
                .memberSeq(findById.getMemberSeq())
                .password(memberUpdateDto.getPassword())
                .username(memberUpdateDto.getUsername())
                .address(memberUpdateDto.getAddress())
                .build();
        memberRepository.save(member);
        return ResponseEntity.ok(new RestResult<>("success", "회원정보수정이 완료되었습니다."));
    }

    public ResponseEntity<RestResult<Object>> pointCharge(PointChargeDto pointChargeDto, String id){
//        TODO 여기 원래 Obtional들어가는거 아닌가..?
//        null값일 경우를 대비해, 미리 오류 처리를 어떻게 할 것인지 정해둬야 함.
//        방법 1) return pointRepository.findById(memberSeq).orElseThrow()
//        방법 2) return pointRepository.findById(memberSeq).orElse(null)
        Point findById = pointRepository.findById(id);

        if(findById == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RestResult<>("error",new RestError("POINT_NOT_FOUND","충전할 POINT를 입력해주세요.")));
        }

        Point point = Point.builder()
                .pointSeq(findById.getPointSeq()) // 기본키 pointSeq를 가져와야 findById에서 맞는 값을 가져옴. 아니면 값 추가됨.
                .id(findById.getId())
                .pointBalance(pointChargeDto.getPointBalance())
                .accumulationDate(findById.getAccumulationDate())
                .usageDate(findById.getUsageDate())
                .expirationDate(findById.getExpirationDate())
                .build();
        pointRepository.save(point);
        return ResponseEntity.ok(new RestResult<>("success","POINT 충전이 완료되었습니다."));
    }



    public ResponseEntity<RestResult<Object>> findByPhoneNum(String phoneNum){
        Member byPhoneNum = memberRepository.findByPhoneNum(phoneNum);

        if(!phoneNum.equals(byPhoneNum.getPhoneNum())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RestResult<>("error", new RestError("PHONENUM_NOT_FOUND","일치하는 핸드폰번호가 없습니다.")));
        }

        return ResponseEntity.ok(new RestResult<>("success", byPhoneNum.getId()));

    }


    public ResponseEntity<RestResult<Object>> findById(String id){
        Member byId = memberRepository.findById(id);

        if (byId == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RestResult<>("error", new RestError("ID_NOT_FOUND", "일치하는 ID가 없습니다.")));
        }

        return ResponseEntity.ok(new RestResult<>("success", byId.getId()));
    }

}
