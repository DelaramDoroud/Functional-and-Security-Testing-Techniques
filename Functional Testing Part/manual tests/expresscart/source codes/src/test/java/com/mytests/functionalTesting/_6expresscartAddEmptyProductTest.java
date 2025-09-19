package com.mytests.functionalTesting;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class _6expresscartAddEmptyProductTest  {

    @Test
    public void testAddEmptyProduct() {
        WebDriver driver = new ChromeDriver();

       try {
            driver.get("http://localhost:3000/admin/login"); 

            // When the user enters "owner@test.com" in the "email address" field
            WebElement emailField = driver.findElement(By.id("email")); 
            emailField.sendKeys("owner@test.com");

            // And enters "e2eW3Bt3s71nGB3nchM4rK" in the "Password" field
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("e2eW3Bt3s71nGB3nchM4rK");

            // And clicks the "Sign in" button
            WebElement signInButton = driver.findElement(By.id("loginForm"));
            signInButton.click();

            // And clicks the "+" icon to the right of the link "Products"
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement addProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/admin/product/new']")));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", addProduct);

            // And clicks the "Add product" button
            WebElement addProductButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frm_edit_product_save")));
            addProductButton.click();

            // Then the fileds "Product title" and "Product price" are highlighted in red
            WebElement productTitleDiv = driver.findElement(By.cssSelector("input#productTitle")).findElement(By.xpath("./ancestor::div[contains(@class,'form-group')]"));
            String classesTitle = productTitleDiv.getAttribute("class");
            assertTrue(classesTitle.contains("has-error"));
            assertTrue(classesTitle.contains("has-danger"));

        } finally {
            driver.quit();
        }
    }
}
