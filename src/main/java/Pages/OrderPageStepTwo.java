package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс OrderPageStepTwo представляет вторую страницу оформления заказа
public class OrderPageStepTwo {
    private final WebDriver driver;

    // Локаторы для полея даты, дня в календаре, поля периода аренды, самого периода, чекбокса серого и черного цвета, кнопки заказать и кнопки подверждения на модалке
    private final By dateField = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='* Когда привезти самокат']");
    private final By dayCalendar = By.xpath("//div[@class='react-datepicker__day react-datepicker__day--022' and text()='22']");
    private final By rentalPeriodDropdown = By.xpath("//div[@class='Dropdown-placeholder' and text()='* Срок аренды']");
    private final By rentalPeriodSelect = By.xpath("//div[@class='Dropdown-option' and text()='двое суток']");
    private final By blackScooterCheckbox = By.xpath("//label[@for='black' and text()='чёрный жемчуг']");
    private final By greyScooterCheckbox = By.xpath("//label[@for='grey' and text()='серая безысходность']");
    private final By commentField = By.xpath("//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN' and @placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    private final By confirmOrderButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Middle__1CSJM') and text()='Да']");

    public OrderPageStepTwo(WebDriver driver) {
        this.driver = driver;
    }

    // Метод заполняет детали заказа и подтверждает заказ
    public void fillOrderDetails(String comment, String color) {
        driver.findElement(dateField).click();
        driver.findElement(dayCalendar).click();
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(rentalPeriodSelect).click();

        if (color.equalsIgnoreCase("grey")) {
            driver.findElement(blackScooterCheckbox).click();
        } else if (color.equalsIgnoreCase("black")) {
            driver.findElement(greyScooterCheckbox).click();
        }

        driver.findElement(commentField).sendKeys(comment);
        driver.findElement(orderButton).click();
        driver.findElement(confirmOrderButton).click();
    }
}
