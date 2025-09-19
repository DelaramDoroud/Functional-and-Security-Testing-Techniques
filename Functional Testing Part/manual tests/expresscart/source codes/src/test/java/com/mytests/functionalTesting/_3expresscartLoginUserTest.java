package com.mytests.functionalTesting;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class _3expresscartLoginUserTest  {

    @Test
    public void testLoginUser() {
        WebDriver driver = new ChromeDriver();

       try {
            driver.get("http://localhost:3000/admin/login"); 

            // When the user enters "test000@test.com" in the "email address" field
            WebElement emailField = driver.findElement(By.id("email")); 
            emailField.sendKeys("test000@test.com");

            // And enters "e2eW3Bt3s71nGB3nchM4rK" in the "Password" field
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("password");

            // And clicks the "Sign in" button
            WebElement signInButton = driver.findElement(By.id("loginForm"));
            signInButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Then "Dashboard" is shown on the page
            WebElement showText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Dashboard']")));
            assertTrue(showText.isDisplayed());

            // Then the user clicks the "Logout" link
            WebElement logoutLink = driver.findElement(By.linkText("Logout"));
            logoutLink.click();

        } finally {
            driver.quit();
        }
    }
}
