package com.example.shopping.controller;

import com.example.shopping.domain.entity.History;
import com.example.shopping.domain.request.HistoryRequest;
import com.example.shopping.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/histories")
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping
    public List<History> findAllHistory() {
        return historyService.findAllHistory();
    }

    @PostMapping
    public void saveHistory(@RequestBody HistoryRequest historyRequest) {
        historyService.saveHistory(historyRequest);
    }

    @DeleteMapping("{history_seq}")
    public void deleteHistory(@PathVariable("history_seq") Long history_seq) {
        historyService.deleteHistory(history_seq);
    }
}
