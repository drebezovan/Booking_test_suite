package tests;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import pages.HomePage;
import pages.HotelPage;
import pages.MapPage;
import pages.SearchCity;
import pages_2.AttractionsPage;
import pages_2.FirstAttractionPage;
import pages_2.SearchAttractions;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

public class SelenideTests {
    HomePage homePage = new HomePage();
    SearchCity searchCity = new SearchCity();
    MapPage mapPage = new MapPage();
    HotelPage hotelPage = new HotelPage();
    AttractionsPage rentCarPage = new AttractionsPage();
    SearchAttractions searchAttractions = new SearchAttractions();
    FirstAttractionPage firstAttractionPage = new FirstAttractionPage();
    private String city = "Лондон";
    private String startData = "2023-04-29";
    private String endData = "2023-05-02";

    @Test
    @DisplayName("Тест-кейс 1")
    public void testScenario_1() throws InterruptedException {

        //1.1 зайти на сайт https://www.booking.com/
        //1.2 ввести в поиске любой город(заграничный)
        //1.3 выбрать случайные даты
        //1.4 нажать на кнопку «Найти»
        //1.5 нажать на кнопку «Показать на карте»
        //1.6 навести курсор на первый отель(карточка слева)
        //1.7 сохранить(в переменные) название отеля, количество звезд, среднюю оценку,
        // количество отзывов, стоимость
        //1.8 нажать на прыгающий маркер на карте
        //1.9 на открывшейся странице отеля проверить название отеля,
        // количество звезд, среднюю оценку, количество отзывов и стоимость

        homePage.openHomePage()
                .acceptCookie()
                .searchCityAndData(city, startData, endData);

        searchCity.checkCityHeader(city)
                .clickMapButton();

        String name = mapPage.getNameOfHotel().trim();
        Long numberOfStars = mapPage.getNumberOfStar();
        String rating = mapPage.getAverageRating().trim();
        String reviews = mapPage.getNumberOfReviews().trim();
        String cost = mapPage.getCost().trim();

        mapPage.findFirstHotel();
        mapPage.clickMovingMarker();

        String newName = hotelPage.getHotelName().trim();
        Long newNumberOfStars = hotelPage.getStars();
        String newRating = hotelPage.getAverageRating();
        String newReview = hotelPage.getNumberOfReviews().trim();
        String newCost = hotelPage.getCost().trim();

        assertEquals(name, newName);
        assertEquals(numberOfStars, newNumberOfStars);
        assertEquals(rating, newRating);
        assertEquals(reviews, newReview);
        assertEquals(cost, newCost);
    }

    @Test
    @DisplayName("Тест-кейс 2")
    public void testScenario_2() throws InterruptedException {

        //2.1 зайти на сайт https://www.booking.com/
        //2.2 нажать на кнопку «Варианты досуга»
        //2.3 ввести в поиске любой город(заграничный)
        //2.4 выбрать случайные даты
        //2.5 нажать на кнопку «Проверить цены»
        //2.6 на открывшейся странице достопримечательностей нажать на кнопку "Самая низкая цена"
        //2.7 сохранить(в переменные) название первой достопримечательности и цену
        //2.8 нажать на первую достопримечательность
        //2.7 на открывшейся странице достопримечательности проверить название и цену

        homePage.openHomePage()
                .acceptCookie()
                .clickAttractionsButton();

        rentCarPage.searchCityAndData(city, startData, endData);
        searchAttractions.clickLowPrice();

        String nameFirstAttraction = searchAttractions.getNameFirstAttraction();
        String priseFirstAttraction = searchAttractions.getPriceFirstAttraction();

        searchAttractions.clickFirstAtracction();

        String nameAttraction = firstAttractionPage.getName();
        String priseAttraction = firstAttractionPage.getPrice();

        assertAll(
                () -> assertEquals(nameFirstAttraction, nameAttraction),
                //названия через раз не совпадают
                () -> assertEquals(priseFirstAttraction, priseAttraction)
                //цены на страницах не совпадают
        );
    }
}