package com.mytests.functionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.*;

class _4mediawikiCreateAndLinkPageTest {

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
    void CreateAndLinkPage() throws InterruptedException {
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

        // And enters "E2E Web Testing" in the search bar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search"))).sendKeys("E2E Web Testing1");
        Thread.sleep(1000);

        // And presses Enter
        driver.findElement(By.name("search")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        // And click the "E2E Web Testing2" link (if it exists, otherwise create page)
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("E2E Web Testing1"))).click();
        Thread.sleep(1000);

        // And closes the notification
        WebElement notif = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.oo-ui-popupWidget")));
        WebElement closeButton = notif.findElement(By.xpath(".//a[@title='Close' and contains(@class,'oo-ui-buttonElement-button')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", closeButton);
        wait.until(ExpectedConditions.invisibilityOf(notif));

        // And enters the first part of the text in the editor
        // And enters "[[" in the editor
        //  And enters "Software testing" in the popup search bar
        WebElement editableDiv = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.ve-ce-branchNode.ve-ce-documentNode[contenteditable='true']")));
        editableDiv.click();
        editableDiv.sendKeys("the first part of the text");
        Thread.sleep(1000);
        editableDiv.sendKeys("[[");
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.oo-ui-inputWidget-input[type='search']")));
        js.executeScript("arguments[0].value='Software testing1'; arguments[0].dispatchEvent(new Event('input'));", searchInput);

        //And clicks the "Software testing2" link
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'Software testing1') and @class='oo-ui-labelElement-label-highlight']"))).click();
        Thread.sleep(1000);

        // And enters the last part of the text in the editor
        editableDiv.click();
        editableDiv.sendKeys("the last part of the text");  
        
        // And clicks the "Save" button
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.oo-ui-tool-link.ve-ui-toolbar-saveButton"))).click();
        Thread.sleep(1000);

        // And enters "Page created" in the sumamry
        WebElement summaryTextarea = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea.oo-ui-inputWidget-input[placeholder='Describe what you changed']")));
        summaryTextarea.clear();
        summaryTextarea.sendKeys("Page created");
        Thread.sleep(1000);

        //  And clicks the "Save page" button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'oo-ui-buttonElement-button') and .//span[text()='Save page']]"))).click();
        Thread.sleep(1000);
        
        // Then the page is displayed with "E2E Web Testing" as title
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1#firstHeading span.mw-page-title-main")));
        String actualTitle = titleElement.getText().trim();
        String expectedTitle = "E2E Web Testing1";
        assertEquals(expectedTitle, actualTitle);
        Thread.sleep(1000);

        // When the user clicks the "Software testing1"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/index.php/Software_testing1']"))).click();
        Thread.sleep(1000);

        // Then the page created in the previous test case is displayed
        WebElement titleElement2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1#firstHeading span.mw-page-title-main")));
        String actualTitle2 = titleElement2.getText().trim();
        String expectedTitle2 = "Software testing1";
        assertEquals(expectedTitle2, actualTitle2);
        Thread.sleep(1000);
    }
}