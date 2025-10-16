// File: src/test/java/com/yogesh/testcases/SignUpLoginTest.java
package com.yogesh.testcases;

import com.yogesh.model.User;
import com.yogesh.service.UserService;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.time.Duration;

public class SignUpLoginTest extends BaseTest {
    @Test
    public void testSignUpAndLogin() throws SQLException {
        test = extent.createTest("Sign Up and Login Test");
        UserService userService = new UserService();
        String username = "testuser" + System.currentTimeMillis();
        String password = "testpass";

        // Sign Up
        signUp(username, password);

        // Register user in DB
        User user = new User(username, password);
        userService.registerUser(user);

        // Login
        login(username, password);

        // Verify login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertEquals(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser"))).getText(), "Welcome " + username);
    }
}
