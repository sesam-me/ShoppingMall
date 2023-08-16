package com.example.shopping.cart.domain.dto;

import com.example.shopping.cart.domain.entity.Cart;
import com.example.shopping.inventory.domain.dto.InventoryDto;
import com.example.shopping.member.domain.entity.Member;
import com.example.shopping.product.domain.dto.InterestDto;
import com.example.shopping.product.domain.dto.ProductDto;
import com.example.shopping.product.domain.dto.ProductOptionDto;
import com.example.shopping.product.domain.dto.ProductOptionDto2;
import com.example.shopping.product.domain.entity.Product;
import com.example.shopping.product.domain.response.ProductResponse;
import com.example.shopping.review.domain.dto.ReviewDto;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long cartSeq;
    private Long memberSeq;
    private Long productSeq;
    private String brand;
    private LocalDate createAt;
    private String detailImgUrl;
    private String ename;
    private String hname;
    private String imgUrl;
    private Integer price;
    private List<InterestDto> interestDto;
    private List<InventoryDto> inventoryDto;
    private List<ProductOptionDto> productOptionDto;
    private List<ProductOptionDto2> productOptionDto2;
    private List<ReviewDto> reviewDto;

    public CartDto(Cart cart) {
        this.cartSeq = cart.getCartSeq();
        this.memberSeq = cart.getMember().getMemberSeq();
        this.productSeq = cart.getProduct().getProductSeq();
        this.brand = cart.getProduct().getBrand();
        this.createAt = cart.getProduct().getCreateAt();
        this.detailImgUrl = cart.getProduct().getDetailImgUrl();
        this.ename = cart.getProduct().getEname();
        this.hname = cart.getProduct().getHname();
        this.imgUrl = cart.getProduct().getImgUrl();
        this.price = cart.getProduct().getPrice();
        this.interestDto = cart.getProduct().getInterests() != null ?
                cart.getProduct().getInterests().stream().map(InterestDto::new).toList() :
                new ArrayList<>();
        this.inventoryDto = cart.getProduct().getInventories() != null ?
                cart.getProduct().getInventories().stream().map(InventoryDto::new).toList() :
                new ArrayList<>();
        this.productOptionDto = cart.getProduct().getProductOptions() != null ?
                cart.getProduct().getProductOptions().stream().map(ProductOptionDto::new).toList() :
                new ArrayList<>();
        this.productOptionDto2 = cart.getProduct().getProductOptions2() != null ?
                cart.getProduct().getProductOptions2().stream().map(ProductOptionDto2::new).toList() :
                new ArrayList<>();
        this.reviewDto = cart.getProduct().getReviews() != null ?
                cart.getProduct().getReviews().stream().map(ReviewDto::new).toList() :
                new ArrayList<>();
    }
}
