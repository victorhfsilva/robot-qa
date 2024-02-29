package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

    @FindBy(xpath = "//button[contains(text(),'More About Us')]")
    private WebElement aboutButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HomePage navigateTo(){
        driver.get(getHomeAddress());
        return this;
    }

    public HomePage clickAboutButton(){
        aboutButton.click();
        return this;
    }

    public WebElement getAboutButton() {
        return aboutButton;
    }
}
