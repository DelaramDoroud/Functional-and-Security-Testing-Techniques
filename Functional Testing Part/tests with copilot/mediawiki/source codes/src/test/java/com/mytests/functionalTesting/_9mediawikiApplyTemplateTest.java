package com.mytests.functionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.*;

class _9mediawikiApplyTemplateTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "http://localhost:8080/index.php/Main_Page"; // Change to your MediaWiki URL

    @BeforeEach
    void setUp() {
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void ApplyTemplate() throws InterruptedException {
        // Given the user is on the home page
        driver.get(baseUrl);
        Thread.sleep(1000);

        // When the user clicks the "Log in" link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log in"))).click();
        Thread.sleep(1000);

        // And enters "admin" in the "Username" field
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpName1"))).sendKeys("admin");
        Thread.sleep(1000);

        // And enters "e2eW3Bt3s71nGB3nchM4rK" in the "Password" field
        driver.findElement(By.id("wpPassword1")).sendKeys("e2eW3Bt3s71nGB3nchM4rK");
        Thread.sleep(1000);

        // And clicks the "Log in" button
        driver.findElement(By.id("wpLoginAttempt")).click();
        Thread.sleep(1000);

        // And enters "Selenium WebDriver" in the search bar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search"))).sendKeys("Selenium WebDriver");
        Thread.sleep(1000);

        // And presses Enter
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        // And clicks the "Edit source" link
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@title,'Edit this page') and .//span[text()='Edit source']]"))).click();
        Thread.sleep(1000);
        
        // And enters "{{Software|dev=Selenium|ver=3.141.59}}" at the beginning of the page
        WebElement textArea = wait.until(ExpectedConditions.elementToBeClickable(By.id("wpTextbox1")));
        String existingText = textArea.getText();
        textArea.clear();
        textArea.sendKeys("{{Software|dev=Selenium|ver=3.141.59}} " + existingText);
        Thread.sleep(1000);
        
        // And clicks the "Save page" button
        wait.until(ExpectedConditions.elementToBeClickable(By.id("wpSave"))).click();
        Thread.sleep(1000);

        // Then the page is displayed with "Selenium WebDriver" 
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstHeading")));
        String actualTitle = titleElement.getText().trim();
        String expectedTitle = "Selenium WebDriver";
        assertEquals(expectedTitle, actualTitle);
        Thread.sleep(1000);

        // And "Developer: Selenium Latest version: 3.141.59" is shown at the beginning of the body
        WebElement bodyParagraph = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#mw-content-text div.mw-parser-output > p")));
        String actualBodyText = bodyParagraph.getText().trim();
        Thread.sleep(1000);
        String expectedBodyText = "Developer: Selenium Latest version: 3.141.59";
        assertTrue(actualBodyText.startsWith(expectedBodyText), "The body does not start with the expected text.");
        Thread.sleep(1000);
    }
}