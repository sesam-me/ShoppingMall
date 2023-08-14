package com.example.shopping.review.repository;

import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.review.domain.entity.Review;
import com.example.shopping.review.domain.entity.ReviewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewMemberRepository extends JpaRepository<ReviewMember, Long> {

    ReviewMember findByMemberAndReview(Member member, Review review);

    Boolean existsByMember_MemberSeqAndReview_ReviewSeq(Long memberSeq, Long reviewSeq);

    @Modifying
    @Query("DELETE FROM ReviewMember WHERE member.memberSeq = :memberSeq AND review.reviewSeq = :reviewSeq")
    void deleteLike(@Param("memberSeq") Long MemberSeq, @Param("reviewSeq") Long ReviewSeq);

    List<ReviewMember> findAllByMember_MemberSeq(Long memberSeq);
}
