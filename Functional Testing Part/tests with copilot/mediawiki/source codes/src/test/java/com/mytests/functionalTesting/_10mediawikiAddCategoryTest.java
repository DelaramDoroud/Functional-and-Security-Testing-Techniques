package com.mytests.functionalTesting;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import static org.junit.jupiter.api.Assertions.*;

class _10mediawikiAddCategoryTest {

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
    void AddCategory() throws InterruptedException {
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

        // And clicks the "Edit" link
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Edit"))).click();
        Thread.sleep(1000);

        // And clicks the icon with three lines
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.ve-ui-toolbar-group-pageMenu.oo-ui-widget[title='Page options'] span.oo-ui-popupToolGroup-handle"))).click();
        Thread.sleep(1000);

        // And clicks "Categories"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Categories']/parent::a"))).click();
        Thread.sleep(1000);     
        
        // And enters "Browser automation tools" in the "Add a category" field
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.oo-ui-inputWidget-input[placeholder='Add a category']"))).sendKeys("Browser automation tools");
        Thread.sleep(1000);

        // And presses Enter
        driver.findElement(By.cssSelector("input.oo-ui-inputWidget-input[placeholder='Add a category']")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);

        // And clicks the "Apply changes" button
        driver.findElement(By.xpath("//span[text()='Apply changes']/ancestor::a[contains(@class, 'oo-ui-buttonElement-button')]")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        
        // And clicks the "Save" button
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.oo-ui-tool-link.ve-ui-toolbar-saveButton"))).click();
        Thread.sleep(1000);

        // And enters "Added category" in the sumamry
        WebElement summaryTextarea = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea.oo-ui-inputWidget-input[placeholder='Describe what you changed']")));
        summaryTextarea.clear();
        summaryTextarea.sendKeys("Added category");
        Thread.sleep(1000);

        //  And clicks the "Save page" button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'oo-ui-buttonElement-button') and .//span[text()='Save changes']]"))).click();
        Thread.sleep(1000);
        
        // Then the page has title "Selenium WebDriver"
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1#firstHeading span.mw-page-title-main")));
        String actualTitle = titleElement.getText().trim();
        String expectedTitle = "Selenium WebDriver";
        assertEquals(expectedTitle, actualTitle);
        Thread.sleep(1000);

        // And "Category: Browser automation tools" is displayed at the end of the page
        WebElement showCategory = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("catlinks")));
        String actualCategory = showCategory.getText();
        String expectedCategory = "Category: Browser automation tools";
        assertEquals(actualCategory, expectedCategory);
        Thread.sleep(1000);

    }
}