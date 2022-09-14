package com.upwork.pages;

import com.upwork.basepage.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class SearchPage extends BasePage {

    private Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass());

    @FindBy(css = "div.up-card div.up-card-hover")
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
    public List<WebElementFacade> freelancerCountryName;

    public String getJobProgress(String freelancerName) {

        String xpath = "//div[@class='identity-name' and contains(text(),'" + freelancerName + "')]" +
                "/ancestor::div[contains(@class,'up-card-hover')]" +
                "//div[contains(@class,'profile-stats')]" +
                "//span[@class='up-job-success-text']";


        String jobProgressText = "";

        WebElement jobProgress = null;
        if (getDriver().findElements(By.xpath(xpath)).size() != 0) {
            jobProgress = getDriver().findElement(By.xpath(xpath));
            String[] jobProgressFullText = jobProgress.getText().split(" ");
            jobProgressText = jobProgressFullText[0];


        } else {
            jobProgressText = "No Job Progress Rate available for " + freelancerName;
        }
        logger.info("Locating Job progress status for " + freelancerName);
        return jobProgressText;
    }

    public WebElement getOverviewWebelement(String freelancerName) {

        String xpath = "//div[@class='identity-name' and contains(text(),'" + freelancerName + "')]" +
                "/ancestor::div[contains(@class,'up-card-hover')]" +
                "//div[contains(@class,'break')]" +
                "/div[contains(@class,'clamped')]";

        WebElement overViewLocator = getDriver().findElement(By.xpath(xpath));
        logger.info("Locating Overview / Summary for " + freelancerName);
        return overViewLocator;
    }

    public List<WebElement> getSkillsWebElement(String freelancerName) {
        String xpath = "//div[@class='identity-name' and contains(text(),'" + freelancerName + "')]" +
                "/ancestor::div[contains(@class,'up-card-hover')]" +
                "//div[@class='up-skill-badge']";

        List<WebElement> skillsLocator = getDriver().findElements(By.xpath(xpath));
        logger.info("Locating Skills for " + freelancerName);
        return skillsLocator;
    }


    public void clickOnRandomFreelancer(String randomFreelancer) {

        for (WebElementFacade freelancer : freelancerName) {
            if (freelancer.getText().trim().equals(randomFreelancer)) {
                Actions actions = new Actions(getDriver());
                actions.moveToElement(freelancer);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", freelancer);
                freelancer.click();
            }
        }
        logger.info("Random Freelancer is located and scrolled to element and clicked" + freelancerName);
    }


}
