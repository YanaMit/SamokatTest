import POM.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TextTests {

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

        }

    @Test
    public void checkHowMuchButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.scrollToButtonAndClick(mainPage.howMuch);
        mainPage.isVisible(mainPage.accordeon0);
        String actualText = driver.findElement(mainPage.accordeon0).getText();
        Assert.assertEquals("Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                actualText);
    }

    @Test
    public void checkManyItemsButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.scrollToButtonAndClick (mainPage.manyItems);
        mainPage.isVisible(mainPage.accordeon1);
        String actualText = driver.findElement(mainPage.accordeon1).getText();
        Assert.assertEquals("Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                    actualText);
    }

    @Test
    public void checkCancelOrderButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.scrollToButtonAndClick (mainPage.cancelOrder);
        mainPage.isVisible(mainPage.accordeon6);
        String actualText = driver.findElement(mainPage.accordeon6).getText();
        Assert.assertEquals("Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                actualText);
    }



    @After
    public void after() {
        driver.quit();
    }


}
