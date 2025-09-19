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

public class _5KanboardEditProjectTest {

    @Test
    public void testEditProject() {
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

            // And clicks the "#1" icon to the left of "Test 2"
            WebElement _1Icon = driver.findElement(By.xpath("//strong[contains(text(), '#1 ')]"));
            _1Icon.click();

            // And clicks the "Configure this project" link
            WebElement configText = driver.findElement(By.linkText("Configure this project"));
            configText.click();

            // And clicks the "Edit project" link
            WebElement editText = driver.findElement(By.linkText("Edit project"));
            editText.click();

            // And enters "This is the new description" in the "Description" field
            WebElement descriptionField = driver.findElement(By.name("description"));
            descriptionField.sendKeys("This is the new description");

            // And clicks the "Save" button
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'btn-blue')]")));
            saveButton.click();

            // And clicks the "Summary" link
            WebElement summary = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Summary")));
            summary.click();

            // Then "This is the new description" is shown below "Description"
            WebElement projectStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'This is the new description')]")));
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
