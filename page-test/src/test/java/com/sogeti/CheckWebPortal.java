package com.sogeti;

import com.sogeti.domain.PageForm;


import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class CheckWebPortal {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckWebPortal.class);


    String xpathServices = "html/body/div[1]/header/div[2]/nav/ul/li[3]/div[1]/span";
    String xpathAutomation = "//*[text()='Automation']";


    @Test
    public void testCase1() throws InterruptedException {
        WebDriverManager wdm = WebDriverManager.firefoxdriver().enableVnc().browserInDocker().enableRecording();
        WebDriver driver = wdm.create();
        driver.get("https://www.sogeti.com");
        WebElement cookie_accept = driver.findElement( By.className("acceptCookie"));
        cookie_accept.click();

        Actions actions = new Actions(driver);

        WebElement service = driver.findElement( By.xpath(xpathServices));
        //Verify services is loaded
        actions.moveToElement(service).click().build().perform();


        Thread.sleep(5000);
        WebElement automation = driver.findElement(By.xpath(xpathAutomation));

        actions.moveToElement(automation).click().build().perform();

        //Verify automation is loaded
        Assert.assertTrue(automation.isDisplayed());
        Thread.sleep(1000);

        service = driver.findElement(By.xpath(xpathServices));
        automation = driver.findElement(By.xpath(xpathAutomation));
        //Hover again to Services menu
        actions.moveToElement(service).click().build().perform();
        Assert.assertTrue(service.isEnabled() & automation.isEnabled());
        wdm.quit();

    }

    @Test
    public void testCase2() throws InterruptedException {
        WebDriverManager wdm = WebDriverManager.firefoxdriver().enableVnc().browserInDocker().enableRecording();
        WebDriver driver = wdm.create();
        driver.get("https://www.sogeti.com");
        WebElement cookie_accept = driver.findElement( By.className("acceptCookie"));
        cookie_accept.click();

        Actions actions = new Actions(driver);
        WebElement service = driver.findElement( By.xpath(xpathServices));
        actions.moveToElement(service).click().build().perform();

        WebElement automation = driver.findElement(By.xpath(xpathAutomation));
        actions.moveToElement(automation).click().build().perform();
        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        PageForm form = PageForm.getFormObject();

        WebElement firsName = driver.findElement(By.xpath("//*[@id='4ff2ed4d-4861-4914-86eb-87dfa65876d8']"));
        actions.moveToElement(firsName);
        js.executeScript("arguments[0].scrollIntoView();", firsName);

        firsName.sendKeys("FirstName");
        Thread.sleep(1000);
        WebElement lastName = driver.findElement(By.xpath("//*[@id='11ce8b49-5298-491a-aebe-d0900d6f49a7']"));
        lastName.sendKeys(form.getLastName());
        Thread.sleep(1000);
        WebElement email = driver.findElement(By.xpath("//*[@id='056d8435-4d06-44f3-896a-d7b0bf4d37b2']"));
        email.sendKeys(form.getEmail());
        Thread.sleep(1000);

        WebElement phone = driver.findElement(By.xpath("//*[@id='755aa064-7be2-432b-b8a2-805b5f4f9384']"));
        phone.sendKeys(form.getPhoneNumber());

        WebElement company = driver.findElement(By.xpath("//*[@id='703dedb1-a413-4e71-9785-586d609def60']"));
        company.sendKeys(form.getCompanyName());
        Thread.sleep(1000);
        Select drpCountry = new Select(driver.findElement(By.xpath("//*[@id='e74d82fb-949d-40e5-8fd2-4a876319c45a']")));
        drpCountry.selectByVisibleText("Germany");

        WebElement msg = driver.findElement(By.xpath("//*[@id='88459d00-b812-459a-99e4-5dc6eff2aa19']"));
        msg.sendKeys(form.getMessageSent());
        Thread.sleep(5000);

        WebElement checkBox = driver.findElement(By.xpath("//label[text()='I agree']"));
        checkBox.click();
        Thread.sleep(5000);
        //Captcha
        WebElement captchaCheckBox = driver.findElement(By.xpath("//*[@id='recaptcha-anchor']"));
        captchaCheckBox.click();
        Thread.sleep(3000);

        WebElement summitButton = driver.findElement(By.name("Submit"));
        summitButton.submit();
        Thread.sleep(3000);
        wdm.quit();
    }

    @Test
    public void testCase3() throws InterruptedException {
        WebDriverManager wdm = WebDriverManager.firefoxdriver().enableVnc().browserInDocker().enableRecording();
        WebDriver driver = wdm.create();
        driver.get("https://www.sogeti.com");
        WebElement cookie_accept = driver.findElement( By.className("acceptCookie"));
        cookie_accept.click();

        WebElement worldWide = driver.findElement(By.className("navbar-global"));
        Thread.sleep(5000);

        new Actions(driver).moveToElement(worldWide).click().build().perform();
        Thread.sleep(5000);

        List<WebElement> countries = driver.findElements(By.xpath("//*[@id='country-list-id']/ul/li"));
        for(WebElement e : countries){
           e.click();
           Thread.sleep(3000);
           Assert.assertTrue(e.isEnabled() & e.isDisplayed());
        }
        wdm.quit();
    }



}
