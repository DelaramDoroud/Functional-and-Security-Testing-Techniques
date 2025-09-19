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

public class _4expresscartAddExistingUserFailsTest  {

    @Test
    public void testAddExistingUserFails() {
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

            // And enters "TestUser000" in the "Users name" field
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usersName")));
            usernameField.sendKeys("TestUser000");

            // And enters "test000@test.com" in the "User email" field
            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail")));
            email.sendKeys("test000@test.com");

            // And enters "e2eW3Bt3s71nGB3nchM4rK" in the "User password" field
            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userPassword")));
            password.sendKeys("e2eW3Bt3s71nGB3nchM4rK");

            // And enters "e2eW3Bt3s71nGB3nchM4rK" in the "Password confirm" field
            WebElement confirmPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-match='#userPassword']")));
            confirmPassword.sendKeys("e2eW3Bt3s71nGB3nchM4rK");

            // And clicks the "Create" button
            WebElement createButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnUserAdd")));
            createButton.click();

            // alert is shown for about 2 seconds then disappears
            // Then "A user with that email address already exists" is shown in a red alert at the bottom of the table
            WebElement errorAlert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("notify_message")));
            assertTrue(errorAlert.isDisplayed());
            assertEquals("A user with that email address already exists", errorAlert.getText());
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            boolean disappeared = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("notify_message")));
            assertTrue(disappeared, "Alert did not disappear after expected time");

            // Then the user clicks the "Logout" link
            WebElement logoutLink = driver.findElement(By.linkText("Logout"));
            logoutLink.click();

        } finally {
            driver.quit();
        }
    }
}
