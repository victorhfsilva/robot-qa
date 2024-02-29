package pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    
    private String homeAddress = "http://localhost:8081";

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public BasePage openPage(String url){
        driver.get(url);
        return this;
    }

    public BasePage switchToWindow(String title){
        Set<String> windowHandles = driver.getWindowHandles();

        String desiredWindowHandle = windowHandles.stream()
                .filter(handle -> driver.switchTo().window(handle).getTitle().contains(title))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Janela desejada não encontrada"));

        driver.switchTo().window(desiredWindowHandle);
        return this;
    }

    public BasePage maximize(){
        driver.manage().window().maximize();
        return this;
    }

    public BasePage goBack() {
        driver.navigate().back();
        return this;
    }
    
    public BasePage refreshPage() {
        driver.navigate().refresh();
        return this;
    }

    public BasePage waitForElementToBeClickable(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return this;
    }

    
    public BasePage waitForTitleContains(String title, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.titleContains(title));
        return this;
    }

    public BasePage waitUrlToBe(String url, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.urlToBe(url));
        return this;
    }

    public BasePage waitForVisibilityOf(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        return this;
    }

    public BasePage wait(Duration duration){
        driver.manage().timeouts().implicitlyWait(duration);
        return this;
    }

    public BasePage takeScreenshot(String pathName) {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(pathName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getHomeAddress() {
        return this.homeAddress;
    }

}
