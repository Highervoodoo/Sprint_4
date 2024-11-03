package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Класс OrderStatusPage представляет модалку с созданием заказа
public class OrderStatusPage {
    private final WebDriver driver;

    // Локатор для получения информации о успехе.
    private final  By statusInfo = By.xpath("//div[@class='Order_ModalHeader__3FDaJ' and contains(., 'Заказ оформлен')]");

    public OrderStatusPage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для получения текста об успехе
    public String getStatusInfo() {
        return driver.findElement(statusInfo).getText();
    }
}
