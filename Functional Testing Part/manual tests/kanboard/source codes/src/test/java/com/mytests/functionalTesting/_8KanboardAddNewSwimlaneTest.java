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

public class _8KanboardAddNewSwimlaneTest  {

    @Test
    public void AddNewSwimlane() {
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

            // And enters "New Swimlane 3" in the "Name" field
            WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("form-name")));
            titleField.sendKeys("New Swimlane 3");

            // And clicks the "Save" button
            WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'btn-blue')]")));
            saveButton.click();

            // Then "New Swimlane 3" is shown in the last row of the table
            WebElement showText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(normalize-space(.),'New Swimlane 3')]")));
            assertTrue(showText.isDisplayed());

            WebElement aIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("avatar-letter")));
            aIcon.click();

            WebElement logoutLink = driver.findElement(By.linkText("Logout"));
            logoutLink.click();

        } finally {
            driver.quit();
        }
    }
}
