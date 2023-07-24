package com.example.shopping.delivery.controller;

import com.example.shopping.delivery.domain.dto.DeliveryInsertDto;
import com.example.shopping.delivery.domain.entity.Delivery;
import com.example.shopping.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deliveries")

public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/insert")
    public void deliveryInsert(@RequestBody DeliveryInsertDto deliveryInsertDto){

        deliveryService.deliveryInsert(deliveryInsertDto);
    }

    @GetMapping
    public List<Delivery> findAll(){
        return deliveryService.findAll();
        //TODO  DeliveryReponse 만들기
    }
}
