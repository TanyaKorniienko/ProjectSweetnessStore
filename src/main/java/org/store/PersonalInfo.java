package org.store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PersonalInfo {
    private final WebDriver driver;

    public PersonalInfo(ChromeDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@id='id_gender1']")
    WebElement gender;

    @FindBy(xpath = "//select[@id='days']")
    WebElement days;

    @FindBy(xpath = "//select[@id='months']")
    WebElement months;

    @FindBy(xpath = "//select[@id='years']")
    WebElement years;

    @FindBy(xpath = "//input[@id='old_passwd']")
    WebElement passwordOld;

    @FindBy(xpath = "//button[@name='submitIdentity']")
    WebElement saveInfoButton;

    public void addUserInfo() {
        gender.click();
        setDay();
        setMonth();
        setYear();
        passwordOld.sendKeys("555555");
    }

    public void setDay() {
        Select dropdown = new Select(days);
        dropdown.getOptions().get(9).click();
    }

    public void setMonth() {
        Select dropdown = new Select(months);
        dropdown.getOptions().get(6).click();
    }

    public Select getSelectYearsOptions() {
        return new Select(years);
    }
    public void setYear() {
        getSelectYearsOptions().selectByIndex(5);
    }

    public String getSelectedYear() {
        return getSelectYearsOptions().getFirstSelectedOption().getText();
    }

    public String getSuccessMessage() {
        return driver.findElement(By.xpath("//p[(contains(text(), ' Ваша персональная информация обновлена.'))]")).getText();
    }

    public PersonalInfo saveNewInfo() {
        saveInfoButton.click();
        return new PersonalInfo((ChromeDriver) driver);
    }
}



