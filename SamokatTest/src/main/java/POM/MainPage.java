package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


public class MainPage {

    //переход на страницу
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    //кнопка, сколько стоит
   // private static final WebElement howMuch = driver.findElement(".//div[@id='accordion__heading-0']");

    public static final By howMuch = By.id("accordion__heading-0");

    //кнопка, можно ли взять несколько
    public static final By manyItems = By.id("accordion__heading-1");
    //кнопко, как отменить запас
    public static final By cancelOrder = By.id("accordion__heading-6");

    //то, что открывается по кнопкам:
    public static final By accordeon0 = By.xpath(".//div[@id='accordion__panel-0']/p");

    public static final By accordeon1 = By.xpath(".//div[@id='accordion__panel-1']/p");

    public static final By accordeon6 = By.xpath(".//div[@id='accordion__panel-6']/p");


    //верхняя кнопка "заказать"
    public static final By firstOrderButton =
            By.xpath(".//div[@class='Header_Nav__AGCXC']/" +
            "button[@class='Button_Button__ra12g']");

    //нижняя кнопка "заказать"
    public static final By secondOrderButton =
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


    //шаг - доскроллить и нажать
   public void scrollToButtonAndClick (By button) {
       scroll(button);
       clickButton(button);
   }

   //шаг проверить видимость верхней кнопки "заказать" и нажать на нее
    public boolean isVisible(By element) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).isDisplayed();
    }

    public void clickVisibleFirstOrderButton (By button) {
        isVisible(firstOrderButton);
        clickButton(firstOrderButton);
    }

    //шаг доскроллить до нижнего "заказать", проверить видимость кнопки и нажать на нее
    public void scrollAndClickSecondOrderButton(By button){
        scroll(secondOrderButton);
        isVisible(secondOrderButton);
        clickButton(secondOrderButton);
    }


}
