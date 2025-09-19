package com.mytests.functionalTesting;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class _8expresscartSearchProductTest  {

    @Test
    public void SearchProduct() {
        WebDriver driver = new ChromeDriver();

       try {
            driver.get("http://localhost:3000/"); 

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // When the user enters "NewProduct000" in the "Search shop" field
            WebElement SearchProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("frm_search")));
            SearchProduct.sendKeys("NewProduct000");
            Thread.sleep(1000);

            // And clicks the "Search" button
            WebElement SearchButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btn_search")));
            SearchButton.click();
            Thread.sleep(1000);

            // Then "NewProduct000" is shown to the right of "Search results:"
            WebElement searchResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Search results:')]")));
            WebElement product = searchResults.findElement(By.xpath("./strong"));
            String actualText = product.getText().trim();
            String expectedText = "NewProduct000";
            assertEquals(expectedText, actualText, "The product name is not displayed correctly next to 'Search results:'");
            Thread.sleep(1000);

            // And "NewProduct000" is the only product shown in the results
            List<WebElement> productTitles = driver.findElements(By.xpath("//h3[@class='product-title product-title-home top-pad-10']"));
            int productCount = 0;
            for (WebElement productTitle : productTitles) {
                if (productTitle.getText().trim().equals("NewProduct000")) {
                    productCount++;
                }
            }
            assertEquals(1, productCount, "The number of 'NewProduct000' products in the results is not correct.");
            Thread.sleep(1000);

        }  catch (InterruptedException e) {
            e.printStackTrace();
        }  finally {
            driver.quit();
        }
    }
}
