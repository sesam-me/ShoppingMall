package com.example.shopping.review.service;

import com.example.shopping.member.domain.entity.Member;
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

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewMemberRepository reviewMemberRepository;

    public List<Review> findAllReview() {
        return reviewRepository.findAll();
    }

    public void saveReview(ReviewRequest reviewRequest, Long productSeq) {
        reviewRepository.save(reviewRequest.toEntity(productSeq));
    }

    public void saveLike(Long memberSeq, Long reviewSeq) {
        reviewMemberRepository.save(ReviewMember.builder()
                .member(Member.builder().memberSeq(memberSeq).build())
                .review(Review.builder().reviewSeq(reviewSeq).build())
                .build());
    }

    public void deleteLike(Long memberSeq, Long reviewSeq) {
        reviewMemberRepository.deleteLike(memberSeq, reviewSeq);
    }

    public void deleteReview(Long reviewSeq) {
        reviewRepository.deleteById(reviewSeq);
    }
}
