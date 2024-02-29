package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ChatPage extends BasePage {

    @FindBy(id = "robots-select")
    private WebElement robotsSelect;

    @FindBy(xpath = "//textarea")
    private WebElement questionTextArea;

    @FindBy(xpath = "//button[contains(text(), 'Ask')]")
    private WebElement askButton;

    public ChatPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ChatPage navigateTo(){
        driver.get(getHomeAddress() + "/chat");
        return this;
    }

    public ChatPage setQuestionTextArea(String question) {
        questionTextArea.sendKeys(question);
        return this;
    }

    public ChatPage clearQuestionTextArea() {
        questionTextArea.clear();
        return this;
    }

    public ChatPage selectRobot(String robot) {
        Select dropdown = new Select(robotsSelect);
        dropdown.selectByVisibleText(robot);
        return this;
    }

    public ChatPage clickAskButton() {
        askButton.click();
        return this;
    }

    public WebElement getRobotsSelect() {
        return robotsSelect;
    }

    public WebElement getQuestionTextArea() {
        return questionTextArea;
    }

    public WebElement getAskButton() {
        return askButton;
    }
}
