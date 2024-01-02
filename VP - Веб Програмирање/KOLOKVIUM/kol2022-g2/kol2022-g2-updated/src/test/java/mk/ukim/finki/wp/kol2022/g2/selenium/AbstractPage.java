package mk.ukim.finki.wp.kol2022.g2.selenium;

import mk.ukim.finki.wp.exam.util.ExamAssert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AbstractPage {

    protected WebDriver driver;

    @FindBy(tagName = "h1")
    private List<WebElement> h1;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public static void get(WebDriver driver, String relativeUrl) {
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:9999") + relativeUrl;
        driver.get(url);
    }

    public static boolean assertRelativeUrl(WebDriver driver, String relativeUrl) {
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:9999") + relativeUrl;
        String current = driver.getCurrentUrl();
        return ExamAssert.assertUrlEquals("Requesting " + relativeUrl, url, current);
    }

    public static boolean assertAbsoluteUrl(WebDriver driver, String url) {
        String current = driver.getCurrentUrl();
        return ExamAssert.assertUrlEquals("Requesting " + url, url, current);
    }

    public void assertNoError() {
        if (this.h1 != null && !this.h1.isEmpty()) {
            String text= this.h1.get(0).getText();
            ExamAssert.assertNotEquals("Check error", "Whitelabel Error Page", text);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
