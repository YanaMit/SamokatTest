package POM;

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

    public static final By nameField = By.xpath(".//input[@placeholder='* Имя']");

    public static final By lastnameField = By.xpath(".//input[@placeholder='* Фамилия']");

    public static final By adressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    public static final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");

    public static final By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //кнопка далее
    public static final By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");

    //указание полей:
    public static final By station = By.xpath(".//div[@class='Order_Text__2broi' and text()='Черкизовская']");

    public static final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    public static final By today = By.xpath(".//div[@class='react-datepicker']//div[contains(@class, '--today')]");

    public static final By howLong = By.xpath(".//div[text()='* Срок аренды']");

    public static final By oneDay = By.xpath(".//div[@class='Dropdown-menu']//div[text()='сутки']");

    public static final By color = By.xpath("//input[@id='black']");

    public static final By orderButton = By.xpath("//div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public static final By yesButton = By.xpath("//div/button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");

    //после окончания оформления заказа для проверки появившегося окна:
    public static final By madeOrderWindow = By.xpath(".//div[@class='Order_Modal__YZ-d3']");

    public static final By madeOrder = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ']");


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
        driver.findElement(nextButton).click();
    }

    //выбрать метро
    public void clickMetroStation(){
        driver.findElement(station).click();
    }

    //выбрать дату
    public void pickDate() {
        driver.findElement(date).click();
        driver.findElement(today).click();
    }

    //выбрать, на какой срок
    public void pickHowLong() {
        driver.findElement(howLong).click();
        driver.findElement(oneDay).click();
    }

    //выбрать цвет
    public void chooseColor(){
        driver.findElement(color).click();
    }

    //шаг сделать заказ
    public void makeOrder(){
        isVisible(nameField);
        printInput("Григорий",nameField );
        printInput("Фунтиков",lastnameField );
        printInput("г. Москваб ул. Ленинаб д. 1",adressField );
        printInput("Черкизово",metroField );
        clickMetroStation();
        printInput("+79051234567",phoneNumberField );
        clickNext();
        pickDate();
        pickHowLong();
        chooseColor();
        driver.findElement(orderButton).click();
        driver.findElement(yesButton).click();
        isVisible(madeOrderWindow);
    }
}
