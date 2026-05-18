package com.example.demo.mapper;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.model.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequest req) {
        Product p = new Product();
        p.setName(req.getName());
        p.setPrice(req.getPrice());
        p.setDescription(req.getDescription());
        return p;
    }

    public static ProductResponse toResponse(Product p) {
        ProductResponse res = new ProductResponse(p.getId(), p.getPrice(), p.getName(), p.getDescription());
        res.setId(p.getId());
        res.setName(p.getName());
        res.setPrice(p.getPrice());
        res.setDescription(p.getDescription());
        return res;
    }
}