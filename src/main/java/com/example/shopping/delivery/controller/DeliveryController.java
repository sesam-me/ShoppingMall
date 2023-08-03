package com.example.shopping.delivery.controller;

import com.example.shopping.delivery.domain.dto.DeliveryInsertDto;
import com.example.shopping.delivery.domain.entity.Delivery;
import com.example.shopping.delivery.domain.response.DeliveryResponse;
import com.example.shopping.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deliveries")
@CrossOrigin("*")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/insert")
    public void deliveryInsert(@RequestBody DeliveryInsertDto deliveryInsertDto){

        deliveryService.deliveryInsert(deliveryInsertDto);
    }

    @GetMapping
    public List<DeliveryResponse> findAll(){
        return deliveryService.findAll();
        //TODO DeliveryResponse 만들기
    }

    @GetMapping("{memberSeq}")
    public List<Delivery> findByMemerSeq(@PathVariable Long memberSeq) {
        return deliveryService.findByMemerSeq(memberSeq);
    }
}
