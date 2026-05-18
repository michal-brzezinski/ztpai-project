package com.example.demo.service;

import com.example.demo.event.ProductCreatedEvent;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.*;

class ProductServiceEventTest {

    @Test
    void createProduct_publishesEvent() {
        ProductRepository repo = mock(ProductRepository.class);
        ApplicationEventPublisher publisher = mock(ApplicationEventPublisher.class);

        ProductService service = new ProductService(repo, publisher);

        Product p = new Product();
        p.setName("Test");

        Product saved = new Product();
        saved.setId(1L);
        saved.setName("Test");

        when(repo.save(any())).thenReturn(saved);

        service.createProduct(p);

        ArgumentCaptor<ProductCreatedEvent> captor =
                ArgumentCaptor.forClass(ProductCreatedEvent.class);

        verify(publisher).publishEvent(captor.capture());
        Assertions.assertEquals("Test", captor.getValue().getProduct().getName());
    }
}
