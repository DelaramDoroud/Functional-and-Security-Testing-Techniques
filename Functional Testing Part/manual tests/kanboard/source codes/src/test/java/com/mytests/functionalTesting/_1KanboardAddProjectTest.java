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

public class _1KanboardAddProjectTest {

    @Test
    public void testAddNewProject() {
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

            // And enter "Test 2" in the "Name" field
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("form-name")));
            nameField.sendKeys("Test 2");

            // And clicks the "Save" button
            WebElement saveButton = driver.findElement(By.xpath("//button[contains(@class, 'btn-blue')]"));
            saveButton.click();

            // Then "Test 2" is shown to the right of the "KB" logo
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement projectName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
            assertTrue(projectName.isDisplayed());

            // And "This project is open" is shown below "Summary"
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement projectStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'This project is open')]")));
            assertTrue(projectStatus.isDisplayed());

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
