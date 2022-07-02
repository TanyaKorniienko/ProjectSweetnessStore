package org.store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserInfoPage {
    private final WebDriver driver;

    public UserInfoPage(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//a[@href='https://sweetness.com.ua/identity']")
    WebElement openPersonalInfoPage;

    @FindBy(xpath = "//a[@href='https://sweetness.com.ua/address']")
    WebElement openUserAddressPage;

    public PersonalInfo openPersonalInfoPage(){
        openPersonalInfoPage.click();
        return new PersonalInfo((ChromeDriver) driver);
    }

    public UserAddressPage openUserAddressPage(){
        openUserAddressPage.click();
        return new UserAddressPage((ChromeDriver) driver);
    }
}
