import POM.MainPage;
import POM.OrderPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class OrderTests {

    private WebDriver driver;

    @Before
    public void before() {
        setupBrowser("Chrome");
    }

    private void setupBrowser (String browser) {
        if (browser == "Firefox") {
            System.setProperty("webdriver.gecko.driver", "/Users/svetlana/Documents/WebDriver/bin/geckodriver");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            driver = new FirefoxDriver();
        }
        if (browser == "Chrome") {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(chromeOptions);
        }
       //когда Хром - найдена ошибка, указанная в задании, в FireFox все тесты проходят корректно!
    }

    @Test
    public void checkMadeOrderFirstButton(){
        OrderPage orderPage = new OrderPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickVisibleFirstOrderButton(mainPage.firstOrderButton);
        orderPage.makeOrder();
        String actualText = driver.findElement(orderPage.madeOrder).getText();
        Assert.assertTrue(actualText.contains("Заказ оформлен"));
    }

    @Test
    public void checkMadeOrderSecondButton(){
        OrderPage orderPage = new OrderPage(driver);
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.scrollAndClickSecondOrderButton(mainPage.secondOrderButton);
        orderPage.makeOrder();
        String actualText = driver.findElement(orderPage.madeOrder).getText();
        Assert.assertTrue(actualText.contains("Заказ оформлен"));
    }

    @After
    public void after() {
        driver.quit();
    }

}
