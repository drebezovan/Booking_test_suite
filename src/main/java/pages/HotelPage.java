package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import steps.PageWithHotelParameters;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class HotelPage implements PageWithHotelParameters {

    public static final By HOTEL_NAME = By.xpath("//h2");
    public static final By STAR = By.cssSelector("[data-testid='rating-stars']");
    public static final By SQUARE = By.cssSelector("[data-testid='rating-squares']");
    public static final By AVERAGE_RATING = By.cssSelector("div.b5cd09854e.d10a6220b4");
    public static final By NUMBER_OF_REVIEWS = By.cssSelector("div.d8eab2cf7f.c90c0a70d3.db63693c62");
    public static final By COST = By.cssSelector(".prco-valign-middle-helper");

    @Step("На открывшейся странице отеля проверить количество звезд")
    public String getNumberOfStars() {
        Long count;
        if ($(STAR).exists()) {
            count = executeJavaScript("return $(arguments[0]).children().length", $(STAR));
        } else {
            count = executeJavaScript("return $(arguments[0]).children().length", $(SQUARE));
        }
        assert count != null;
        return count.toString();
    }

    @Step("На открывшейся странице отеля проверить название отеля")
    public String getNameOfHotel() {
        return $(HOTEL_NAME).innerText();
    }

    @Step("На открывшейся странице отеля проверить среднюю оценку")
    public String getAverageRating() {
        return $(AVERAGE_RATING).innerText();
    }

    @Step("На открывшейся странице отеля проверить количество отзывов")
    public String getNumberOfReviews() {
        return $(NUMBER_OF_REVIEWS).innerText().trim();
    }

    @Step("На открывшейся странице отеля проверить стоимость")
    public String getCost() {
        return $(COST).innerText().trim();
    }
}