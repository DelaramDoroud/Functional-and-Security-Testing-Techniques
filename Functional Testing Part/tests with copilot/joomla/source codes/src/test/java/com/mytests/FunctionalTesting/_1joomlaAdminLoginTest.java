package com.mytests.FunctionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.*;

class _1joomlaAdminLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        // Set path to chromedriver if not in PATH
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testAdminLoginAndLogout() {
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

        // Then "Super User" is shown as value of the "Name" field
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jform_name")));
        assertEquals("Super User", nameField.getAttribute("value"));

        // Given the previous assertion passed
        // Then the user clicks the "Log out" link
        WebElement logoutLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log out")));
        logoutLink.click();

        // And clicks the "Log out" button
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        logoutButton.click();

    }
}