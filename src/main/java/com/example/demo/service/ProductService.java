package com.example.demo.service;

import com.example.demo.event.ProductCreatedEvent;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ApplicationEventPublisher publisher;

    public ProductService(ProductRepository productRepository,
                          ApplicationEventPublisher publisher) {
        this.repository = productRepository;
        this.publisher = publisher;
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product updateProduct(Long id, Product product) {
        product.setId(id); // update zamiast insert
        return repository.save(product);
    }

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"));
    }

    public void deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found");
        }

        repository.deleteById(id);
    }

    public Product createProduct(Product product) {

        if (product.getName() == null ||
                product.getName().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Product name cannot be null or empty");
        }

        System.out.println(">>> PRODUCT RECEIVED:");
        System.out.println("name = " + product.getName());
        System.out.println("price = " + product.getPrice());
        System.out.println("description = " + product.getDescription());

        // zapis produktu
        Product saved = repository.save(product);

        // publikacja eventu
        publisher.publishEvent(new ProductCreatedEvent(saved));

        return saved;
    }
}