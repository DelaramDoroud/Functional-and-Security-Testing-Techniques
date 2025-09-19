package com.mytests.functionalTesting;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;


import static org.junit.jupiter.api.Assertions.*;

public class _2KanboardAddEmptyProjectTest {

    @Test
    public void AddEmptyProject() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:8080/login"); 

            // When the user enters "admin" in the "Username" field
            WebElement usernameField = driver.findElement(By.id("form-username")); 
            usernameField.sendKeys("admin");

            // And enters "admin" in the "Password" field
            WebElement passwordField = driver.findElement(By.id("form-password"));
            passwordField.sendKeys("admin");

            // And clicks thre "Sign in" button
            WebElement signInButton = driver.findElement(By.xpath("//button[contains(@class, 'btn-blue')]"));
            signInButton.click();

            // And clicks the "New project" link
            WebElement newProjectLink = driver.findElement(By.linkText("New project"));
            newProjectLink.click();

            // And clicks the "Save" button
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'btn-blue')]")));
            saveButton.click();

            // Then "The project name is required" is show below the "Name" field
            WebElement errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("form-errors")));
            String actualError = errorText.findElement(By.tagName("li")).getText().trim();
            String expectedError = "The project name is required";
            assertEquals(expectedError, actualError);

            WebElement closeIcon = driver.findElement(By.id("modal-close-button"));
            closeIcon.click();

            // Then the user clicks on the "A" icon in the top-right corner of the screen
            WebElement aIcon = driver.findElement(By.className("avatar-letter"));
            aIcon.click();

            // And clicks the "Logout" link
            WebElement logoutLink = driver.findElement(By.linkText("Logout"));
            logoutLink.click();

        } finally {
            driver.quit();
        }
    }
}
