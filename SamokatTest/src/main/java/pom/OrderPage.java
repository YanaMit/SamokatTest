package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrderPage {

    private WebDriver driver;
    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }


    //указание полей:

    private static final By NAME_FIELD = By.xpath(".//input[@placeholder='* Имя']");

    private static final By LASTNAME_FIELD = By.xpath(".//input[@placeholder='* Фамилия']");

    private static final By ADRESS_FIELD = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    private static final By METRO_FIELD = By.xpath(".//input[@placeholder='* Станция метро']");

    private static final By PHONE_NUMBER_FIELD = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //кнопка далее
    private static final By NEXT_BUTTON = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");

    //указание полей:
    private static final By DATE = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    private static final By CHOOSED_DAY = By.xpath(".//div[@class=\"react-datepicker\"]//div[contains(@class, \"--selected\")]");

    private static final By HOW_LONG = By.xpath(".//div[text()='* Срок аренды']");

    private static final By COLOR_BLACK = By.xpath("//input[@id='black']");

    private static final By COLOR_GREY = By.xpath("//input[@id='grey']");

    private static final By ORDER_BUTTON = By.xpath("//div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private static final By YES_BUTTON = By.xpath("//div/button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");

    //после окончания оформления заказа для проверки появившегося окна:
    private static final By MADE_ORDER_WINDOW = By.xpath(".//div[@class='Order_Modal__YZ-d3']");

    private static final By MADE_ORDER = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");


    //подождать видимости первого поля
    public boolean isVisible(By element) {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).isDisplayed();
    }

    // заполенние полей данными
    public void printInput (String inp, By element){
        driver.findElement(element).sendKeys(inp);
    }

    //нажать далее
    public void clickNext(){
        driver.findElement(NEXT_BUTTON).click();
    }

    //выбрать метро //редактировала
    public void clickMetroStation(String metroStation){
        By stationButton = By.xpath(".//div[@class='Order_Text__2broi' and text()='" + metroStation + "']");
        isVisible(stationButton);
        driver.findElement(stationButton).click();
    }

    //выбрать дату
    public void pickDate(String date) {
        driver.findElement(DATE).sendKeys(date);
        driver.findElement(CHOOSED_DAY).click();
    }


    //выбрать, на какой срок
    public void pickHowLong(String howLong) {
        driver.findElement(HOW_LONG).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-option' and text()='" + howLong +"']")).click();
    }

    //выбрать цвет
    public void chooseColor(String color){
        if (color.equals("черный жемчуг")) {
            driver.findElement(COLOR_BLACK).click();
        }
        else {driver.findElement(COLOR_GREY).click();
        }
    }

    //шаг сделать заказ
    public void makeOrder(String name, String lastName, String adress, String metroStation,
                          String phoneNumber, String date, String howLong, String color) {

        isVisible(NAME_FIELD);
        printInput(name, NAME_FIELD);
        printInput(lastName, LASTNAME_FIELD);
        printInput(adress, ADRESS_FIELD);
        printInput(metroStation, METRO_FIELD);
        clickMetroStation(metroStation);
        printInput(phoneNumber, PHONE_NUMBER_FIELD);
        clickNext();
        pickDate(date);
        pickHowLong(howLong);
        chooseColor(color);
        driver.findElement(ORDER_BUTTON).click();
        driver.findElement(YES_BUTTON).click();
        isVisible(MADE_ORDER_WINDOW);
    }

    public String getActualText() {
        String actualText = driver.findElement(MADE_ORDER).getText();
        return actualText;
    }

}
