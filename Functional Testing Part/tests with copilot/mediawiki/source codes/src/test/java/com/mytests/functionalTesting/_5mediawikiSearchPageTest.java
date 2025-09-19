package com.mytests.functionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.*;

class _5mediawikiSearchPageTest {

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
    void SearchPage() throws InterruptedException {
        // Given the user is on the home page
        driver.get(baseUrl);
        Thread.sleep(1000);

        // And enters "Software testing" in the search bar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search"))).sendKeys("Software testing");
        Thread.sleep(1000);

        // And presses Enter
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        
        // Then the page "Software testing" is displayed
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1#firstHeading span.mw-page-title-main")));
        String actualTitle = titleElement.getText().trim();
        String expectedTitle = "Software testing";
        assertEquals(expectedTitle, actualTitle);
        Thread.sleep(1000);
    }
}