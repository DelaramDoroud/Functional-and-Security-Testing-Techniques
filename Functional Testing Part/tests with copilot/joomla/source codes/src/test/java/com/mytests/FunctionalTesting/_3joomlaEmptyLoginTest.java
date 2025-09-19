package com.mytests.FunctionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.*;

class _3joomlaEmptyLoginTest {

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
    void testEmptyLoginShowsHtml5Validation() {
        // Given the user is on the home page
        driver.get("http://localhost:8080/");

        // When the user clicks the "Author Login" link
        WebElement authorLoginLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Author Login")));
        authorLoginLink.click();

        // And clicks the "Log in" button without entering credentials
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        loginButton.click();

        // Then "Please fill out this field." is shown as a HTML 5 validation message
        WebElement usernameField = driver.findElement(By.id("username"));
        String validationMessage = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", usernameField);

        assertEquals("Please fill out this field.", validationMessage);
    }
}