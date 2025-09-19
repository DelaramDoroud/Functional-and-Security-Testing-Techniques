package com.mytests.FunctionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class _6joomlaBadSiteAdminLoginTest {

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
    void testBadSiteAdminLoginShowsErrorMessage() {
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

        // And clicks the "Site Administrator" link (opens in new tab)
        WebElement adminLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Site Administrator")));
        String originalWindow = driver.getWindowHandle();
        adminLink.click();

        // Wait for new tab and switch to it
        wait.until(d -> d.getWindowHandles().size() > 1);
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        for (String handle : tabs) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // And enters "administrator" in the "Username" field (admin login)
        WebElement adminUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mod-login-username")));
        adminUsername.clear();
        adminUsername.sendKeys("administrator");

        // And enters "wrongpassword" in the "Password" field
        WebElement adminPassword = driver.findElement(By.id("mod-login-password"));
        adminPassword.clear();
        adminPassword.sendKeys("wrongpassword");

        // And clicks the "Log in" button
        WebElement adminLoginButton = driver.findElement(By.xpath("//button[contains(@class, 'btn btn-primary btn-block btn-large login-button')]"));
        adminLoginButton.click();

        // Then error message is shown in a yellow box
        WebElement errorBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'alert') and contains(text(),'Username and password')]")));
        String errorMsg = errorBox.getText();
        assertTrue(errorMsg.contains("Username and password do not match or you do not have an account yet."));
    }
}