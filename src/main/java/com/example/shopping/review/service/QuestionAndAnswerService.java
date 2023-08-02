package com.example.shopping.review.service;

import com.example.shopping.review.domain.entity.QuestionAndAnswer;
import com.example.shopping.review.domain.request.QuestionAndAnswerRequest;
import com.example.shopping.review.repository.QuestionAndAnswerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class QuestionAndAnswerService {
    private final QuestionAndAnswerRepository questionAndAnswerRepository;

    public List<QuestionAndAnswer> findAllQAndA() {
        return questionAndAnswerRepository.findAll();
    }

    public void saveQAndA(QuestionAndAnswerRequest questionAndAnswerRequest, Long productSeq, Long memberSeq) {

        questionAndAnswerRepository.save(questionAndAnswerRequest.toEntity(productSeq, memberSeq));
    }

    public void deleteQAndA(Long qaSeq) {
        questionAndAnswerRepository.deleteById(qaSeq);
    }
}
