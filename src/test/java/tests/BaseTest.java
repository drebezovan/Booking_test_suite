package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

public class BaseTest {
    ObjectMapper mapper;
    File file;
    @BeforeEach
    public void setup(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        mapper = new ObjectMapper(new YAMLFactory());
        file = new File("src/test/java/yaml/inputData.yml");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.remote = "http://localhost:4444/wd/hub";
        System.setProperty("chromeoptions.args", "\"--no-sandbox\",\"--disable-dev-shm-usage\",\"--remote-debugging-port=9222\"");
    }
    @AfterEach
    public void turnDown() {
        Selenide.closeWebDriver();
    }
}