package com.example.shopping.member.domain.dto;

import com.example.shopping.delivery.domain.entity.Delivery;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data @Getter
@AllArgsConstructor @NoArgsConstructor
public class MemberInsertDto {
    private String id;
    private String password;
    private String username;
    private boolean gender;
    private LocalDateTime registrationDate;
    private String address;

    @OneToMany(mappedBy = "members")
    private List<Delivery> deliveries;
}
