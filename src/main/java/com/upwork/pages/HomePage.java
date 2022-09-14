package com.upwork.pages;

import com.upwork.basepage.BasePage;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.assertj.core.api.Assertions.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class HomePage extends BasePage {

    /**
     * Home Page locators are defined here
     **/

    private Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(id = "px-captcha")
    public List<WebElement> recap;

    @FindBy(css = "span.nav-logo")
    public WebElementFacade upworklogo;

    @FindBy(css = "div#nav-main button[data-cy='search-switch-btn']")
    public WebElementFacade catalogDropDown;

    @FindBy(xpath = "//div[@id='nav-main']//span[contains(@class,'query')]")
    public List<WebElementFacade> catalogQuery;


    @FindBy(css = "div#nav-main input[type='search']")
    public WebElementFacade searchTextBox;

    @FindBy(css = "a span.is-match")
    public List<WebElementFacade> searchMenuMatch;


    /*@FindBy(css = "div.up-card div.up-card-hover")
    public List<WebElementFacade> freelancerCard;

    @FindBy(css = "div.identity-name")
    public List<WebElementFacade> freelancerName;

    @FindBy(css = "div[data-qa=rate]>span>strong")
    public List<WebElementFacade> freelancerRateinDollars;

    @FindBy(css = "div[data-qa=rate]>span>span")
    public List<WebElementFacade> freelancerRatePerhour;

    @FindBy(css = "p.freelancer-title>strong")
    public List<WebElementFacade> freelancerTitle;

    @FindBy(css = "div.up-job-success-bar>span")
    public List<WebElementFacade> jobsuccessPercentage;


    @FindBy(css = "span[itemprop='country-name']")
    public List<WebElementFacade> freelancerCountryName;*/


    @SneakyThrows
    public HomePage checkCaptcha() {
        if (recap.size() != 0) {
            logger.info("Captcha is seen - Waiting for 1 minute");
            Thread.sleep(60000);
            if(recap.size() != 0) {
                Assertions.fail("The captcha is not selected within the time limit");
            }
        }
        return this;
    }

    public HomePage click(WebElement webElement) {
        super.clickOn(webElement);
        logger.info("Clicked on the " + webElement);
        return this;
    }

    public HomePage selectCatalog(String text) {
        for (WebElementFacade elementFacade : catalogQuery) {
            if (elementFacade.getText().contains(text)) {
                click(elementFacade);
                logger.info("Element contains text " + text +  " So clicked on " + elementFacade);
                break;
            }
        }
        return this;
    }



}
