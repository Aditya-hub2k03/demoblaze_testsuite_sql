// File: src/main/java/com/yogesh/model/Product.java
package com.yogesh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private String category;
    private double price;

    // Custom constructor for Product(String name, String category, double price)
    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
