package com.mytests.functionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.*;

class _7mediawikiCreateTemplateTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "http://localhost:8080/index.php/Main_Page"; // Change to your MediaWiki URL

    @BeforeEach
    void setUp() {
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void CreateTemplate() throws InterruptedException {
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

        // And enters "Template:Software" in the search bar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search"))).sendKeys("Template:Software3");
        Thread.sleep(1000);

        // And presses Enter
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        // And clicks the "Template:Software" link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Template:Software3"))).click();
        Thread.sleep(1000);

        // And enters "Developer: {{{dev}}} Latest version: {{{ver}}}" in the editor
        WebElement textArea = wait.until(ExpectedConditions.elementToBeClickable(By.id("wpTextbox1")));
        textArea.sendKeys("Developer: {{{dev}}} Latest version: {{{ver}}}");
        Thread.sleep(1000);

        // And enters "Page created" in the sumamry
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@title, 'Enter a short summary')]")));
        Thread.sleep(1000);

        // And clicks the "Save page" button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@title, 'Save your changes')]"))).click();
        Thread.sleep(1000);

        // Then the page is displayed with "Template:Software" as title and the previously inserted text as body
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstHeading")));
        String actualTitle = titleElement.getText().trim();
        String expectedTitle = "Template:Software3";
        assertEquals(expectedTitle, actualTitle);
        Thread.sleep(1000);

        WebElement bodyParagraph = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#mw-content-text div.mw-parser-output > p")));
        String actualBodyText = bodyParagraph.getText().trim();
        Thread.sleep(1000);
        String expectedBodyText = "Developer: {{{dev}}} Latest version: {{{ver}}}";
        assertEquals(expectedBodyText, actualBodyText);
        Thread.sleep(1000);
    }
}