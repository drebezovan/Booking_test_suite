package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
import steps.LocalDataStep;
import steps.MapAssertions;
import yml.InputData;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static random.RandomNumber.getRandomIntegerBetweenRange;

@Epic("Web tests")
@Feature("Booking website")
@DisplayName("Проверка параметров")
@Link(name = "Ссылка сайта", url = "https://www.booking.com/")
@Owner("Дребезова Наталья")
public class SelenideTests extends BaseTest{
    HomePage homePage = new HomePage();
    SearchCity searchCity = new SearchCity();
    MapPage mapPage = new MapPage();
    HotelPage hotelPage = new HotelPage();
    AttractionsPage attractionsPage = new AttractionsPage();
    SearchAttractions searchAttractions = new SearchAttractions();
    FirstAttractionPage firstAttractionPage = new FirstAttractionPage();
    LocalDataStep localDataStep = new LocalDataStep();


    String startData = localDataStep.getStartData();
    String endData = localDataStep.getEndData();
    InputData inputData;
    int randomNum = getRandomIntegerBetweenRange(0, 10);

    @Test
    @DisplayName("Проверка параметров отеля ")
    @Description("Проверяем совпадение параметров " +
            "(название, количество звезд, среднюю оценку, количество отзывов и стоимость) " +
            "отеля на странице с картой и на странице отеля")
    @Severity(SeverityLevel.BLOCKER)
    public void testScenario_1() throws InterruptedException, IOException {

        inputData = mapper.readValue(new File("src/test/java/yaml/inputData.yml"), InputData.class);

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
                .closeRegistrationWindow()
                .selectLanguage("Русский")
                .searchCityAndData(inputData.getCity().get(randomNum), startData, endData);

        searchCity.checkCityHeader(inputData.getCity().get(randomNum))
                .clickMapButton();

        Map<String, String> hotelParamsFromMapPage = mapPage.getParameterMap();

        mapPage.findFirstHotel();
        mapPage.clickMovingMarker();

        Map<String, String> hotelParamsFromHotelPage = hotelPage.getParameterMap();
        MapAssertions.assertMapEquals(hotelParamsFromMapPage, hotelParamsFromHotelPage);
    }

    @Test
    @DisplayName("Проверка параметров достопримечательности ")
    @Description("Проверяем совпадение параметров (название и цену) " +
            "достопримечательности на странице всех достопримечательностей города " +
            "и на странице выбранной достопримечательности ")
    @Severity(SeverityLevel.BLOCKER)
    public void testScenario_2() throws InterruptedException, IOException {

        inputData = mapper.readValue(new File("src/test/java/yaml/inputData.yml"), InputData.class);

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
                .closeRegistrationWindow()
                .selectLanguage("Русский")
                .clickAttractionsButton();

        attractionsPage.searchCityAndData(inputData.getCity().get(randomNum), startData, endData);
        searchAttractions.clickLowPrice();

        Map<String, String> attractionParamsFromSearchAttractions = searchAttractions.getAttractionParams();

        searchAttractions.clickFirstAttraction();

        Map<String, String> attractionParamsFromFirstAttractionPage = firstAttractionPage.getAttractionParams();
        MapAssertions.assertMapEquals(attractionParamsFromSearchAttractions, attractionParamsFromFirstAttractionPage);
    }
}