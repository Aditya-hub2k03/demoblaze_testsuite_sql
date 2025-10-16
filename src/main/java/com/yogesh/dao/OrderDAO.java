// File: src/main/java/com/yogesh/dao/OrderDAO.java
package com.yogesh.dao;

import com.yogesh.model.Order;
import com.yogesh.util.DBUtil;
import java.sql.*;

public class OrderDAO {
    public void addOrder(Order order) throws SQLException {
        String query = "INSERT INTO orders(user_id, product_id, quantity, total_price) VALUES(?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getUserId());
            stmt.setInt(2, order.getProductId());
            stmt.setInt(3, order.getQuantity());
            stmt.setDouble(4, order.getTotalPrice());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    order.setId(rs.getInt(1));
                }
            }
        }
    }

    public double getTotalPriceForUser(int userId) throws SQLException {
        String query = "SELECT SUM(total_price) AS total FROM orders WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total");
                }
            }
        }
        return 0.0;
    }

    public boolean orderExistsForUser(int userId) throws SQLException {
        String query = "SELECT COUNT(*) FROM orders WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }
}
