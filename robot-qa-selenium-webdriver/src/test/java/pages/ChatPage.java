package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ChatPage {

    private WebDriver driver;

    private By robotsSelect = By.id("robots-select");

    private By questionTextArea = By.xpath("//textarea");

    private By askButton = By.xpath("//button[contains(text(), 'Ask')]");

    public ChatPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setQuestionTextArea(String question) {
        driver.findElement(questionTextArea).sendKeys(question);
    }

    public void clearQuestionTextArea() {
        driver.findElement(questionTextArea).clear();
    }

    public void selectRobot(String robot) {
        WebElement robotsSelectDropdown = driver.findElement(robotsSelect);
        Select dropdown = new Select(robotsSelectDropdown);
        dropdown.selectByVisibleText(robot);
    }

    public void clickAskButton() {
        driver.findElement(askButton).click();
    }

    public By getRobotsSelect() {
        return robotsSelect;
    }

    public By getQuestionTextArea() {
        return questionTextArea;
    }

    public By getAskButton() {
        return askButton;
    }
}
