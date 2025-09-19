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

public class _9KanboardAddEmptySwimlaneTest {

   @Test
    public void testAddEmptySwimlane() {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:8080/login"); 

            // When the user enters "admin" in the "Username" field
            WebElement usernameField = driver.findElement(By.id("form-username")); 
            usernameField.sendKeys("admin");

            // And enters "admin" in the "Password" field
            WebElement passwordField = driver.findElement(By.id("form-password"));
            passwordField.sendKeys("admin");

            // And clicks the "Sign in" button
            WebElement signInButton = driver.findElement(By.xpath("//button[contains(@class, 'btn-blue')]"));
            signInButton.click();

            // And clicks the "#1" icon to the left of "Test 2"
            WebElement _1Icon = driver.findElement(By.xpath("//strong[contains(normalize-space(.), '#1')]"));
            _1Icon.click();

            // And clicks the "Configure this project" link
            WebElement configText = driver.findElement(By.linkText("Configure this project"));
            configText.click();

            // And clicks the "Swimlanes" link
            WebElement swimlaane = driver.findElement(By.linkText("Swimlanes"));
            swimlaane.click();

            // And clicks the "Add a new swimlane" link
            WebElement newSwimlaane = driver.findElement(By.linkText("Add a new swimlane"));
            newSwimlaane.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // And clicks the "Save" button
            WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'btn-blue')]")));
            saveButton.click();

            // Then "The name is required" is shown below the "Name" field
            WebElement errorText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("form-errors")));
            String actualError = errorText.findElement(By.tagName("li")).getText().trim();
            String expectedError = "The name is required";
            assertEquals(expectedError, actualError);

            WebElement closeIcon = driver.findElement(By.id("modal-close-button"));
            closeIcon.click();

            // Then the user clicks on the "A" icon in the top-right corner of the screen
            WebElement aIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("avatar-letter")));
            aIcon.click();

            // And clicks the "Logout" link
            WebElement logoutLink = driver.findElement(By.linkText("Logout"));
            logoutLink.click();

        } finally {
            driver.quit();
        }
    }
}
