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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;


@RunWith(Parameterized.class)

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

        private final By clickToQuestion;
        private final By openedAnswer;
        private final String anwserText;

    public TextTests(By clickToQuestion, By openedAnswer, String anwserText) {
        this.clickToQuestion = clickToQuestion;
        this.openedAnswer = openedAnswer;
        this.anwserText = anwserText;
    }

    @Parameterized.Parameters
    public static Object[][] myTextTests() {
        return new Object[][]{
                {MainPage.howMuch, MainPage.accordeon0,"Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {MainPage.manyItems, MainPage.accordeon1, "Пока что у нас так: один заказ — один самокат. " +
                        "Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {MainPage.cancelOrder, MainPage.accordeon6, "Да, пока самокат не привезли. Штрафа не будет, " +
                        "объяснительной записки тоже не попросим. Все же свои."},
        };
    }

    @Test
    public void checkAccordionButtons() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.scrollToButtonAndClick(clickToQuestion);
        mainPage.isVisible(openedAnswer);
        String actualText = driver.findElement(openedAnswer).getText();
        Assert.assertEquals(anwserText,
                actualText);
    }

    @After
    public void after() {
        driver.quit();
    }

}