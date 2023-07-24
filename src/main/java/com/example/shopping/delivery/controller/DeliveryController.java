package com.example.shopping.delivery.controller;

import com.example.shopping.delivery.domain.dto.DeliveryInsertDto;
import com.example.shopping.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deliveries")

public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/insert")
    public void deliveryInsert(@RequestBody DeliveryInsertDto deliveryInsertDto){
        deliveryService.deliveryInsert(deliveryInsertDto);
    }

//    @GetMapping
//    public List<DeliveryInsertDto> findAll(){
//        return
//    }
}
