package org.store;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Random;

public class AuthorizationPage {
    private final WebDriver driver;

    AuthorizationPage(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id='email_create']")
    static
    WebElement userEmail;

    @FindBy(xpath = "//button[@id='SubmitCreate']")
    static
    WebElement submitCreateButton;

    @FindBy(xpath = "//input[@id='email']")
    static
    WebElement existingUserEmail;

    @FindBy(xpath = "//input[@id='passwd']")
    static
    WebElement existingUserPassword;

    @FindBy(xpath = "//button[@id='SubmitLogin']")
    static
    WebElement loginButton;

    static class UserData {
        private String emailForNewUser;
        private String emailOfExistingUser;
        private String password;

        UserData(String emailOfExistingUser, String password) {
            this.emailOfExistingUser = emailOfExistingUser;
            this.password = password;
        }

        UserData(String emailForNewUser) {//UserData
            this.emailForNewUser = emailForNewUser;
        }

        public void enterExistingUserData() {
            existingUserEmail.sendKeys(emailOfExistingUser);
            existingUserPassword.sendKeys(password);
        }

        public void enterNewUserEmail() {
            userEmail.sendKeys(emailForNewUser);
        }

        public String getPassword_Of_existing_User() {
            return password;
        }

        public String getNew_User_email() {
            return emailForNewUser;
        }

        public String getEmail_Of_existing_User() {
            return emailOfExistingUser;
        }
    }

    public String getEmailRandoms() {
        String CHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder s = new StringBuilder();
        Random r = new Random();
        while (s.length() < 10) {
            int index = (int) (r.nextFloat() * CHARS.length());
            s.append(CHARS.charAt(index));
        }
        String sStr = s.toString();
        return sStr;
    }

    public UserInfoPage clickSignInButton() {
        loginButton.click();
        return new UserInfoPage((ChromeDriver) driver);
    }

    public RegistrationPage startCreateUserButton() {
        submitCreateButton.click();
        return new RegistrationPage((ChromeDriver) driver);
    }
}