package com.example.shopping.order.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class HistoryUpdateRequest {
    private String cancelContent; //주문취소사유
    private String refundContent; //주문환불사유
    private LocalDate historyDate;//취소환불일
}
