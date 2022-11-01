package com.example.lab08.query;

import com.example.lab08.core.ProductEntity;
import com.example.lab08.core.data.ProductRepository;
import com.example.lab08.core.event.ProductCreatedEvent;
import com.example.lab11.command.event.ProductReserveEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {

    private final ProductRepository productRepository;

    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        productRepository.save(productEntity);
    }

    @EventHandler
    public void on(ProductReserveEvent productReserveEvent) {
        ProductEntity productEntity = productRepository.findByProductId(productReserveEvent.getProductId());
        productEntity.setQuantity(productEntity.getQuantity() - productReserveEvent.getQuantity());
        productRepository.save(productEntity);
    }
}
