import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class TextBoxTests extends BaseTest {


    @Feature("Проверка заполнения формы DemoQA. Проверка формы подтверждения")
    @Story("Заполнение формы DemoQA и проверка подтверждающей формы")
    @Owner("FkkffRf")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "automation-practice-form", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("Заполнение формы DemoQA")
    @Test
    void fillFormTest(){
        step("Открываем тестируемую форму ", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });
        step("Убираем рекламные баннеры ", () -> {
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        });

        step("Заполняем текстом ФИ Email ", () -> {
        $("#firstName").setValue("StudentName ");
        $("#lastName").setValue(" StudentLastName");
        $("#userEmail").setValue("User@email.com");
        });
        step("Указываем пол", () -> {
        $("#gender-radio-1").parent().click();
        $("#genterWrapper").$(byText("Female")).click();
        });
        step("Заполняем текстом № телефона ", () -> {
        $("#userNumber").setValue("8299000001");
        });
        step("Выбираем в календаре дату рождения ", () -> {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("May");
        $(By.className("react-datepicker__year-select")).selectOption("1975");
        $(".react-datepicker__day--029:not(.react-datepicker__day--outside-month").click();
        });
        step("Загружаем файл  ", () -> {
        $("#uploadPicture").uploadFromClasspath("ForDemoQA.txt");
        });
        step("Выбираем предметы ", () -> {
        $("#subjectsInput").setValue("a").pressEnter().setValue("i").pressEnter().setValue("m").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $(byText("Music")).click();
        $(by("for","hobbies-checkbox-2")).click();
        });
        step("Заполняем текстом адрес ", () -> {
        $("#currentAddress").setValue(" 221b \n Baker St, \n  London, Grate Britain ");
        $("#state").click();
        });
        step("Выбираем штат и город ", () -> {
        $(byText("Haryana")).click();
        $("#city").click();
        $(byText("Karnal")).click();
        $("#submit").click();
        });
        step("Проверяем подтверждение заполнения формы ", () -> {
        $(".modal-dialog").should(appear);
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        });
        step("Проверяем заполнение формы подтверждения", () -> {
        $(".table-responsive").$(byText("Student Name"))
                .parent().shouldHave((text("StudentName StudentLastName")));
        $(".table-responsive").$(byText("Student Email"))
                .parent().shouldHave((text("User@email.com")));
        $(".table-responsive").$(byText("Gender"))
                .parent().shouldHave((text("Female")));
        $(".table-responsive").$(byText("Mobile"))
                .parent().shouldHave((text("8299000001")));
        $(".table-responsive").$(byText("Date of Birth"))
                .parent().shouldHave((text("29 May,1975")));
        $(".table-responsive").$(byText("Subjects"))
                .parent().shouldHave((text("Maths, Hindi, Chemistry")));
        $(".table-responsive").$(byText("Hobbies"))
                .parent().shouldHave((text("Sports, Music, Reading")));
        $(".table-responsive").$(byText("Picture"))
                .parent().shouldHave((text("ForDemoQA.txt")));
        $(".table-responsive").$(byText("Address"))
                .parent().shouldHave((text("221b Baker St, London, Grate Britain")));
        $(".table-responsive").$(byText("State and City"))
                .parent().shouldHave((text("Haryana Karnal")));
        });
    }
}



