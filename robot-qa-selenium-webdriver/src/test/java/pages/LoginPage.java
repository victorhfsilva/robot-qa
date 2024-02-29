package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    @FindBy(name = "username")
    private WebElement userInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(text(), 'Login')]")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public LoginPage navigateTo(){
        driver.get(getHomeAddress() + "/login");
        return this;
    }


    public LoginPage setUserInput(String user){
        userInput.sendKeys(user);
        return this;
    }

    public LoginPage setPasswordInput(String password){
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton(){
        loginButton.click();
        return this;
    }

    public WebElement getUserInput() {
        return userInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }
}
