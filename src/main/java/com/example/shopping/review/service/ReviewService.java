package com.example.shopping.review.service;

import com.example.shopping.common.RestError;
import com.example.shopping.common.RestResult;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public void saveLike(Long reviewSeq, Long memberSeq) {
        try {
            Optional<Member> member = memberRepository.findById(memberSeq);
            Optional<Review> review = reviewRepository.findById(reviewSeq);

            ReviewMember reviewMember = ReviewMember
                    .builder()
                    .review(review.get())
                    .member(member.get())
                    .build();
            reviewMemberRepository.save(reviewMember);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RestResult<>("error", new RestError("server_error", "server_error")));
        }
        try {
            Optional<Review> review = reviewRepository.findById(reviewSeq);
            Review review1 = review.get();
            review1.setHeart(review1.getHeart() + 1); // heart 라는 필드가 있어야 하는 이유 반전교화 공뷰
            reviewRepository.save(review1);

        }catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RestResult<>("error", new RestError("server_error", "server_error")));
        }
        //review_member 테이블에 값이 들어왔을때
        //review 의 heart 수를 올려줘
    }

    public void deleteLike(Long memberSeq, Long reviewSeq) {
        try {
            reviewMemberRepository.deleteLike(memberSeq, reviewSeq);
        }catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RestResult<>("error", new RestError("server_error", "server_error")));
        }
        try {
            Optional<Review> review = reviewRepository.findById(reviewSeq);
            Review review1 = review.get();
            review1.setHeart(review1.getHeart() - 1);
            reviewRepository.save(review1);

        }catch (Exception e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RestResult<>("error", new RestError("server_error", "server_error")));
        }

        //review_member 테이블에 값이 지워졌을 때
        //review 의 heart 수를 내려줘
    }

    public void deleteReview(Long reviewSeq) {
        reviewRepository.deleteById(reviewSeq);
    }


}
