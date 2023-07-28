package com.example.shopping.order.domain.request;

import com.example.shopping.order.domain.entity.History;
import com.example.shopping.product.domain.entity.Product;
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
    private String cancelContent; //주문취소사유
    private String refundContent; //주문환불사유
    private LocalDate historyDate;//취소환불일자


    public History toEntity(Long productSeq) {
        return History.builder()
                .cancelContent(cancelContent)
                .refundContent(refundContent)
                .historyDate(historyDate)
                .product(Product.builder().productSeq(productSeq).build())
                .build();
    }

}
