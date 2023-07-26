import pom.MainPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)

public class TextTests extends SetupBrowser {

    private WebDriver driver;

   @Before
    public void before() {
            this.driver = setupBrowser("Chrome");
   }

        private  String answerText;
        private  int number;
        private  String openedAnswers;


    public TextTests(int number, String openedAnswers, String answerText) {
        this.number = number;
        this.openedAnswers = openedAnswers;
        this.answerText = answerText;
    }

    @Parameterized.Parameters
    public static Object[][] myTextTests() {
        return new Object[][]{

                { 0,"Сколько это стоит? И как оплатить?","Сутки — 400 рублей. Оплата курьеру — наличными или картой." },
                { 1,"Хочу сразу несколько самокатов! Так можно?","Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим." },
                { 6,"Можно ли отменить заказ?","Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои." },

        };
    }

    @Test
    public void checkAnswerTextInAccordion (){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickQuestionsAboutImportant(number);
        Assert.assertEquals("Текст ответа не совпадает", answerText, mainPage.getTextFromAnswer(number));
    }

   @Test
    public void checkTextOfQuestion() {
        MainPage mainPage = new MainPage(driver);
        Assert.assertEquals("Текст вопроса не совпадает",openedAnswers,mainPage.getTextOfQuestion(number));
    }


    @After
    public void after() {
        driver.quit();
    }

}