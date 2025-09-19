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

public class _7expresscartAddNewProdToCartTest {

    @Test
    public void testAddNewProdToCart() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:3000/"); 

            Thread.sleep(1000);

            // When the user clicks the "NewProduct000" link
            WebElement prodLink = driver.findElement(By.cssSelector("a[href='/product/newproduct000']"));
            prodLink.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            Thread.sleep(1000);

            // And clicks the "Add to cart" button
            WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add to cart']")));
            addToCartButton.click();

            Thread.sleep(1000);

            // And clicks the "Home" link
            WebElement homeLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home")));
            homeLink.click();

            Thread.sleep(1000);

            // Then "1" is shown in the red square to the right of the "Cart" link
            WebElement cartCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart-count")));
            String productCount = cartCount.getText();
            assertEquals("1", productCount);

            Thread.sleep(1000);

            // When the user clicks on the "Cart" link
            WebElement cartLink = driver.findElement(By.cssSelector("a[href='/checkout/cart']"));
            cartLink.click();

            Thread.sleep(1000);

            // Then "NewProduct000" is shown in the "Cart contents"
            WebElement prodName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/product/newproduct000']")));
            String product = prodName.getText();
            assertEquals("NewProduct000", product);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
