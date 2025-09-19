package com.mytests.FunctionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.*;

class _2joomlaBadLoginTest {

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
    void testBadLoginShowsErrorMessage() {
        // Given the user is on the home page
        driver.get("http://localhost:8080/");

        // When the user clicks the "Author Login" link
        WebElement authorLoginLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Author Login")));
        authorLoginLink.click();

        // And enters "administrator" in the "Username" field
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        usernameField.clear();
        usernameField.sendKeys("administrator");

        // And enters "wrongpassword" in the "Password" field
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.clear();
        passwordField.sendKeys("wrongpassword");

        // And clicks the "Log in" button
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        loginButton.click();

        // Then error message is shown in a yellow box
        WebElement errorBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert') and contains(text(),'Username and password')]")));
        String errorMsg = errorBox.getText();
        assertTrue(errorMsg.contains("Username and password do not match or you do not have an account yet."));
    }
}

