package com.example.shopping.member.controller;

import com.example.shopping.member.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
@CrossOrigin("*")
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

    @PostMapping("/test/{id}")
    public String selfVerificationTest(@PathVariable String id){

        try {
            return  mailService.sendVerificationMessage(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}