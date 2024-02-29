import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.HomePage;
import java.time.Duration;

public class HomeTest {

    private static final WebDriver driver = new ChromeDriver();

    private final static HomePage homePage = new HomePage(driver);

    @BeforeAll
    public static void setup() {
        WebElement aboutButton = homePage.getAboutButton();
        homePage.navigateTo()
                .maximize()
                .waitForVisibilityOf(aboutButton, Duration.ofSeconds(10));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    void aboutButtonTest() {
        String expectedUrl = homePage.getHomeAddress() + "/about";
        homePage.clickAboutButton().waitUrlToBe(expectedUrl, Duration.ofSeconds(10));
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl());
    }
}
