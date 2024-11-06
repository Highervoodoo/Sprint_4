package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import Pages.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class FaqTest {
    private WebDriver driver;
    private MainPage mainPage;

    private final int questionIndex;
    private final String expectedAnswer;

    public FaqTest(int questionIndex, String expectedAnswer) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{ // Список ожидаемых ответов и их индекс
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        });
    }

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
    }

    @Test
    public void testFaqDropdownDisplaysCorrectAnswerText() {
        mainPage.clickFaqQuestion(questionIndex); // Кликаем по вопросу

        // Получаем текст ответа и сравниваем с ожидаемым
        String actualAnswer = mainPage.getFaqAnswerText(questionIndex);
        assertEquals("Текст ответа не совпадает для вопроса №" + (questionIndex + 1), expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
            driver.quit();
    }
}