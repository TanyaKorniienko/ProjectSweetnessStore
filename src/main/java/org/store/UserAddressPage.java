package org.store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserAddressPage {
    private final WebDriver driver;

    public UserAddressPage(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id='address1']")
    WebElement addressField;

    @FindBy(xpath = "//input[@id='city']")
    WebElement cityField;

    @FindBy(xpath = "//select[@id='id_state']//option")
    List<WebElement> stateList;

    @FindBy(xpath = "//button[@id='submitAddress']")
    WebElement submitAddressButton;

    public void setCity() {
        cityField.sendKeys("Kiev");
    }

    public String getCity() {
        return cityField.getText();
    }

    public void setAddress() {
        addressField.sendKeys("Saksaganskogo Street");
    }

    public String getAddress() {
        return addressField.getText();
    }

    public void addUserAddress() {
        setAddress();
        setCity();
        stateList.get(10).click();
    }

    public UserAddressPage submitAddress() {
        submitAddressButton.click();
        return new UserAddressPage((ChromeDriver) driver);
    }
}
