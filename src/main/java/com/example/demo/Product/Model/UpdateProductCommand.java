package com.example.demo.Product.Model;

import lombok.Data;

@Data
public class UpdateProductCommand {
    private String id;
    private Product product;

    public UpdateProductCommand(String id, Product product) {
        this.id = id;
        this.product = product;
    }
}
