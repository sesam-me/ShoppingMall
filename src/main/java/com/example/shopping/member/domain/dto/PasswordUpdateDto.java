package com.example.shopping.member.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class PasswordUpdateDto {
    private String currentPassword;
    private String updatePassword;
}
