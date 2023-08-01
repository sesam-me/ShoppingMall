package com.example.shopping.review.service;

import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.member.repository.MemberRepository;
import com.example.shopping.review.domain.entity.Review;
import com.example.shopping.review.domain.entity.ReviewMember;
import com.example.shopping.review.domain.request.ReviewRequest;
import com.example.shopping.review.repository.ReviewMemberRepository;
import com.example.shopping.review.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMemberRepository reviewMemberRepository;
    private final MemberRepository memberRepository;

    public List<Review> findAllReview() {
        return reviewRepository.findAll();
    }

    public void saveReview(ReviewRequest reviewRequest, Long productSeq) {
        reviewRepository.save(reviewRequest.toEntity(productSeq));
    }

    public void saveLike(Long memberSeq, Long reviewSeq) {
        Optional<Member> member = memberRepository.findById(memberSeq);
        Optional<Review> review = reviewRepository.findById(reviewSeq);

        System.out.println(member);
        System.out.println(review);

        ReviewMember reviewMember = ReviewMember.builder().review(review.get()).member(member.get()).build();
        reviewMemberRepository.save(reviewMember);
    }

    public void deleteLike(Long memberSeq, Long reviewSeq) {
        reviewMemberRepository.deleteLike(memberSeq, reviewSeq);
    }

    public void deleteReview(Long reviewSeq) {
        reviewRepository.deleteById(reviewSeq);
    }
}
