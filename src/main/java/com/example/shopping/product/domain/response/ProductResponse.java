package com.example.shopping.product.domain.response;

import com.example.shopping.product.domain.entity.Interest;
import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.product.domain.entity.ProductOption;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class ProductResponse {
    private Long productSeq;
    private String name;
    private String imgUrl;
    private List<ProductOptionDto> productOptionDto;
    private List<InterestDto> interestDto;

    public ProductResponse(Product product) {
        this.productSeq = product.getProductSeq();
        this.name = product.getName();
        this.imgUrl = product.getImgUrl();
        this.productOptionDto = product.getProductOption() != null ?
                product.getProductOption().stream().map(ProductOptionDto::new).toList()
                : new ArrayList<>();
        this.interestDto = product.getInterests() != null ?
                product.getInterests().stream().map(InterestDto::new).toList()
                : new ArrayList<>();
    }

    @Getter @AllArgsConstructor
    class ProductOptionDto {
        private Long optionSeq;
        private String color;
        private Integer size;
        private Long productSeq;

        public ProductOptionDto(ProductOption productOption) {
            this.optionSeq = productOption.getOptionSeq();
            this.color = productOption.getColor();
            this.size = productOption.getSize();
            this.productSeq = productOption.getProduct().getProductSeq();
        }
    }
    @Getter @AllArgsConstructor
    class InterestDto {
        private Long interestSeq;
        private LocalDate interestDate; //관심 표현한 날짜
        private Integer interestLike;
        private Long productSeq;

        public InterestDto(Interest interest) {
            this.interestSeq = interest.getInterestSeq();
            this.interestDate = interest.getInterestDate();
            this.interestLike = interest.getInterestLike();
            this.productSeq = interest.getProduct().getProductSeq();
        }
    }
}
