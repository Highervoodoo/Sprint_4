package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс OrderPageStepOne представляет первую страницу оформления заказа
public class OrderPageStepOne {
    private final WebDriver driver;

    // Локаторы для полей ввода имени, фамилии, телефона, адреса, поля выбора метро и первой станции в выпадающем списке и кнопки Далее
    private final By firstNameField = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Имя']");
    private final By lastNameField = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Фамилия']");
    private final By addressField = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Адрес: куда привезти заказ']");
    private final By phoneField = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Телефон: на него позвонит курьер']");
    private final By metroStationDropdown = By.xpath("//input[@class='select-search__input' and @placeholder='* Станция метро']");
    private final By metroStationSelect = By.xpath("//li[contains(@class, 'select-search__row')][1]/button");
    private final By nextButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Middle__1CSJM') and text()='Далее']");

    public OrderPageStepOne(WebDriver driver) {
        this.driver = driver;
    }

    // Метод заполняет форму заказа на первой страничке
    public void fillOrderForm(String firstName, String lastName, String address, String phone) {
        driver.findElement(metroStationDropdown).click();
        driver.findElement(metroStationSelect).click();
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

}

