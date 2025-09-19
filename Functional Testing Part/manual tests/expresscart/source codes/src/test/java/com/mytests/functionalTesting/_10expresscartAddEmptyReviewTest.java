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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class _10expresscartAddEmptyReviewTest {

    @Test
    public void testAddEmptyReview() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:3000/"); 

            Thread.sleep(1000);

            // When the user clicks on the account icon
            WebElement accountIcon = driver.findElement(By.cssSelector("a[href='/customer/account']"));
            accountIcon.click();

            // And enters "test@test.com" in the "email address" field
            WebElement emailField = driver.findElement(By.id("email")); 
            emailField.sendKeys("test@test.com");

            // And enters "e2eW3Bt3s71nGB3nchM4rK" in the "Password" field
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("e2eW3Bt3s71nGB3nchM4rK");

            // And clicks the "Sign in" button
            WebElement signInButton = driver.findElement(By.id("customerloginForm"));
            signInButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            Thread.sleep(1000);

            // And goes to the home page of the site
            WebElement homeLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home")));
            homeLink.click();

            Thread.sleep(1000);

            // And clicks the "NewProduct000" link
            WebElement prodLink = driver.findElement(By.cssSelector("a[href='/product/newproduct000']"));
            prodLink.click();

            Thread.sleep(1000);

            // And clicks the "Add review" button
            WebElement addReviewButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-review")));
            addReviewButton.click();

            Thread.sleep(1000);

            // And clicks the "Add review" button
            WebElement addReview = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addReview")));
            addReview.click();

            Thread.sleep(1000);

            // the alert is shown for about 2 seconds then disappears
            // Then "Please supply a review title" is shown in a red bar to the bottom of the screen
            WebElement showalert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notify_message")));
            assertTrue(showalert.isDisplayed());
            assertEquals("Please supply a review title", showalert.getText());
            boolean disappeared = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notify_message")));
            assertTrue(disappeared, "Alert did not disappear after expected time");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        
        finally {
            driver.quit();
        }
    }
}
