package com.example.shopping.domain.request;

import com.example.shopping.domain.entity.InventoryAlert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryAlertRequest {
    private Integer alert_count;

    public InventoryAlert toEntity() {
        return InventoryAlert.builder()
                .alert_count(alert_count)
                .build();
    }
}
