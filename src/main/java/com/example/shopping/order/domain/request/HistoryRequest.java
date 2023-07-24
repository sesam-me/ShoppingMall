package com.example.shopping.order.domain.request;

import com.example.shopping.order.domain.entity.History;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryRequest {
    private String cancel_content; //주문취소사유
    private String refund_content; //주문환불사유
    private LocalDate history_date;//취소환불일자

    public History toEntity() {
        return History.builder()
                .cancel_content(cancel_content)
                .refund_content(refund_content)
                .history_date(history_date)
                .build();
    }

}
