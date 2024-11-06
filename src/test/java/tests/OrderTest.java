package tests;

import Pages.MainPage;
import Pages.OrderPageStepOne;
import Pages.OrderPageStepTwo;
import Pages.OrderStatusPage;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Arrays;
import java.util.Collection;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPageStepOne orderPageStepOne;
    private OrderPageStepTwo orderPageStepTwo;
    private OrderStatusPage orderStatusPage;

    // Параметры для теста
    private final String buttonPosition;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phone;
    private final String comment;
    private final String expectedStatus;
    private final String color;

    public OrderTest (String buttonPosition, String firstName, String lastName, String address, String phone, String comment, String expectedStatus, String color) {
        this.buttonPosition = buttonPosition;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.comment = comment;
        this.expectedStatus = expectedStatus;
        this.color = color;
    }

    // Параметризация с двумя наборами данных и двумя наборами браузеров
    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"top", "Иван", "Иванов", "ул. Пушкина", "+71234567890", "Комментарий 1", "Заказ оформлен", "grey"},
                {"bot", "Алексей", "Смирнов", "ул. Лермонтова", "+79876543210", "Комментарий 2", "Заказ оформлен", "black"},
        });
    }

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        orderPageStepOne = new OrderPageStepOne(driver);
        orderPageStepTwo = new OrderPageStepTwo(driver);
        orderStatusPage = new OrderStatusPage(driver);
    }

    // Основной тест, который проходит по всему флоу заказа и проверяет статус
    @Test
    public void testOrderFlow() {
        mainPage.clickOrderButton(buttonPosition);
        orderPageStepOne.fillOrderForm(firstName, lastName, address, phone); // Заполняем первый шаг
        orderPageStepTwo.fillOrderDetails(comment, color); // Заполняем второй шаг

        // Проверяем смогли ли заказать самокат
        String statusText = orderStatusPage.getStatusInfo();
        assertTrue("Не получилось заказать", statusText.contains(expectedStatus));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}