package mk.ukim.finki.wp.kol2023.g2.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddOrEditMovie extends AbstractPage {

    private WebElement name;
    private WebElement description;
    private WebElement rating;
    private WebElement genre;
    private WebElement director;
    private WebElement submit;

    public AddOrEditMovie(WebDriver driver) {
        super(driver);
    }

    public static ItemsPage add(WebDriver driver, String addPath, String name, String description, String rating, String genre, String director) {
        get(driver, addPath);
        assertRelativeUrl(driver, addPath);

        AddOrEditMovie addOrEditMovie = PageFactory.initElements(driver, AddOrEditMovie.class);
        addOrEditMovie.assertNoError();
        addOrEditMovie.name.sendKeys(name);
        addOrEditMovie.description.sendKeys(description);
        addOrEditMovie.rating.sendKeys(rating);

        Select selectType = new Select(addOrEditMovie.genre);
        selectType.selectByValue(genre);

        Select selectLocation = new Select(addOrEditMovie.director);
        selectLocation.selectByValue(director);

        addOrEditMovie.submit.click();
        return PageFactory.initElements(driver, ItemsPage.class);
    }

    public static ItemsPage update(WebDriver driver, WebElement editButton, String name, String description, String rating, String genre, String director) {
        String href = editButton.getAttribute("href");
        System.out.println(href);
        editButton.click();
        assertAbsoluteUrl(driver, href);

        AddOrEditMovie addOrEditMovie = PageFactory.initElements(driver, AddOrEditMovie.class);
        addOrEditMovie.name.clear();
        addOrEditMovie.name.sendKeys(name);
        addOrEditMovie.description.sendKeys(description);
        addOrEditMovie.rating.sendKeys(rating);

        Select selectType = new Select(addOrEditMovie.genre);
        selectType.selectByValue(genre);

        Select selectLocation = new Select(addOrEditMovie.director);
        selectLocation.selectByValue(director);

        addOrEditMovie.submit.click();
        return PageFactory.initElements(driver, ItemsPage.class);
    }
}