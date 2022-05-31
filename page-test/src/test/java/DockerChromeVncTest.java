
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DockerChromeVncTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DockerChromeVncTest.class);

    WebDriver driver;
    WebDriverManager wdm = WebDriverManager.firefoxdriver().browserInDocker();

    @BeforeEach
    void setupTest() {
        driver = wdm.create();
    }

    @AfterEach
    void teardown() {
        wdm.quit();
    }

    @Test
    void test() {
        driver.get("https://www.sogeti.com");


    }

}
