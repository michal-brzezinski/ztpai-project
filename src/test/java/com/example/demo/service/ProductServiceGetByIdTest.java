package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceGetByIdTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ApplicationEventPublisher publisher;

    @InjectMocks
    private ProductService service;

    @Test
    void getProductById_returnsProduct() {
        Product p = new Product();
        p.setId(1L);
        p.setName("Test");

        when(repository.findById(1L)).thenReturn(Optional.of(p));

        Product result = service.getProductById(1L);

        assertNotNull(result);
        assertEquals("Test", result.getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void getProductById_notFound_throwsException() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () -> service.getProductById(99L));

        verify(repository, times(1)).findById(99L);
    }
}
