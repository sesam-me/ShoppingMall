package com.example.shopping.product.domain.entity;

import com.example.shopping.inventory.domain.entity.Inventory;
import com.example.shopping.inventory.domain.entity.InventoryAlert;
import com.example.shopping.order.domain.entity.History;
import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.review.domain.entity.QuestionAndAnswer;
import com.example.shopping.review.domain.entity.Review;
import jakarta.persistence.*;
import lombok.*;

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
    private String name;
    private String imgUrl;
    private String createAt;
    private String brand;


    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<ProductOption> productOptions;

//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
//    private List<Interest> interests;

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


    public void update(String name, String imgUrl, String brand) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productSeq=" + productSeq +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", createAt='" + createAt + '\'' +
                ", brand='" + brand + '\'' +
                ", productOptions=" + productOptions +
                ", questionAndAnswers=" + questionAndAnswers +
                ", reviews=" + reviews +
                ", orders=" + orders +
                ", inventories=" + inventories +
                '}';
    }
}
