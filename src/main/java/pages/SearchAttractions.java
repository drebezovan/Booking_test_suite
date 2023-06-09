package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import steps.PageWithAttractionParameters;

import static com.codeborne.selenide.Selenide.$;

public class SearchAttractions implements PageWithAttractionParameters {
    public static final By LOW_PRICE_BUTTON = By.xpath("//label[text()='Самая низкая цена']");
    public static final By NAME_FIRST_ATTRACTION = By.xpath("//h4");
    public static final By PRICE_FIRST_ATTRACTION = By.cssSelector("div.ac78a73c96");
    public static final By FIRST_ATTRACTION_FIELD = By.cssSelector("[data-testid='card']");

    @Step("На открывшейся странице достопримечательностей нажать на кнопку «Самая низкая цена»")
    public SearchAttractions clickLowPrice() throws InterruptedException {
        Thread.sleep(5000);
        $(LOW_PRICE_BUTTON).shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Сохранить(в переменную) название первой достопримечательности")
    public String getNameOfAttraction() {
        return $(NAME_FIRST_ATTRACTION).innerText();
    }

    @Step("Сохранить(в переменную) цену первой достопримечательности")
    public String getPriceOfAttraction() {
        return $(PRICE_FIRST_ATTRACTION).innerText();
    }

    @Step("Нажать на первую достопримечательность")
    public SearchAttractions clickFirstAttraction() throws InterruptedException {
        $(FIRST_ATTRACTION_FIELD).click();
        Selenide.switchTo().window(1);
        Thread.sleep(5000);
        return this;
    }
}