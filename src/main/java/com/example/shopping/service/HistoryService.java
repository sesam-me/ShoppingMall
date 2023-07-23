package com.example.shopping.service;

import com.example.shopping.domain.entity.History;
import com.example.shopping.domain.request.HistoryRequest;
import com.example.shopping.repository.HistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class HistoryService {
    private final HistoryRepository historyRepository;

    public List<History> findAllHistory() {
        return historyRepository.findAll();
    }

    public void saveHistory(HistoryRequest historyRequest) {
        historyRepository.save(historyRequest.toEntity());
    }

    public void deleteHistory(Long history_seq) {
        historyRepository.deleteById(history_seq);
    }
}
