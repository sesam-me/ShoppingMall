package com.example.shopping.member.service;

import com.example.shopping.common.RestError;
import com.example.shopping.common.RestResult;
import com.example.shopping.member.domain.Response.MemberLoginResponse;
import com.example.shopping.member.domain.Response.MemberResponse;
import com.example.shopping.member.domain.dto.*;
import com.example.shopping.member.domain.entity.Grade;
import com.example.shopping.member.domain.entity.LoginHistory;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.member.domain.entity.Point;
import com.example.shopping.member.repository.GradeRepository;
import com.example.shopping.member.repository.LoginRecordRepository;
import com.example.shopping.member.repository.MemberRepository;
import com.example.shopping.member.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;
    private final GradeRepository gradeRepository;
    private final LoginRecordRepository loginRecordRepository;

    public ResponseEntity<RestResult<Object>> findById2(String id) {
        Member byId = memberRepository.findById(id);
        return ResponseEntity.ok(new RestResult<>("success", byId));

    }

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

//  if (findMember != null) {
//        return ResponseEntity.status(HttpStatus.CONFLICT)
//                .body(new RestResult<>("error", new RestError("duplicate_id", "이미 사용중인 ID 입니다.")));
//    }


//    # 로그인 & 로그인 이력
    public ResponseEntity<RestResult<Object>> memberLogin(MemberLoginDto memberLoginDto) {
        // 입력받은 ID로 해당하는 ID가 존재하는지 확인한다.. 여기서 만약 null 이라면..? 해당 회원이 아예 존재하지 않는것이니.. 로그인 이력 테이블에 쌓아 줄 필요없이 바로 예외처리 해준다.
        Member findMember = memberRepository.findById(memberLoginDto.getId());
        if (findMember == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RestResult<>("error",new RestError("NOT_FOUND","NOT_FOUND")));
        }

        // 방금 찾아온 회원의 정보에서 비밀번호를 꺼내와서.. 유저가 입력한 번호와 같지 않다면 ? 로그인 이력 테이블에 member_seq 와 로그인 실패기록을 적재한다..
        if (!findMember.getPassword().equals(memberLoginDto.getPassword())) {
            LoginHistory loginHistory = LoginHistory.builder()
                    .loginTime(LocalDateTime.now())
                    .isSuccessfulLogin(false)
                    .member(findMember)
                    .build();
            loginRecordRepository.save(loginHistory);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RestResult<>("error",new RestError("BAD_REQUEST","BAD_REQUEST")));
        }

        // 위에서 찾아온 회원의 정보에서 꺼내온 비밀번호와 유저가 입력한 번호가 같다면 .. ? 로그인을 성공 시켜주고  로그인 이력 테이블에 성공로그를 적재한다..
        MemberLoginResponse build = MemberLoginResponse
                .builder()
                .address(findMember.getAddress())
                .id(findMember.getId())
                .deliveries(findMember.getDeliveries())
                .username(findMember.getUsername())
                .phoneNum(findMember.getPhoneNum())
                .isLogin(true)
                .build();

        LoginHistory loginRecord = LoginHistory.builder()
                    .member(findMember)
                    .loginTime(LocalDateTime.now())
                    .isSuccessfulLogin(true)
                    .build();
            loginRecordRepository.save(loginRecord);

            return ResponseEntity.ok(new RestResult<>("success", build));
        }



//    # 회원정보수정
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
                .password(findById.getPassword())
                .username(memberUpdateDto.getUsername())
                .address(memberUpdateDto.getAddress())
                .build();
        memberRepository.save(member);
        return ResponseEntity.ok(new RestResult<>("success", "회원정보수정이 완료되었습니다."));
    }

//    POINT 충전
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


//      # ID 찾기
    public ResponseEntity<RestResult<Object>> findByPhoneNum(String phoneNum){
        Member byPhoneNum = memberRepository.findByPhoneNum(phoneNum);

        if(!phoneNum.equals(byPhoneNum.getPhoneNum())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RestResult<>("error", new RestError("PHONENUM_NOT_FOUND","일치하는 핸드폰번호가 없습니다.")));
        }

        return ResponseEntity.ok(new RestResult<>("success", byPhoneNum.getId()));

    }


//    # 비밀번호 찾기
    public ResponseEntity<RestResult<Object>> findById(String id){
//        TODO
//        회원의 ID 로 비밀번호를 찾는 기능을 만들어야함 (ok)
//        회원의 ID를 입력하면 그 이메일로 임시비밀번호가 발송되고 임시비밀번호로 바뀌어야함
        Member byId = memberRepository.findById(id);

        if (byId == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RestResult<>("error", new RestError("ID_NOT_FOUND", "일치하는 ID가 없습니다.")));
        }

        return ResponseEntity.ok(new RestResult<>("success", byId.getId()));
    }


//    # 현재 비밀번호 일치 여부 확인 후 수정
    public ResponseEntity<RestResult<Object>> checkCurrentPassword(PasswordUpdateDto passwordUpdateDto, String id){
        Member byId = memberRepository.findById(id);
        if(!byId.getPassword().equals(passwordUpdateDto.getCurrentPassword())){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("error", "현재 비밀번호가 일치하지 않습니다."));
        }

                Member member = Member.builder()
                .memberSeq(byId.getMemberSeq())
                .id(byId.getId())
                .password(passwordUpdateDto.getUpdatePassword()) // password만 변경
                .username(byId.getUsername())
                .phoneNum(byId.getPhoneNum())
                .registrationDate(byId.getRegistrationDate())
                .address(byId.getAddress())
                .deliveries(byId.getDeliveries())
                .point(byId.getPoint())
                .grade(byId.getGrade())
                .payment(byId.getPayment())
                .build();
        memberRepository.save(member);
        return ResponseEntity.ok(new RestResult<>("success", "비밀번호수정이 완료되었습니다."));
    }





}
