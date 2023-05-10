package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import config.ProjectConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HomePage {
    public static final ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);
    public static final By REGISTRATION_MODAL_WINDOW = By.cssSelector
            ("button.fc63351294.a822bdf511.e3c025e003.fa565176a8.f7db01295e.c334e6f658.ae1678b153");
    public static final By CHANGE_LANGUAGE_BUTTON = By.cssSelector("button[data-testid='header-language-picker-trigger']");
    public static final By LANGUAGE_BUTTON = By.cssSelector("span.cf67405157");
    public static final By ACCEPT_COOKIE_BUTTON = By.xpath("//button[@id='onetrust-accept-btn-handler']");
    public static final By SEARCH_FIELD = By.xpath("//input[@id=':Ra9:']");
    public static final By SUBMIT_BUTTON = By.xpath("//button[@type='submit']");
    public static final By FIRST_IN_DROPDOWN = By.cssSelector("div.a40619bfbe");
    public static final By DATA_FIELD = By.xpath("//div[@class='ed2ff9f661' or @class='d606c76c5a']");

    public static final String DATA = "span[data-date='%s']";
    public static final By ATTRACTIONS_BUTTON = By.id("attractions");

    //div[@class='e5aa33035e']//button

    @Step("Открыть страницу сайта https://www.booking.com/")
    public HomePage openHomePage() {
        Selenide.open(cfg.baseUrl());
        return this;
    }

    @Step("Нажать на кнопку «Принять» (файлы Cookie) ")
    public HomePage closeRegistrationWindow() {
        $(REGISTRATION_MODAL_WINDOW).click();
//        $(ACCEPT_COOKIE_BUTTON).click();
        return this;
    }
    @Step("Выбрать язык сайта")
    public HomePage selectLanguage(String languageName) {
        $(CHANGE_LANGUAGE_BUTTON).click();
        $$(LANGUAGE_BUTTON).findBy(Condition.text(languageName)).click();
        return this;
    }

    @Step("Ввести в поиске любой город(заграничный) с параметром city={0}, " +
            "выбрать случайные даты с параметрами startData={1} и endData={2} " +
            " и нажать на кнопку «Найти»")
    public HomePage searchCityAndData(String city, String startData, String endData) {
        $(SEARCH_FIELD).sendKeys(city);
        $(FIRST_IN_DROPDOWN).shouldHave(Condition.text(city));
        $(DATA_FIELD).click();
        $(String.format(DATA, startData)).click();
        $(String.format(DATA, endData)).click();
        $(SUBMIT_BUTTON).click();
        return this;
    }

    @Step("Нажать на кнопку «Варианты досуга»")
    public HomePage clickAttractionsButton() {
        $(ATTRACTIONS_BUTTON).click();
        return this;
    }
}