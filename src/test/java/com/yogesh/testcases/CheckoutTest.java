// File: src/test/java/com/yogesh/testcases/CheckoutTest.java
package com.yogesh.testcases;

import com.yogesh.model.Order;
import com.yogesh.model.Product;
import com.yogesh.model.User;
import com.yogesh.service.OrderService;
import com.yogesh.service.ProductService;
import com.yogesh.service.UserService;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.time.Duration;

public class CheckoutTest extends BaseTest {
    @Test
    public void testCheckout() throws SQLException {
        test = extent.createTest("Checkout Test");
        UserService userService = new UserService();
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();

        String username = "testuser" + System.currentTimeMillis();
        String password = "testpass";

        // Sign Up
        signUp(username, password);

        // Register user in DB
        User user = new User(username, password);
        userService.registerUser(user);

        // Add a product to DB
        Product product = new Product("Samsung galaxy s6", "Phones", 360.00);
        productService.addProduct(product);

        // Login
        login(username, password);

        // Add items to cart
        driver.findElement(By.linkText("Samsung galaxy s6")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();
        handleAlert();

        // Place order
        driver.findElement(By.id("cartur")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='Samsung galaxy s6']")));
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();

        // Fill out order form
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("Test User");
        driver.findElement(By.id("country")).sendKeys("India");
        driver.findElement(By.id("city")).sendKeys("Hyderabad");
        driver.findElement(By.id("card")).sendKeys("123456789");
        driver.findElement(By.id("month")).sendKeys("12");
        driver.findElement(By.id("year")).sendKeys("2025");
        driver.findElement(By.xpath("//button[text()='Purchase']")).click();

        // Verify order confirmation on UI
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Thank you for your purchase!']")));
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Thank you for your purchase!']")).isDisplayed());

        // Create an order in DB for verification
        Order order = new Order(user.getId(), product.getId(), 1, product.getPrice());
        orderService.placeOrder(order);

        // Verify order exists in DB
        Assert.assertTrue(orderService.orderExistsForUser(user.getId()));
    }
}
