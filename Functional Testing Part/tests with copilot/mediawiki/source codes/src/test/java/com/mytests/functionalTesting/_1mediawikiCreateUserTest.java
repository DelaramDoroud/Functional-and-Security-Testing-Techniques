package com.mytests.functionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

class _1mediawikiCreateUserTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "http://localhost:8080/index.php/Main_Page"; // Change to your MediaWiki URL

    @BeforeEach
    void setUp() {
        // Set the path to your chromedriver if not in PATH
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void createsNewUser() throws InterruptedException {
        // Given the user is on the home page
        driver.get(baseUrl);

        // When the user clicks the "Log in" link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log in"))).click();

        // And enters "admin" in the "Username" field
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpName1"))).sendKeys("admin");

        // And enters "e2eW3Bt3s71nGB3nchM4rK" in the "Password" field
        driver.findElement(By.id("wpPassword1")).sendKeys("e2eW3Bt3s71nGB3nchM4rK");

        // And clicks the "Log in" button
        driver.findElement(By.id("wpLoginAttempt")).click();

        // And clicks the "Special pages" link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Special pages"))).click();

        // And clicks the "Create account" link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Create account"))).click();

        // And enters "User001" in the "Username" field
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpName2"))).sendKeys("User004");

        // And enters "e2eW3Bt3s71nGB3nchM4rK" in the "Password" field
        driver.findElement(By.id("wpPassword2")).sendKeys("e2eW3Bt3s71nGB3nchM4rK");

        // And enters "e2eW3Bt3s71nGB3nchM4rK" in the "Confirm Password" field
        driver.findElement(By.id("wpRetype")).sendKeys("e2eW3Bt3s71nGB3nchM4rK");

        // And enters "Real Name 001" in the "Real Name" field
        driver.findElement(By.id("wpRealName")).sendKeys("Real Name 004");

        // And clicks the "Create" button
        driver.findElement(By.id("wpCreateaccount")).click();

        // Debug: Print all messages on the page
        Thread.sleep(2000); // Wait for messages to appear
        for (WebElement el : driver.findElements(By.cssSelector("div, span, p"))) {
            if (el.isDisplayed() && !el.getText().trim().isEmpty()) {
                System.out.println("Message: " + el.getText());
            }
        }

        // Then "The user account for User001 (talk) has been created." is displayed
        WebElement content = driver.findElement(By.id("mw-content-text"));
        String text = content.getText();
        Assertions.assertTrue(
        text.contains("The user account for User004 (talk) has been created."),
        "Expected success message not found!"
        );

    }
}