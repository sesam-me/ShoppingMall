package com.example.shopping.member.controller;

import com.example.shopping.member.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class MailController {
    private final MailService mailService;
    @PostMapping("{email}")
    public String mailTest(@PathVariable String email) {
        try {
            return mailService.sendSimpleMessage(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}