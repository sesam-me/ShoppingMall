package com.example.shopping.order.domain.dto;

import com.example.shopping.order.domain.entity.History;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class HistoryDto {
    private Long historySeq;
    private String cancelContent; //주문취소사유
    private String refundContent; //주문환불사유
    private LocalDate historyDate;//취소환불일자
    private Long productSeq;

    public HistoryDto(History history) {
        this.historySeq = history.getHistorySeq();
        this.cancelContent = history.getCancelContent();
        this.refundContent = history.getRefundContent();
        this.historyDate = history.getHistoryDate();
        this.productSeq = history.getProduct().getProductSeq();
    }
}
