package com.mytests.functionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.*;

class _8mediawikiCreatePageFromSourceTest {

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
    void CreatePageFromSource() throws InterruptedException {
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search"))).sendKeys("Selenium WebDriver1");
        Thread.sleep(1000);

        // And presses Enter
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        // And click the "Selenium WebDriver" link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Selenium WebDriver1"))).click();
        Thread.sleep(1000);

        // And closes the notification
        WebElement notif = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.oo-ui-popupWidget")));
        WebElement closeButton = notif.findElement(By.xpath(".//a[@title='Close' and contains(@class,'oo-ui-buttonElement-button')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", closeButton);
        wait.until(ExpectedConditions.invisibilityOf(notif));

        // And clicks the "Create source" link
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@title,'Edit this page') and .//span[text()='Create source']]"))).click();
        Thread.sleep(1000);
        
        // And enters the text of the page in the editor
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("wpTextbox1"))).sendKeys("the text of the page");
        Thread.sleep(1000);
        
        // And clicks the "Save page" button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@title, 'Save your changes')]"))).click();
        Thread.sleep(1000);

        // Then the page is displayed with "Selenium WebDriver" as title and the previously inserted text as body
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstHeading")));
        String actualTitle = titleElement.getText().trim();
        String expectedTitle = "Selenium WebDriver1";
        assertEquals(expectedTitle, actualTitle);
        Thread.sleep(1000);

        WebElement bodyParagraph = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#mw-content-text div.mw-parser-output > p")));
        String actualBodyText = bodyParagraph.getText().trim();
        Thread.sleep(1000);
        String expectedBodyText = "the text of the page";
        assertEquals(expectedBodyText, actualBodyText);
        Thread.sleep(1000);
    }
}