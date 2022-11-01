package com.example.lab11.command.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductReserveEvent {

    private final String productId;
    private final int quantity;
    private final String orderId;
    private final String userId;
}
