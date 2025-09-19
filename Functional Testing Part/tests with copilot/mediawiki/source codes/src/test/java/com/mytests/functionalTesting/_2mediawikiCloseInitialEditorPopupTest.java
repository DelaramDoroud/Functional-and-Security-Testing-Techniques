package com.mytests.functionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

class _2mediawikiCloseInitialEditorPopupTest {

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
    void CloseInitialEditorPopup() throws InterruptedException {
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

        // And enters "Software testing" in the search bar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search"))).sendKeys("Software testing1");
        Thread.sleep(1000);

        // And presses Enter
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        // And click the "Software testing" link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Software testing1"))).click();
        Thread.sleep(1000);
    }
}