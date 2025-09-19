package com.mytests.functionalTesting;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class _2expresscartAddEmptyUserTest  {

    @Test
    public void testAddEmptyUser() {
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

            // And clicks the "+" icon to the right of the link "Users"
            WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/admin/user/new']")));
            addUser.click();

            // And clicks the "Create" button
            WebElement createButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnUserAdd")));
            createButton.click();

            // Then all the fields are highlighted in red
            List<WebElement> showError = driver.findElements(By.cssSelector("div.form-group.has-error.has-danger"));
            assertTrue(showError.size() == 4, "Expected 4 fields highlighted in red, but found: " + showError.size());

            // Then the user clicks the "Logout" link
            WebElement logoutLink = driver.findElement(By.linkText("Logout"));
            logoutLink.click();

        } finally {
            driver.quit();
        }
    }
}
