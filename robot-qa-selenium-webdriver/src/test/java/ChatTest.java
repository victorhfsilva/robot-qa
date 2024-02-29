import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ChatPage;
import pages.LoginPage;
import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChatTest {

    public static final WebDriver driver = new ChromeDriver();

    private static final LoginPage loginPage = new LoginPage(driver);

    private static final ChatPage chatPage = new ChatPage(driver);

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

        chatPage.navigateTo().waitForVisibilityOf(chatPage.getRobotsSelect(), Duration.ofSeconds(10));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @Order(1)
    @CsvSource({
        "How are you powered?, 13DC-2, I am powered by electricity.",
        "What can you do?, 13DC-2, I can do all you wish.",
        "What is your purpose?, 13DC-2, To fulfill your desires."
    })
    void questionTest(String question, String robot, String expectedAnswer) throws InterruptedException {
        chatPage.clearQuestionTextArea().wait(Duration.ofSeconds(5));

        chatPage.setQuestionTextArea(question)
                .selectRobot(robot)
                .clickAskButton();

        WebElement answer = driver.findElement(By.xpath("//p[contains(text(), '" + expectedAnswer + "')]"));
        Assertions.assertTrue(answer.isDisplayed());
    }

    @ParameterizedTest
    @Order(2)
    @CsvSource({
        "What is your purpose?, 2345-1, To make you feel.",
        "What can you do?, 2345-1, I can make you feel like never before.",
        "Do you have emotions?, 2345-1, No, I do not have emotions."
    })
    void questionTest2(String question, String robot, String expectedAnswer) throws InterruptedException {
        chatPage.clearQuestionTextArea().wait(Duration.ofSeconds(5));

        chatPage.setQuestionTextArea(question)
                .selectRobot(robot)
                .clickAskButton();

        WebElement answer = driver.findElement(By.xpath("//p[contains(text(), '" + expectedAnswer + "')]"));
        Assertions.assertTrue(answer.isDisplayed());
    }

    @ParameterizedTest
    @Order(3)
    @CsvSource({
        "Wrong Question, 13DC-2",
        "Wrong Question, 2345-1"
    })
    public void wrongQuestionTest(String wrongQuestion, String robot) throws InterruptedException {
        chatPage.clearQuestionTextArea().wait(Duration.ofSeconds(5));

        chatPage.setQuestionTextArea(wrongQuestion)
                .selectRobot(robot)
                .clickAskButton();

        WebElement answer = driver.findElement(By.xpath("//p[contains(text(), 'Sorry')]"));
        Assertions.assertTrue(answer.isDisplayed());
    }


}
