package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    private By aboutButton = By.xpath("//button[contains(text(),'More About Us')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAboutButton(){
        driver.findElement(aboutButton).click();
    }

    public By getAboutButton() {
        return aboutButton;
    }
}
