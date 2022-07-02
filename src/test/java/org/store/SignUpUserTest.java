package org.store;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class SignUpUserTest {
    private WebDriver driver;
    private HomePage homePage;
    private AuthorizationPage authorizationPage;
    private WebDriverWait wait;
    private AuthorizationPage.UserData userData;
    private RegistrationPage createUser;
    private RegistrationPage newUserForm;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sweetness.com.ua/");
        homePage = new HomePage((ChromeDriver) driver);
        authorizationPage = new AuthorizationPage((ChromeDriver) driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        userData = new AuthorizationPage.UserData("elenarobertson37+" + authorizationPage.getEmailRandoms() + "@gmail.com");
        createUser = new RegistrationPage((ChromeDriver) driver);
        newUserForm = new RegistrationPage("Elena", "Robertson", "123456");
    }

    @Test
    public void signUpNewUser() {
        homePage.clickLoginButton();
        userData.enterNewUserEmail();
        authorizationPage.startCreateUserButton();
        newUserForm.enterNewUserData();
        createUser.submitUserRegistration();
        
        String congratulationsMessage = driver.findElement(By.xpath("//p[contains(text(), ' Ваша учетная запись создана.')]")).getText();
        assertThat(congratulationsMessage.equals("Ваша учетная запись создана."));
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://sweetness.com.ua/my-account");
        assertThat(userData.getNew_User_email()).startsWith("elena").contains("@").endsWith("gmail.com");
        assertThat(createUser.getNewUserPassword()).isEqualTo("123456").hasSize(6);
        System.out.println("true");
    }

    @After
    public void close() {
        driver.quit();
    }
}
