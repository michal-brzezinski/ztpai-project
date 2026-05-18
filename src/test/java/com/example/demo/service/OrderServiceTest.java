package com.example.demo.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private final OrderService orderService = new OrderService();

    @Test
    void calculateTotal_correctSum() {
        double result = orderService.calculateTotal(List.of(10.0, 20.0, 30.0));
        assertEquals(60.0, result);
    }

    @Test
    void calculateTotal_emptyList_returnsZero() {
        double result = orderService.calculateTotal(List.of());
        assertEquals(0.0, result);
    }

    @Test
    void calculateTotal_null_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> orderService.calculateTotal(null));
    }
}
