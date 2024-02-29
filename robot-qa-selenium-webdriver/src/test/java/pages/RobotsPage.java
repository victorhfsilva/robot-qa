package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RobotsPage extends BasePage {

    @FindBy(xpath = "//h3[contains(text(), '13DC-2')]")
    private WebElement robotTitle1;

    @FindBy(xpath = "//p[contains(text(), 'Created to fulfill all your desires.')]")
    private WebElement robotDescription1;

    @FindBy(xpath = "//li[contains(text(), 'How are you powered?')]")
    private WebElement robotQuestion11;

    @FindBy(xpath = "//li[contains(text(), 'What is your purpose?')]")
    private WebElement robotQuestion12;

    @FindBy(xpath = "//li[contains(text(), 'What can you do?')]")
    private WebElement robotQuestion13;

    @FindBy(xpath = "//h3[contains(text(), '2345-1')]")
    private WebElement robotTitle2;

    @FindBy(xpath = "//p[contains(text(), 'Created to make you feel human.')]")
    private WebElement robotDescription2;

    @FindBy(xpath = "//li[contains(text(), 'Do you have emotions?')]")
    private WebElement robotQuestion21;

    @FindBy(xpath = "//li[contains(text(), 'What is your purpose?')]")
    private WebElement robotQuestion22;

    @FindBy(xpath = "//li[contains(text(), 'What can you do?')]")
    private WebElement robotQuestion23;


    public RobotsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public RobotsPage navigateTo(){
        driver.get(getHomeAddress() + "/robots");
        return this;
    }

    public WebElement getRobotTitle1() {
        return this.robotTitle1;
    }

    public WebElement getRobotDescription1() {
        return this.robotDescription1;
    }

    public WebElement getRobotQuestion11() {
        return this.robotQuestion11;
    }

    public WebElement getRobotQuestion12() {
        return this.robotQuestion12;
    }

    public WebElement getRobotQuestion13() {
        return this.robotQuestion13;
    }

    public WebElement getRobotTitle2() {
        return this.robotTitle2;
    }

    public WebElement getRobotDescription2() {
        return this.robotDescription2;
    }

    public WebElement getRobotQuestion21() {
        return this.robotQuestion21;
    }

    public WebElement getRobotQuestion22() {
        return this.robotQuestion22;
    }

    public WebElement getRobotQuestion23() {
        return this.robotQuestion23;
    }

}
