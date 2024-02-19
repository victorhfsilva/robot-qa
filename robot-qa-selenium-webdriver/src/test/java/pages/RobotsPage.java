package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RobotsPage {

    private WebDriver webdriver;

    private By robotTitle1 = By.xpath("//h3[contains(text(), '13DC-2')]");
    private By robotDescription1 = By.xpath("//p[contains(text(), 'Created to fulfill all your desires.')]");
    private By robotQuestion11 = By.xpath("//li[contains(text(), 'How are you powered?')]");
    private By robotQuestion12 = By.xpath("//li[contains(text(), 'What is your purpose?')]");
    private By robotQuestion13 = By.xpath("//li[contains(text(), 'What can you do?')]");

    private By robotTitle2 = By.xpath("//h3[contains(text(), '2345-1')]");
    private By robotDescription2 = By.xpath("//p[contains(text(), 'Created to make you feel human.')]");
    private By robotQuestion21 = By.xpath("//li[contains(text(), 'Do you have emotions?')]");
    private By robotQuestion22 = By.xpath("//li[contains(text(), 'What is your purpose?')]");
    private By robotQuestion23 = By.xpath("//li[contains(text(), 'What can you do?')]");

    public RobotsPage(WebDriver webdriver) {
        this.webdriver = webdriver;
    }

    public By getRobotTitle1() {
        return robotTitle1;
    }

    public By getRobotDescription1() {
        return robotDescription1;
    }

    public By getRobotQuestion11() {
        return robotQuestion11;
    }

    public By getRobotQuestion12() {
        return robotQuestion12;
    }

    public By getRobotQuestion13() {
        return robotQuestion13;
    }

    public By getRobotTitle2() {
        return robotTitle2;
    }

    public By getRobotDescription2() {
        return robotDescription2;
    }

    public By getRobotQuestion21() {
        return robotQuestion21;
    }

    public By getRobotQuestion22() {
        return robotQuestion22;
    }

    public By getRobotQuestion23() {
        return robotQuestion23;
    }
}
