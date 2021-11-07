package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import static java.lang.Thread.sleep;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.close;


public class Case1 {
    public static ChromeDriver driver;

    @Before
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver","C:\\webdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public static void tearDown(){
        driver.quit();
        close();
    }


    @Дано("Вход в бсл через (.*) с паролем (.*)")
    public void вход_в_бсл_через_с_паролем(String login, String password) throws InterruptedException {
        driver.get("https://sso.kz00c1.kz.infra/opensso/UI/Login?goto=https%3A%2F%2Fbsl.kz00c1.kz.infra%2Fbsl");
        sleep(2000);
        driver.findElementById("IDToken1").sendKeys(login);
        sleep(2000);
        driver.findElementById("IDToken2").sendKeys(password);
        sleep(2000);
        driver.findElementById("kc-login").click();
    }

    @Тогда("Нажать на создать банк")
    public void нажать_на_создать_банк() throws InterruptedException {
        driver.findElementByXPath("/html/body/div[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/a/div").click();
        sleep(2000);
    }

    @Дано("Код банка")
    public void код_банка() throws InterruptedException {
        driver.findElementById("id24").sendKeys("TestingA");
        sleep(2000);
    }

    @Дано("В поле второстепенный банковский код ввести (.*)")
    public void в_поле_второстепенный_банковский_код_ввести(String code) throws InterruptedException {
        driver.findElementById("id25").sendKeys(code);
        sleep(2000);
    }

    @Дано("Наименование банка")
    public void наименование_банка() throws InterruptedException {
        driver.findElementById("id26").sendKeys("Test Bank");
        sleep(2000);
    }

    @Дано("В поле статус выбрать значение (.*)")
    public void в_поле_статус_выбрать_значение(String status) throws InterruptedException {
        driver.findElementById("id27").sendKeys(status);
        sleep(2000);
    }

    @Дано("Сдвиг даты платежа")
    public void сдвиг_даты_платежа() throws InterruptedException {
        driver.findElementById("id29").sendKeys("12");
        sleep(2000);
        driver.findElementById("id15").click();
        sleep(2000);
    }

    @Тогда("Проверка деталей банка")
    public void проверка_деталей_банка() throws InterruptedException {
        String bank_code = driver.findElementByXPath("/html/body/div[2]/div[1]/div[1]/div/div[1]/ul[1]/li[2]/span[2]/span").getText();
        Assert.assertNotNull(bank_code);
        sleep(2000);
    }
}
