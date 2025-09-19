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

public class _9expresscartAddReviewTest {

    @Test
    public void testAddReview() {
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

            // And enters "Review000" in the "Title:" field
            WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("review-title")));
            titleField.sendKeys("Review000");

            Thread.sleep(1000);

            // And enters "Description000" in the "Description:" field
            WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("review-description")));
            descriptionField.sendKeys("Description000");

            Thread.sleep(1000);

            // And enters "5" in the "Rating:" field
            WebElement ratingFiled = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("review-rating")));
            ratingFiled.sendKeys("5");

            Thread.sleep(1000);

            // And clicks the "Add review" button
            WebElement addButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addReview")));
            addButton.click();

            Thread.sleep(1000);

            // And clicks on "Recent reviews"
            WebElement reviewsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Recent reviews")));
            reviewsLink.click();
            
            // And "Rating: 5" is shown on the page
            WebElement showRate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[b[text()='Rating:']]")));
            String ratingText = showRate.getText().trim();
            assertEquals("Rating: 5", ratingText);

            // Then "Title: Review000" is shown on the page
            WebElement showtitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[b[text()='Title:']]")));
            String titleText = showtitle.getText().trim();
            assertEquals("Title: Review000", titleText);

            // And "Description: Description000" is shown on the page
            WebElement showDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[b[text()='Description:']]")));
            String DescriptionText = showDescription.getText().trim();
            assertEquals("Description: Description000", DescriptionText);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        
        finally {
            driver.quit();
        }
    }
}
