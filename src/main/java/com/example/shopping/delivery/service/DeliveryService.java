package com.example.shopping.delivery.service;

import com.example.shopping.delivery.domain.dto.DeliveryInsertDto;
import com.example.shopping.delivery.domain.entity.Delivery;
import com.example.shopping.delivery.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public void deliveryInsert(DeliveryInsertDto deliveryInsertDto){
        Delivery delivery = Delivery.builder().deliveryDate(deliveryInsertDto.getDeliveryDate()).deliverStatus(deliveryInsertDto.getDeliverStatus()).recipientInformation(deliveryInsertDto.getRecipientInformation()).deliveryMethod(deliveryInsertDto.getDeliveryMethod()).deliveryFee(deliveryInsertDto.getDeliveryFee()).recipientAddress(deliveryInsertDto.getRecipientAddress()).recipientPhoneNumber(deliveryInsertDto.getRecipientPhoneNumber()).build();
        deliveryRepository.save(delivery);
    }
}
