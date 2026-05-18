package com.example.demo.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener {

    @Async
    @EventListener
    public void handleProductCreated(ProductCreatedEvent event) {
        System.out.println("❗❗❗ Product created: " + event.getProduct().getName()+ " ❗❗❗");
    }
}
