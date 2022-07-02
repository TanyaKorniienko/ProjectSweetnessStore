package org.store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    WebDriver driver;

    HomePage(ChromeDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//nav/div[@class='header_user_info']/a[@href='https://sweetness.com.ua/my-account']")
    WebElement loginButton;

    public AuthorizationPage clickLoginButton(){
        loginButton.click();
        return new AuthorizationPage((ChromeDriver) driver);
    }
}
