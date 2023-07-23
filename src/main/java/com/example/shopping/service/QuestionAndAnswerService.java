package com.example.shopping.service;

import com.example.shopping.domain.entity.QuestionAndAnswer;
import com.example.shopping.domain.request.QuestionAndAnswerRequest;
import com.example.shopping.repository.QuestionAndAnswerRepository;
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

    public void saveQAndA(QuestionAndAnswerRequest questionAndAnswerRequest) {
        questionAndAnswerRepository.save(questionAndAnswerRequest.toEntity());
    }

    public void deleteQAndA(Long qa_seq) {
        questionAndAnswerRepository.deleteById(qa_seq);
    }
}
