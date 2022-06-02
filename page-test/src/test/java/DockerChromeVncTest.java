
import com.beust.ah.A;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.plaf.TableHeaderUI;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class DockerChromeVncTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DockerChromeVncTest.class);

    String xpathServices = "html/body/div[1]/header/div[2]/nav/ul/li[3]/div[1]/span";
    String xpathAutomation = "//*[text()='Automation']";

    WebDriver driver;
    WebDriverManager wdm = WebDriverManager.firefoxdriver().enableVnc().browserInDocker().enableRecording();

    @BeforeEach
    void setupTest() {
        driver = wdm.create();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    void teardown() {
        wdm.quit();
    }

    @Test
    @DisplayName("Test Check Services & Automation")
    @Disabled
    void testServicesAutomationMenu() throws InterruptedException {

        getMainPageAndCookie();


        Actions actions = new Actions(driver);

        WebElement service = driver.findElement( By.xpath(xpathServices));
        //Verify services is loaded
        actions.moveToElement(service).click().build().perform();


        Thread.sleep(5000);
        WebElement automation = driver.findElement(By.xpath(xpathAutomation));

        actions.moveToElement(automation).click().build().perform();

        //Verify automation is loaded
        Assert.isTrue(automation.isDisplayed(), "Automation Section is not visible!");
        Thread.sleep(1000);

        service = driver.findElement(By.xpath(xpathServices));
        automation = driver.findElement(By.xpath(xpathAutomation));
        //Hover again to Services menu
        actions.moveToElement(service).click().build().perform();
        Assertions.assertTrue(service.isEnabled() & automation.isEnabled());
        Thread.sleep(5000);


    }

    @Test()
    @DisplayName("Test Form Full automation")
    void testCase2() throws InterruptedException {
        getMainPageAndCookie();

        Actions actions = new Actions(driver);
        WebElement service = driver.findElement( By.xpath(xpathServices));
        actions.moveToElement(service).click().build().perform();

        WebElement automation = driver.findElement(By.xpath(xpathAutomation));
        actions.moveToElement(automation).click().build().perform();
        Thread.sleep(5000);



    }
    void getMainPageAndCookie(){
        driver.get("https://www.sogeti.com");
        WebElement cookie_accept = driver.findElement( By.className("acceptCookie"));
        cookie_accept.click();
    }

}
