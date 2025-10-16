// File: src/test/java/com/yogesh/testcases/CategoryNavigationTest.java
package com.yogesh.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CategoryNavigationTest extends BaseTest {
    @Test
    public void testCategoryNavigation() {
        test = extent.createTest("Category Navigation Test");
        driver.get("https://www.demoblaze.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to Phones
        driver.findElement(By.linkText("Phones")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Samsung galaxy s6")));
        Assert.assertTrue(driver.findElement(By.linkText("Samsung galaxy s6")).isDisplayed());

        // Navigate to Laptops
        driver.findElement(By.linkText("Laptops")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sony vaio i5")));
        Assert.assertTrue(driver.findElement(By.linkText("Sony vaio i5")).isDisplayed());

        // Navigate to Monitors
        driver.findElement(By.linkText("Monitors")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Apple monitor 24")));
        Assert.assertTrue(driver.findElement(By.linkText("Apple monitor 24")).isDisplayed());
    }
}
