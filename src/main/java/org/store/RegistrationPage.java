package org.store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
    private WebDriver driver;
    private String firstName;
    private String lastName;
    private String new_user_password;

    public RegistrationPage(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @FindBy(xpath = "//input[@id='customer_firstname']")
    static
    WebElement firstNameField;

    @FindBy(xpath = "//input[@id='customer_lastname']")
    static
    WebElement lastNameField;

    @FindBy(xpath = "//input[@type='password']")
    static
    WebElement passwordField;

    @FindBy(xpath = "//button[@id='submitAccount']")
    static
    WebElement submit_Button;

    public RegistrationPage(String firstName, String lastName, String new_user_password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.new_user_password = new_user_password;
    }

    public void enterNewUserData() {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        passwordField.sendKeys(new_user_password);
    }

    public String getNewUserPassword() {
        return new_user_password;
    }
    public UserInfoPage submitUserRegistration() {
        submit_Button.click();
        return new UserInfoPage((ChromeDriver) driver);
    }
}
