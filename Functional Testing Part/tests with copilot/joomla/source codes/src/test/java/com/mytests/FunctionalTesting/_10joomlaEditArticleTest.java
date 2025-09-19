package com.mytests.FunctionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class _10joomlaEditArticleTest {

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
    void testEditArticle() {
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

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home"))).click();
        
        // And clicks the gear icon to the bottom right of "Test Article 01"
        // (Assuming the gear icon is a button near the article title)
        WebElement articleCard = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Test Article 01')]/ancestor::*[contains(@class,'item')]")));
        WebElement gearIcon = articleCard.findElement(By.cssSelector(".dropdown-toggle, .fa-cog, [aria-label='Settings'], [data-bs-toggle='dropdown']"));
        gearIcon.click();

        // And clicks the "Edit" option
        WebElement editOption = wait.until(ExpectedConditions.elementToBeClickable(
                articleCard.findElement(By.xpath(".//a[contains(normalize-space(.),'Edit')]"))));
        editOption.click();

        // And enters "EDITED" in the main text editor (append to existing)
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe[id^='jform_articletext_ifr']")));
        WebElement body = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tinymce")));
        body.sendKeys("EDITED");
        driver.switchTo().defaultContent();

        // And clicks the "Save" button
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'btn btn-primary')]")));
        saveButton.click();

        // Then "This is the body of the first article for testing the platformEDITED" is shown as text of the first article
        WebElement bodyParagraph = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='This is the body of the first article for testing the platformEDITED']")));
        assertTrue(bodyParagraph.isDisplayed());
    }
}