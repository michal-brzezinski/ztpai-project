package com.example.demo.controller;

import com.example.demo.mapper.ProductMapper;
import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public List<ProductResponse> getAll() {
        return service.getAllProducts().stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id) {
        Product product = service.getProductById(id);
        return ProductMapper.toResponse(product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ProductResponse create(
            @Valid @RequestBody ProductRequest request) {
        Product product = service.createProduct(
                ProductMapper.toEntity(request)
        );
        return ProductMapper.toResponse(product);
    }

    // tylko ADMIN
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteProduct(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.updateProduct(id, product);
    }

}




