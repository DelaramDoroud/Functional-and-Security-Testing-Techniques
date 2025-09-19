package com.mytests.FunctionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class _8joomlaAddNewArticleTest {

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
    void testAddNewArticle() {
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

        // And enters "Test Article 01" in the "Title" field
        WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("jform_title")));
        titleField.clear();
        titleField.sendKeys("Test Article 01");

        // And enters "This is the body of the first article for testing the platform" in the main text editor
        // Joomla's main editor is usually inside an iframe
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[id^='jform_articletext_ifr']")));
        WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tinymce")));
        body.clear();
        body.sendKeys("This is the body of the first article for testing the platform");
        driver.switchTo().defaultContent();

        // And clicks the "Save" button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'btn btn-primary')]")));
        saveButton.click();

        // Then "Test Article 01" is shown as title of the first article
        WebElement firstArticleTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".blog .page-header h2, .item-title a, .item-title")));
        assertEquals("Test Article 01", firstArticleTitle.getText().trim());

        // And "This is the body of the first article for testing the platform" is shown as text of the first article
        WebElement bodyParagraph = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='This is the body of the first article for testing the platform']")));
        assertTrue(bodyParagraph.isDisplayed());

        // Given the previous assertion passed
        // Then the user clicks the "Author Login" link
        WebElement authorLoginLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Author Login")));
        authorLoginLink.click();

        // And clicks the "Log out" button
        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        logoutButton.click();
    }
}