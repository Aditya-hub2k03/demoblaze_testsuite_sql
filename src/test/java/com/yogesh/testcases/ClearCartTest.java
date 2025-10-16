// File: src/test/java/com/yogesh/testcases/ClearCartTest.java
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

public class ClearCartTest extends BaseTest {
    @Test
    public void testClearCartAndVerifyTotal() throws SQLException {
        test = extent.createTest("Clear Cart and Verify Total Test");
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

        // Add items to cart
        driver.findElement(By.linkText("Samsung galaxy s6")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();
        handleAlert();

        // Go to cart
        driver.findElement(By.id("cartur")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Samsung galaxy s6']")));

        // Delete item from cart
        driver.findElement(By.xpath("//a[contains(text(), 'Delete')]")).click();

        // Wait for the cart to update
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[text()='Samsung galaxy s6']")));

        // Verify cart is empty
        Assert.assertFalse(driver.findElements(By.xpath("//td[text()='Samsung galaxy s6']")).size() > 0);
    }
}
