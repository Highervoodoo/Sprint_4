package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class MainPage {
    private final WebDriver driver;

    // Локаторы для вопросов и ответов
    private final By[] faqQuestions = {
            By.xpath("//div[@id='accordion__heading-0']/parent::div"),
            By.xpath("//div[@id='accordion__heading-1']/parent::div"),
            By.xpath("//div[@id='accordion__heading-2']/parent::div"),
            By.xpath("//div[@id='accordion__heading-3']/parent::div"),
            By.xpath("//div[@id='accordion__heading-4']/parent::div"),
            By.xpath("//div[@id='accordion__heading-5']/parent::div"),
            By.xpath("//div[@id='accordion__heading-6']/parent::div"),
            By.xpath("//div[@id='accordion__heading-7']/parent::div"),
    };

    private final  By[] faqAnswers = {
            By.id("accordion__panel-0"),
            By.id("accordion__panel-1"),
            By.id("accordion__panel-2"),
            By.id("accordion__panel-3"),
            By.id("accordion__panel-4"),
            By.id("accordion__panel-5"),
            By.id("accordion__panel-6"),
            By.id("accordion__panel-7"),
    };

    // Локаторы для двух кнопок Заказать
    private final By orderButtonTop = By.className("Button_Button__ra12g");
    private final By orderButtonBottom = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void scrollToBotButton() {
        WebElement orderBotButton = driver.findElement(By.xpath("//button[contains(@class, 'Button_Middle__1CSJM')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", orderBotButton);
    }

    // Метод для клика по конкретному вопросу
    public void clickFaqQuestion(int index) {
        scrollToBottom();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.elementToBeClickable(faqQuestions[index]));
        driver.findElement(faqQuestions[index]).click();
    }

    // Метод для получения текста ответа
    public String getFaqAnswerText(int index) {
        WebDriverWait wait = new WebDriverWait(driver, 1);
        By answerLocator = faqAnswers[index];
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return answer.getText();
    }

    // Метод для нажатия на кнопку Заказать вверху и внизу
    public void clickOrderButton(String buttonPosition) {
        if (buttonPosition.equals("top")) {
            driver.findElement(orderButtonTop).click();
        } else if (buttonPosition.equals("bot")) {
            scrollToBotButton();
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom));
            driver.findElement(orderButtonBottom).click();
        }
    }
}