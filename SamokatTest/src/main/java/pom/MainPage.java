package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


public class MainPage {

    //переход на страницу
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    //верхняя кнопка "заказать"
    private static final By FIRST_ORDER_BUTTON =
            By.xpath(".//div[@class='Header_Nav__AGCXC']/" +
            "button[@class='Button_Button__ra12g']");

    //нижняя кнопка "заказать"
    private static final By SECOND_ORDER_BUTTON =
            By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage open() {
        driver.get(PAGE_URL);
        return this;
    }

    public void scroll(By element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
    }

    public void clickButton(By button) {
        driver.findElement(button).click();
    }

    public boolean isVisible(By element) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).isDisplayed();
    }

    public void clickVisibleFirstOrderButton () {
        isVisible(FIRST_ORDER_BUTTON);
        clickButton(FIRST_ORDER_BUTTON);
    }

    //шаг доскроллить до нижнего "заказать", проверить видимость кнопки и нажать на нее
    public void scrollAndClickSecondOrderButton(){
        scroll(SECOND_ORDER_BUTTON);
        isVisible(SECOND_ORDER_BUTTON);
        clickButton(SECOND_ORDER_BUTTON);
    }

    private By questionButton;
    public void visibleQuestionButton(int number){
        open();
        questionButton = By.id("accordion__heading-" + number);
        scroll(questionButton);
        isVisible(questionButton);
    }


    public String getTextOfQuestion(int number){
        visibleQuestionButton (number);
        String actualQuestion = driver.findElement(questionButton).getText();
        return actualQuestion;
    }

    public void clickQuestionsAboutImportant(int number) {
        visibleQuestionButton (number);
        driver.findElement(questionButton).click();
    }

    //Метод взятия текста из ответа на вопросы о важном
    public String getTextFromAnswer(int number) {
        By textFromAnswer = By.xpath(".//div[@id='accordion__panel-" + number + "']/p");
        scroll(textFromAnswer);
        String actualTextFromAnswer = driver.findElement(textFromAnswer).getText();
        return actualTextFromAnswer;
    }

}