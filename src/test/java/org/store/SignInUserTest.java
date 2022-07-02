package org.store;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInUserTest {
    private WebDriver driver;
    private HomePage homePage;
    private AuthorizationPage authorizationPage;
    private WebDriverWait wait;
    private AuthorizationPage.UserData userData;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sweetness.com.ua/");
        homePage = new HomePage((ChromeDriver) driver);
        authorizationPage = new AuthorizationPage((ChromeDriver) driver);
        userData = new AuthorizationPage.UserData("elenarobertson37+50@gmail.com", "555555");
    }

    @Test
    public void signInUser() {
        homePage.clickLoginButton();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form[@id= 'login_form']")));
        userData.enterExistingUserData();
        authorizationPage.clickSignInButton();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='info-account']")));

        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://sweetness.com.ua/my-account");
        assertThat(userData.getEmail_Of_existing_User()).isEqualTo("elenarobertson37+50@gmail.com");
        assertThat(userData.getPassword_Of_existing_User()).isEqualTo("555555");
    }

    @After
    public void close() {
        driver.quit();
    }
}

