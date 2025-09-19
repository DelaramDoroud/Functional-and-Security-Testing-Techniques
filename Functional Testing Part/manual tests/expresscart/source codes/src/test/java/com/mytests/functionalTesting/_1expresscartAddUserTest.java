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

public class _1expresscartAddUserTest  {

    @Test
    public void testAddUser() {
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

            // And clicks the "+" icon to the right of the link "Users"
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement addUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/admin/user/new']")));
            addUser.click();

            // And enters "TestUser000" in the "Users name" field
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usersName")));
            usernameField.sendKeys("TestUser000");

            // And enters "test000@test.com" in the "User email" field
            WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail")));
            email.sendKeys("test000@test.com");

            // And enters "password" in the "User password" field
            WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userPassword")));
            password.sendKeys("password");

            // And enters "password" in the "Password confirm" field
            WebElement confirmPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-match='#userPassword']")));
            confirmPassword.sendKeys("password");

            // And clicks the "Create" button
            WebElement createButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnUserAdd")));
            createButton.click();

            // And clicks the "Users" link
            WebElement userLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[href='/admin/users']")));
            userLink.click();

            // Then "User: TestUser000 - (test000@test.com)\nRole: User" is shown in the third row of the table
            WebElement showText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(normalize-space(.), 'TestUser000 - (test000@test.com)')]")));
            assertTrue(showText.isDisplayed());

            // Then the user clicks the "Logout" link
            WebElement logoutLink = driver.findElement(By.linkText("Logout"));
            logoutLink.click();

        } finally {
            driver.quit();
        }
    }
}
