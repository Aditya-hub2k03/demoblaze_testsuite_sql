// File: src/main/java/com/yogesh/service/UserService.java
package com.yogesh.service;

import com.yogesh.dao.UserDAO;
import com.yogesh.model.User;
import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public void registerUser(User user) throws SQLException {
        userDAO.addUser(user);
    }

    public User loginUser(String username, String password) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
