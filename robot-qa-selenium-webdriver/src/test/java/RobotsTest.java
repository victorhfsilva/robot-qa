import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.RobotsPage;
import java.time.Duration;

public class RobotsTest {

    public static final WebDriver driver = new ChromeDriver();

    private static final LoginPage loginPage = new LoginPage(driver);

    private static final RobotsPage robotsPage = new RobotsPage(driver);

    @BeforeAll
    public static void setup() {
        WebElement loginButton = loginPage.getLoginButton();
        loginPage.navigateTo()
                    .maximize()
                    .waitForVisibilityOf(loginButton, Duration.ofSeconds(10));

        loginPage.setUserInput("victor")
                    .setPasswordInput("password")
                    .clickLoginButton()
                    .wait(Duration.ofSeconds(3));

        WebElement authenticationStatus = driver.findElement(By.id("isAuthenticated"));
        Assertions.assertTrue(authenticationStatus.isDisplayed());

        WebElement robotTitle1 = robotsPage.getRobotTitle1();
        robotsPage.navigateTo().waitForVisibilityOf(robotTitle1, Duration.ofSeconds(10));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    void robotsTest() {
        WebElement robotTitle1 = robotsPage.getRobotTitle1();
        WebElement robotDescription1 = robotsPage.getRobotDescription1();
        WebElement robotQuestion11 = robotsPage.getRobotQuestion11();
        WebElement robotQuestion12 = robotsPage.getRobotQuestion12();
        WebElement robotQuestion13 = robotsPage.getRobotQuestion13();
    
        WebElement robotTitle2 = robotsPage.getRobotTitle2();
        WebElement robotDescription2 = robotsPage.getRobotDescription2();
        WebElement robotQuestion21 = robotsPage.getRobotQuestion21();
        WebElement robotQuestion22 = robotsPage.getRobotQuestion22();
        WebElement robotQuestion23 = robotsPage.getRobotQuestion23();
    
        Assertions.assertTrue(robotTitle1.isDisplayed());
        Assertions.assertTrue(robotTitle2.isDisplayed());
        Assertions.assertTrue(robotDescription1.isDisplayed());
        Assertions.assertTrue(robotDescription2.isDisplayed());
        Assertions.assertTrue(robotQuestion11.isDisplayed());
        Assertions.assertTrue(robotQuestion12.isDisplayed());
        Assertions.assertTrue(robotQuestion13.isDisplayed());
        Assertions.assertTrue(robotQuestion21.isDisplayed());
        Assertions.assertTrue(robotQuestion22.isDisplayed());
        Assertions.assertTrue(robotQuestion23.isDisplayed());
    }   

}
