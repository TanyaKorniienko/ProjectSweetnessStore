package org.store;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class AddNewUserAddressTest {
    private WebDriver driver;
    private HomePage homePage;
    private AuthorizationPage authorizationPage;
    private WebDriverWait wait;
    private AuthorizationPage.UserData userData;
    private UserInfoPage userInfoPage;
    private UserAddressPage userAddressPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sweetness.com.ua/");
        homePage = new HomePage((ChromeDriver) driver);
        authorizationPage = new AuthorizationPage((ChromeDriver) driver);
        userData = new AuthorizationPage.UserData("elenarobertson37+50@gmail.com", "555555");
        userInfoPage = new UserInfoPage((ChromeDriver) driver);
        userAddressPage = new UserAddressPage((ChromeDriver) driver);
    }

    @Test
    public void addUserAddress() {
        homePage.clickLoginButton();
        wait = new WebDriverWait(driver, Duration.ofSeconds(55));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id= 'login_form']")));
        userData.enterExistingUserData();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));
        authorizationPage.clickSignInButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='info-account']")));
        userInfoPage.openUserAddressPage();
        userAddressPage.addUserAddress();
        userAddressPage.submitAddress();

        assertThat(userAddressPage.getAddress()).startsWith("Sak");
        assertThat(userAddressPage.getCity()).isEqualTo("Kiev");
    }

    @After
    public void close() {
        driver.quit();
    }
}
