// File: src/main/java/com/yogesh/model/Order.java
package com.yogesh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    private double totalPrice;

    // Custom constructor for Order(int userId, int productId, int quantity, double totalPrice)
    public Order(int userId, int productId, int quantity, double totalPrice) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
