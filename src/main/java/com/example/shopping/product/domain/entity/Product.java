package com.example.shopping.product.domain.entity;

import com.example.shopping.inventory.domain.entity.Inventory;
import com.example.shopping.inventory.domain.entity.InventoryAlert;
import com.example.shopping.order.domain.entity.History;
import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.review.domain.entity.QuestionAndAnswer;
import com.example.shopping.review.domain.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productSeq;
    private String hname;
    private String ename;
    private String imgUrl;
    private String detailImgUrl;
    private LocalDate createAt;
    private String brand;
    private Integer price;


    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductOption> productOptions;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Interest> interests;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductOption2> productOptions2;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<QuestionAndAnswer> questionAndAnswers;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Review> reviews;

//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
//    private List<History> histories;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Inventory> inventories;


    public void update(String hname, String ename, String imgUrl, String brand, String detailImgUrl, Integer price) {
        this.hname = hname;
        this.ename = ename;
        this.imgUrl = imgUrl;
        this.brand = brand;
        this.detailImgUrl = detailImgUrl;
        this.price = price;
    }

}
