package com.example.shopping.order.domain.response;

import com.example.shopping.order.domain.entity.History;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HistoryResponse {
    private String cancelContent; //주문취소사유
    private String refundContent; //주문환불사유
    private LocalDate historyDate;//취소환불일

    public HistoryResponse(History history) {
        this.cancelContent = history.getCancelContent();
        this.refundContent = history.getRefundContent();
        this.historyDate = history.getHistoryDate();
    }
}
