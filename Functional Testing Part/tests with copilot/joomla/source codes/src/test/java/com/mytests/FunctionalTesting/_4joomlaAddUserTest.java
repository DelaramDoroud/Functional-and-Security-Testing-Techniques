package com.mytests.FunctionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class _4joomlaAddUserTest {

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
    void testAddNewUser() {
        // Given the user is on the home page
        driver.get("http://localhost:8080/index.php/author-login"); // Change URL as needed

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

        // And enters "e2eW3Bt3s71nGB3nchM4rK" in the "Password" field
        WebElement adminPassword = driver.findElement(By.id("mod-login-password"));
        adminPassword.clear();
        adminPassword.sendKeys("e2eW3Bt3s71nGB3nchM4rK");

        // And clicks the "Log in" button
        WebElement adminLoginButton = driver.findElement(By.xpath("//button[contains(@class, 'btn btn-primary btn-block btn-large login-button')]"));
        adminLoginButton.click();

        // And clicks the "Users" link
        WebElement usersLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='j-links-link' and contains(text(),'Users')]")));
        usersLink.click();

        // And clicks the "New" button
        WebElement newButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'btn btn-small button-new btn-success')]")));
        newButton.click();

        // And enters "Test User" in the "Name" field
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jform_name")));
        nameField.clear();
        nameField.sendKeys("Test User");

        // And enters "tuser01" in the "Login Name" field
        WebElement loginNameField = driver.findElement(By.id("jform_username"));
        loginNameField.clear();
        loginNameField.sendKeys("tuser01");

        // And enters "tpassword" in the "Password" field
        WebElement userPasswordField = driver.findElement(By.id("jform_password"));
        userPasswordField.clear();
        userPasswordField.sendKeys("tpassword");

        // And enters "tpassword" in the "Confirm Password" field
        WebElement confirmPasswordField = driver.findElement(By.id("jform_password2"));
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys("tpassword");

        // And enters "testmail@example.com" in the "Email" field
        WebElement emailField = driver.findElement(By.id("jform_email"));
        emailField.clear();
        emailField.sendKeys("testmail@example.com");

        // And clicks the "Save & Close" button
        WebElement saveCloseButton = driver.findElement(By.cssSelector("button[aria-label='Save & Close'], button.button-save"));
        saveCloseButton.click();

        // Then "Test User", "tuser01" and "testmail@example.com" are shown respectively as name, username and email in the second row of the table
        WebElement secondRow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='userList']/tbody/tr[2]")));
        
        String name = secondRow.findElement(By.xpath("./td[2]//a")).getText().trim();
        String username = secondRow.findElement(By.xpath("./td[3]")).getText().trim();
        String email = secondRow.findElement(By.xpath("./td[7]")).getText().trim();

        assertEquals("Test User", name);
        assertEquals("tuser01", username);
        assertEquals("testmail@example.com", email);
    }
}