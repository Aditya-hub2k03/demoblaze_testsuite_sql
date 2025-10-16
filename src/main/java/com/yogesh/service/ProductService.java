// File: src/main/java/com/yogesh/service/ProductService.java
package com.yogesh.service;

import com.yogesh.dao.ProductDAO;
import com.yogesh.model.Product;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public void addProduct(Product product) throws SQLException {
        productDAO.addProduct(product);
    }

    public List<Product> getProductsByCategory(String category) throws SQLException {
        return productDAO.getProductsByCategory(category);
    }
}
