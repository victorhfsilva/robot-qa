
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import java.time.Duration;

public class LoginTest {

    public static final WebDriver driver = new ChromeDriver();

    private static final LoginPage loginPage = new LoginPage(driver);

    @BeforeAll
    public static void setup() {
        WebElement loginButton = loginPage.getLoginButton();
        loginPage.navigateTo()
                    .maximize()
                    .waitForVisibilityOf(loginButton, Duration.ofSeconds(10));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    void loginTest() {
        loginPage.setUserInput("victor")
                    .setPasswordInput("password")
                    .clickLoginButton()
                    .wait(Duration.ofSeconds(3));

        WebElement authenticationStatus = driver.findElement(By.id("isAuthenticated"));
        Assertions.assertTrue(authenticationStatus.isDisplayed());
    }
}
