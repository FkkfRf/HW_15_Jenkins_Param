import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import static io.qameta.allure.Allure.step;
import helpers.Attach;

public class BaseTest {

    @BeforeAll
    static void setUp() {

        step("Устанавливаем интеграцию с Selenide", () -> {
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        });


        step("Устанавливаем конфигурацию", () -> {
            DesiredCapabilities capabilities = new DesiredCapabilities();
 //           capabilities.setCapability("browserName", "edge");
//        capabilities.setCapability("browserVersion", "100.0");
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        });
        step("Открываем тестируемую форму в заданной конфигурации браузера", () -> {
            Configuration.baseUrl = "https://demoqa.com";
            Configuration.browserSize = "1920x1080";
            Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        });
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

}

