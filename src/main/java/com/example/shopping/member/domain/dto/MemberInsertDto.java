package com.example.shopping.member.domain.dto;

import com.example.shopping.delivery.domain.entity.Delivery;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class MemberInsertDto {
    private String id;
    private String password;
    private String username;
    private LocalDateTime registrationDate;
    private String address;

//    # grade
    private String gradeName;

//    # point
    private Integer pointBalance;

//    # payment
    private int paymentAmount;
    private String paymentMethod;
    private String paymentStatus;
    private String cardType;
    private LocalDateTime paymentDate;

    @OneToMany(mappedBy = "members")
    private List<Delivery> deliveries;


}
