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
import pages.RobotsPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RobotsTest {

    public static final WebDriver driver = new ChromeDriver();

    private static final LoginPage loginPage = new LoginPage(driver);

    private static final RobotsPage robotsPage = new RobotsPage(driver);

    @BeforeAll
    public static void setup() {
        driver.get("http://localhost:5173/login");

        driver.manage().window().maximize();

        WebElement loginButton = driver.findElement(loginPage.getLoginButton());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.visibilityOf(loginButton));

        loginPage.setUserInput("victor");
        loginPage.setPasswordInput("password");
        loginPage.clickLoginButton();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement authenticationStatus = driver.findElement(By.id("isAuthenticated"));
        Assertions.assertTrue(authenticationStatus.isDisplayed());

        WebElement robotsLink = driver.findElement(By.xpath("//a[@href='/robots']"));
        robotsLink.click();

        WebElement robotTitle1 = driver.findElement(robotsPage.getRobotTitle1());

        wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.visibilityOf(robotTitle1));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    void robotsTest() {
        WebElement robotTitle1 = driver.findElement(robotsPage.getRobotTitle1());
        WebElement robotDescription1 = driver.findElement(robotsPage.getRobotDescription1());
        WebElement robotQuestion11 = driver.findElement(robotsPage.getRobotQuestion11());
        WebElement robotQuestion12 = driver.findElement(robotsPage.getRobotQuestion12());
        WebElement robotQuestion13 = driver.findElement(robotsPage.getRobotQuestion13());

        WebElement robotTitle2 = driver.findElement(robotsPage.getRobotTitle2());
        WebElement robotDescription2 = driver.findElement(robotsPage.getRobotDescription2());
        WebElement robotQuestion21 = driver.findElement(robotsPage.getRobotQuestion21());
        WebElement robotQuestion22 = driver.findElement(robotsPage.getRobotQuestion22());
        WebElement robotQuestion23 = driver.findElement(robotsPage.getRobotQuestion23());

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
