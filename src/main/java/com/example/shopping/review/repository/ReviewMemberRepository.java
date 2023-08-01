package com.example.shopping.review.repository;

import com.example.shopping.review.domain.entity.ReviewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewMemberRepository extends JpaRepository<ReviewMember, Long> {
//    @Modifying
//    @Query("INSERT INTO ReviewMember (MemberSeq, ReviewSeq) VALUES (:MemberSeq, :ReviewSeq)")
//    void insertLike(@Param("MemberSeq") Long MemberSeq, @Param("ReviewSeq") Long ReviewSeq);

    @Modifying
    @Query("DELETE FROM ReviewMember WHERE member.memberSeq = :memberSeq AND review.reviewSeq = :reviewSeq")
    void deleteLike(@Param("memberSeq") Long MemberSeq, @Param("reviewSeq") Long ReviewSeq);
}
