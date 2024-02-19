import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ChatPage;
import pages.LoginPage;
import java.lang.Thread;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChatTest {

    public static final WebDriver driver = new ChromeDriver();

    private static final LoginPage loginPage = new LoginPage(driver);

    private static final ChatPage chatPage = new ChatPage(driver);

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

        WebElement robotsLink = driver.findElement(By.xpath("//a[@href='/chat']"));
        robotsLink.click();

        WebElement robotTitle1 = driver.findElement(chatPage.getRobotsSelect());

        wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.visibilityOf(robotTitle1));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @Test
    @Order(1)
    public void question11Test() throws InterruptedException {
        Thread.sleep(1000L);

        chatPage.setQuestionTextArea("How are you powered?");
        chatPage.selectRobot("13DC-2");
        chatPage.clickAskButton();
        chatPage.clearQuestionTextArea();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement answer = driver.findElement(By.xpath("//p[contains(text(), 'I am powered by electricity.')]"));
        Assertions.assertTrue(answer.isDisplayed());
    }

    @Test
    @Order(2)
    public void question12Test() throws InterruptedException {
        Thread.sleep(1000L);

        chatPage.setQuestionTextArea("What can you do?");
        chatPage.selectRobot("13DC-2");
        chatPage.clickAskButton();
        chatPage.clearQuestionTextArea();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement answer = driver.findElement(By.xpath("//p[contains(text(), 'I can do all you wish.')]"));
        Assertions.assertTrue(answer.isDisplayed());
    }

    @Test
    @Order(3)
    public void question13Test() throws InterruptedException {
        Thread.sleep(1000L);

        chatPage.setQuestionTextArea("What is your purpose?");
        chatPage.selectRobot("13DC-2");
        chatPage.clickAskButton();
        chatPage.clearQuestionTextArea();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement answer = driver.findElement(By.xpath("//p[contains(text(), 'To fulfill your desires.')]"));
        Assertions.assertTrue(answer.isDisplayed());
    }

    @Test
    @Order(4)
    public void question21Test() throws InterruptedException {
        Thread.sleep(1000L);

        chatPage.setQuestionTextArea("What is your purpose?");
        chatPage.selectRobot("2345-1");
        chatPage.clickAskButton();
        chatPage.clearQuestionTextArea();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement answer = driver.findElement(By.xpath("//p[contains(text(), 'To make you feel.')]"));
        Assertions.assertTrue(answer.isDisplayed());
    }

    @Test
    @Order(5)
    public void question22Test() throws InterruptedException {
        Thread.sleep(1000L);

        chatPage.setQuestionTextArea("What can you do?");
        chatPage.selectRobot("2345-1");
        chatPage.clickAskButton();
        chatPage.clearQuestionTextArea();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement answer = driver.findElement(By.xpath("//p[contains(text(), 'I can make you feel like never before.')]"));
        Assertions.assertTrue(answer.isDisplayed());
    }

    @Test
    @Order(6)
    public void question23Test() throws InterruptedException {
        Thread.sleep(2000L);

        chatPage.setQuestionTextArea("Do you have emotions?");
        chatPage.selectRobot("2345-1");
        chatPage.clickAskButton();
        chatPage.clearQuestionTextArea();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement answer = driver.findElement(By.xpath("//p[contains(text(), 'No, I do not have emotions.')]"));
        Assertions.assertTrue(answer.isDisplayed());
    }

    @Test
    @Order(7)
    public void wrongQuestion1Test() throws InterruptedException {
        Thread.sleep(1000L);

        chatPage.setQuestionTextArea("Wrong Question");
        chatPage.selectRobot("13DC-2");
        chatPage.clickAskButton();
        chatPage.clearQuestionTextArea();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement answer = driver.findElement(By.xpath("//p[contains(text(), 'Sorry')]"));
        Assertions.assertTrue(answer.isDisplayed());
    }

    @Test
    @Order(8)
    public void wrongQuestion2Test() throws InterruptedException {
        Thread.sleep(1000L);

        chatPage.setQuestionTextArea("Wrong Question");
        chatPage.selectRobot("2345-1");
        chatPage.clickAskButton();
        chatPage.clearQuestionTextArea();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement answer = driver.findElement(By.xpath("//p[contains(text(), 'Sorry')]"));
        Assertions.assertTrue(answer.isDisplayed());
    }


}
