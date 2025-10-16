// File: src/main/java/com/yogesh/model/User.java
package com.yogesh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;

    // Custom constructor for User(String username, String password)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
