package com.mytests.functionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.*;

class _6mediawikiEditPageTest {

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
    void EditPage() throws InterruptedException {
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search"))).sendKeys("Software testing");
        Thread.sleep(1000);

        // And presses Enter
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        // And clicks the "Edit" link
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Edit"))).click();
        Thread.sleep(1000);

        // And enters the additional text at the end of the editor
        WebElement editableDiv = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ve-ce-branchNode.ve-ce-documentNode[contenteditable='true']")));
        editableDiv.click();
        editableDiv.sendKeys("additional text");
        Thread.sleep(1000);
        
        // And clicks the "Save" button
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.oo-ui-tool-link.ve-ui-toolbar-saveButton"))).click();
        Thread.sleep(1000);

        // And enters "Page expanded" in the sumamry
        WebElement summaryTextarea = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea.oo-ui-inputWidget-input[placeholder='Describe what you changed']")));
        summaryTextarea.clear();
        summaryTextarea.sendKeys("Page expanded");
        Thread.sleep(1000);

        //  And clicks the "Save changes" button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'oo-ui-buttonElement-button') and .//span[text()='Save changes']]"))).click();
        Thread.sleep(1000);
        
        // Then the page is displayed with "Software testing" as title and the full text as body
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1#firstHeading span.mw-page-title-main")));
        String actualTitle = titleElement.getText().trim();
        String expectedTitle = "Software testing";
        assertEquals(expectedTitle, actualTitle);
        Thread.sleep(1000);

        WebElement bodyParagraph = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#mw-content-text div.mw-parser-output > p")));
        String actualBodyText = bodyParagraph.getText().trim();
        Thread.sleep(1000);
        String expectedBodyText = "additional text";
        assertEquals(expectedBodyText, actualBodyText);
        Thread.sleep(1000);
    }
}