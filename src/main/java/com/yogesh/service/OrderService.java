// File: src/main/java/com/yogesh/service/OrderService.java
package com.yogesh.service;

import com.yogesh.dao.OrderDAO;
import com.yogesh.model.Order;
import java.sql.SQLException;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

    public void placeOrder(Order order) throws SQLException {
        orderDAO.addOrder(order);
    }

    public double getTotalPriceForUser(int userId) throws SQLException {
        return orderDAO.getTotalPriceForUser(userId);
    }

    public boolean orderExistsForUser(int userId) throws SQLException {
        return orderDAO.orderExistsForUser(userId);
    }
}
