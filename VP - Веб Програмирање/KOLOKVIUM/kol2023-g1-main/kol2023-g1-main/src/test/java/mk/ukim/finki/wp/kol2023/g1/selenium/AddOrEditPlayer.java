package mk.ukim.finki.wp.kol2023.g1.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddOrEditPlayer extends AbstractPage {

    private WebElement name;
    private WebElement bio;
    private WebElement pointsPerGame;
    private WebElement position;
    private WebElement team;
    private WebElement submit;

    public AddOrEditPlayer(WebDriver driver) {
        super(driver);
    }

    public static ItemsPage add(WebDriver driver, String addPath, String name, String bio, String pointsPerGame, String position, String team) {
        get(driver, addPath);
        assertRelativeUrl(driver, addPath);

        AddOrEditPlayer addOrEditPlayer = PageFactory.initElements(driver, AddOrEditPlayer.class);
        addOrEditPlayer.assertNoError();
        addOrEditPlayer.name.sendKeys(name);
        addOrEditPlayer.bio.sendKeys(bio);
        addOrEditPlayer.pointsPerGame.sendKeys(pointsPerGame);

        Select selectType = new Select(addOrEditPlayer.position);
        selectType.selectByValue(position);

        Select selectLocation = new Select(addOrEditPlayer.team);
        selectLocation.selectByValue(team);

        addOrEditPlayer.submit.click();
        return PageFactory.initElements(driver, ItemsPage.class);
    }

    public static ItemsPage update(WebDriver driver, WebElement editButton, String name, String bio, String pointsPerGame, String position, String team) {
        String href = editButton.getAttribute("href");
        System.out.println(href);
        editButton.click();
        assertAbsoluteUrl(driver, href);

        AddOrEditPlayer addOrEditPlayer = PageFactory.initElements(driver, AddOrEditPlayer.class);
        addOrEditPlayer.name.clear();
        addOrEditPlayer.name.sendKeys(name);
        addOrEditPlayer.bio.sendKeys(bio);
        addOrEditPlayer.pointsPerGame.sendKeys(pointsPerGame);

        Select selectType = new Select(addOrEditPlayer.position);
        selectType.selectByValue(position);

        Select selectLocation = new Select(addOrEditPlayer.team);
        selectLocation.selectByValue(team);

        addOrEditPlayer.submit.click();
        return PageFactory.initElements(driver, ItemsPage.class);
    }
}