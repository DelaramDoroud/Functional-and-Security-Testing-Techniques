package com.mytests.FunctionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.*;

class _5joomlaLoginAsNewUserTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testLoginAsNewUser() {
        // Given the user is on the home page
        driver.get("http://localhost:8080/index.php/author-login");

        // When the user enters "tuser01" in the "Username" field
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameField.clear();
        usernameField.sendKeys("tuser01");

        // And enters "tpassword" in the "Password" field
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys("tpassword");

        // And clicks the "Sign in" button
        WebElement signInButton = driver.findElement(By.cssSelector("button[type='submit']"));
        signInButton.click();

        // Then "Test User" is shown as value of the "Name" field
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jform_name")));
        assertEquals("Test User", nameField.getAttribute("value"));

        // Given the previous assertion passed
        // Then the user clicks the "Author Login" link
        WebElement authorLoginLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Author Login")));
        authorLoginLink.click();

        // And clicks the "Log out" button
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        logoutButton.click();
    }
}