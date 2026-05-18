package com.example.demo.dto;

public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private String description;

    // gettery, settery, konstruktory
    public ProductResponse(Long id, Double price, String name,  String description) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
