import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    public static final WebDriver driver = new ChromeDriver();

    private static final LoginPage loginPage = new LoginPage(driver);

    @BeforeAll
    public static void setup() {
        driver.get("http://localhost:5173/login");

        driver.manage().window().maximize();

        WebElement loginButton = driver.findElement(loginPage.getLoginButton());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.visibilityOf(loginButton));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    void loginTest() {
        loginPage.setUserInput("victor");
        loginPage.setPasswordInput("password");
        loginPage.clickLoginButton();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement authenticationStatus = driver.findElement(By.id("isAuthenticated"));
        Assertions.assertTrue(authenticationStatus.isDisplayed());
    }
}
