import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

import java.time.Duration;

public class HomeTest {

    private static final WebDriver driver = new ChromeDriver();

    private final static HomePage homePage = new HomePage(driver);

    @BeforeAll
    public static void setup() {
        driver.get("http://localhost:5173/");

        driver.manage().window().maximize();

        WebElement aboutButton = driver.findElement(homePage.getAboutButton());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.visibilityOf(aboutButton));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    void aboutButtonTest() {
        String expectedUrl = "http://localhost:5173/about";

        homePage.clickAboutButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }
}
