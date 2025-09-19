package com.mytests.FunctionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class _9joomlaAddEmptyArticleTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testAddEmptyArticleShowsError() {
        // Given the user is on the home page
        driver.get("http://localhost:8080/index.php/author-login");

        // When the user enters "administrator" in the "Username" field
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameField.clear();
        usernameField.sendKeys("administrator");

        // And enters "e2eW3Bt3s71nGB3nchM4rK" in the "Password" field
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys("e2eW3Bt3s71nGB3nchM4rK");

        // And clicks the "Sign in" button
        WebElement signInButton = driver.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        // And clicks the "Create a Post" link
        WebElement createPostLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Create a Post")));
        createPostLink.click();

        // And clicks the "Save" button without entering title or body
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'btn btn-primary')]")));
        saveButton.click();

        // Then "Invalid field: Title" is shown in a red box
        WebElement errorBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#system-message-container div.alert.alert-error.alert-danger")));
        String errorMsg = errorBox.findElement(By.tagName("div")).getText().trim();
        assertEquals("Invalid field:  Title" , errorMsg );
    }
}