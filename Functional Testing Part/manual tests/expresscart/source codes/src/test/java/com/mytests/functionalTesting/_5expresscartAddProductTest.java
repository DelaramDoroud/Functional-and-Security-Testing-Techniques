package com.mytests.functionalTesting;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.openqa.selenium.JavascriptExecutor;


public class _5expresscartAddProductTest  {

    @Test
    public void testAddProduct() {
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

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // And clicks the "+" icon to the right of the link "Products"
            WebElement addProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/admin/product/new']")));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", addProduct);

            // And enters "NewProduct000" in the "Product title" field
            WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productTitle")));
            titleField.sendKeys("NewProduct000");

            // And enters "15.95" in the "Product price" field
            WebElement priceField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productPrice")));
            priceField.sendKeys("15.95");

            // And enters "Description for product 000" in the "Product description" field
            WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='note-editable panel-body']")));
            descriptionField.sendKeys("Description for product 000");

            // And clicks the "Add product" button
            WebElement addProductButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frm_edit_product_save")));
            addProductButton.click();

            // And clicks the "Products" link
            WebElement productLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/admin/products']")));
            productLink.click();

            // Then "NewProduct" is shown in the first row of the table
            WebElement firstProductLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='list-group']/li[2]//div[@class='top-pad-8']/a")));
            String productName = firstProductLink.getText();
            assertEquals("NewProduct000", productName);

        } finally {
            driver.quit();
        }
    }
}
