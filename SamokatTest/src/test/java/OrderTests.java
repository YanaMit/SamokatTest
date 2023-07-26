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
    public OrderTests(String name, String lastName, String adress, String metroStation, String phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.adress = adress;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
    }

    @Parameterized.Parameters
    public static Object[][] myOrderTests() {
        return new Object[][]{

                {"Григорий", "Фунтиков", "г. Москваб ул. Ленинаб д. 1", "Черкизово", "+79051234567"},
               };
    }

    @Test
    public void checkMadeOrderFirstButton(){
        OrderPage orderPage = new OrderPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickVisibleFirstOrderButton();
        orderPage.makeOrder(name, lastName, adress, metroStation, phoneNumber);
        Assert.assertTrue(orderPage.getActualText().contains("Заказ оформлен"));
    }

    @Test
    public void checkMadeOrderSecondButton(){
        OrderPage orderPage = new OrderPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.scrollAndClickSecondOrderButton();
        orderPage.makeOrder(name, lastName, adress, metroStation, phoneNumber);
        Assert.assertTrue(orderPage.getActualText().contains("Заказ оформлен"));
    }

    @After
    public void after() {
        driver.quit();
    }

}
