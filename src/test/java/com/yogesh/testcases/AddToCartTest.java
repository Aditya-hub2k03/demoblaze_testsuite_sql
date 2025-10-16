// File: src/test/java/com/yogesh/testcases/AddToCartTest.java
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

public class AddToCartTest extends BaseTest {
    @Test
    public void testLoginAndAddToCart() throws SQLException {
        test = extent.createTest("Login and Add to Cart Test");
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

        // Add to cart
        driver.findElement(By.linkText("Samsung galaxy s6")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();
        handleAlert();

        // Verify cart
        driver.findElement(By.id("cartur")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Samsung galaxy s6']")));
        Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Samsung galaxy s6']")).isDisplayed());
    }
}
