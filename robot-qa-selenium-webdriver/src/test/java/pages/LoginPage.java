package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By userInput = By.name("username");
    private By passwordInput = By.name("password");
    private By loginButton = By.xpath("//button[contains(text(), 'Login')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUserInput(String user){
        driver.findElement(userInput).sendKeys(user);
    }

    public void setPasswordInput(String password){
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public By getUserInput() {
        return userInput;
    }

    public By getPasswordInput() {
        return passwordInput;
    }

    public By getLoginButton() {
        return loginButton;
    }
}
