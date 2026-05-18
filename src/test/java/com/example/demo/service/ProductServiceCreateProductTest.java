package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceCreateProductTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private ProductService service;

    @Test
    void createProduct_returnsProductWithId() {
        Product input = new Product();
        input.setName("Laptop");

        Product saved = new Product();
        saved.setId(1L);
        saved.setName("Laptop");

        when(repository.save(any(Product.class))).thenReturn(saved);

        Product result = service.createProduct(input);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    void createProduct_nullName_throwsException() {
        Product p = new Product();
        p.setName(null);

        assertThrows(IllegalArgumentException.class,
                () -> service.createProduct(p));

        verify(repository, never()).save(any());
    }

    @Test
    void createProduct_emptyName_throwsException() {
        Product p = new Product();
        p.setName("   ");

        assertThrows(IllegalArgumentException.class,
                () -> service.createProduct(p));

        verify(repository, never()).save(any());
    }
}
