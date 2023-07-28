import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pom.MainPage;
import pom.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


@RunWith(Parameterized.class)

public class OrderTests extends SetupBrowser {

   private WebDriver driver;

   @Before
    public void before() {
        this.driver = setupBrowser("Chrome");
    }


    private String name;
    private String lastName;
    private String adress;
    private String metroStation;
    private String phoneNumber;
    private String date;
    private String howLong;
    private String color;

    public OrderTests(String name, String lastName, String adress, String metroStation,
                      String phoneNumber, String date, String howLong, String color) {
        this.name = name;
        this.lastName = lastName;
        this.adress = adress;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.howLong = howLong;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] myOrderTests() {
        return new Object[][]{

                {"Григорий", "Фунтиков", "г. Москва, ул. Ленина, д. 1", "Черкизовская",
                        "+79051234567", "11.08.2023", "сутки", "черный жемчуг"},
                {"Мария", "Иванова", "г. Саратов ул. Чушкина д. 123", "Павелецкая",
                        "+79260234597", "17.08.2023", "трое суток", "серая безысходность"},
               };
    }

    @Test
    public void checkMadeOrderFirstButton(){
        OrderPage orderPage = new OrderPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickVisibleFirstOrderButton();
        orderPage.makeOrder(name, lastName, adress, metroStation, phoneNumber, date, howLong, color);
        Assert.assertTrue(orderPage.getActualText().contains("Заказ оформлен"));
    }

    @Test
    public void checkMadeOrderSecondButton(){
        OrderPage orderPage = new OrderPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.scrollAndClickSecondOrderButton();
        orderPage.makeOrder(name, lastName, adress, metroStation, phoneNumber, date, howLong, color);
        Assert.assertTrue(orderPage.getActualText().contains("Заказ оформлен"));
    }

    @After
    public void after() {
        driver.quit();
    }

}
