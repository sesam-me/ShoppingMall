package com.example.shopping.member.domain.Response;

import com.example.shopping.delivery.domain.entity.Delivery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class MemberLoginResponse {
    private Long memberSeq;
    private String id;
    private String address;
    private String username;
    private String phoneNum;
    private List<MemberResponse.DeliveryDto> deliveries;
    private Boolean isLogin;



}
