package com.example.shopping.member.domain.entity;

import com.example.shopping.cart.domain.entity.Cart;
import com.example.shopping.delivery.domain.entity.Delivery;
import com.example.shopping.order.domain.entity.Order;
import com.example.shopping.payment.domain.entity.Payment;
import com.example.shopping.product.domain.entity.Interest;
import com.example.shopping.review.domain.entity.QuestionAndAnswer;
import com.example.shopping.review.domain.entity.ReviewMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "members")
@AllArgsConstructor @NoArgsConstructor
@Builder
@Data
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberSeq;

    @Column(unique = true)
    private String id;
    private String password;
    private String username;
    private String phoneNum;


    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();

    private String address;

    @OneToMany(mappedBy = "member")
    private List<Delivery> deliveries;

    @OneToOne
    @JoinColumn(name = "point_seq")
    private Point point;

    @OneToOne
    @JoinColumn(name = "grade_seq")
    private Grade grade;

    @OneToOne
    @JoinColumn(name = "payment_seq")
    private Payment payment;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<ReviewMember> reviewMembers;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Interest> interests;

    @OneToMany(mappedBy = "member")
    @JsonIgnore // 순환 참조 끊기, member로 다시 순환해서 돌아올 때 끝내!
    private List<LoginHistory> loginHistory;

    @OneToMany(mappedBy = "member")
    private List<Cart> carts;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<QuestionAndAnswer> questionAndAnswers;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orders;

    @Override
    public String toString() {
        return "Member{" +
                "memberSeq=" + memberSeq +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", registrationDate=" + registrationDate +
                ", address='" + address + '\'' +
                // Skip 'deliveries', 'point', and 'grade' to avoid recursion
                '}';
    }

}
