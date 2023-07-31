package com.example.shopping.order.controller;

import com.example.shopping.order.domain.entity.History;
import com.example.shopping.order.domain.request.HistoryRequest;
import com.example.shopping.order.service.HistoryService;
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
    public void saveHistory(@RequestBody HistoryRequest historyRequest, Long productSeq) {
        historyService.saveHistory(historyRequest, productSeq);
    }

    @DeleteMapping("{historySeq}")
    public void deleteHistory(@PathVariable("historySeq") Long historySeq) {
        historyService.deleteHistory(historySeq);
    }
}
