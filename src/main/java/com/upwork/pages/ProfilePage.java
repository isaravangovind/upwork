package com.upwork.pages;

import com.upwork.basepage.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

public class ProfilePage extends BasePage {

    /**
     * Profile Page locators are defined here
     **/



    @FindBy(xpath = "//h1[contains(text(),'Pri')]/ancestor::div[@class='profile-viewer']//div[contains(@class,'clamped')]/following-sibling::button")
    public WebElementFacade moreBtn;

    @FindBy(css = "div.profile-viewer div>section.up-card-section div>h1")
    public WebElementFacade viewprofile_freelancerName;


    @FindBy(css = "div.profile-viewer div>section.up-card-section span[itemprop='country-name']")
    public WebElementFacade viewprofile_countryName;

    @FindBy(css = "div.profile-viewer div>section.up-card-section div.d-flex h2")
    public WebElementFacade viewprofile_Title;

    @FindBy(css = "div.profile-viewer div>section.up-card-section div.d-flex div[class*='job-success'] h3[role='presentation']")
    public WebElementFacade viewprofile_Job_successRate;

    @FindBy(css = "div.profile-viewer div>section.up-card-section div.d-flex h3[role='presentation']>span")
    public WebElementFacade viewprofile_RatePerHour;

    @FindBy(xpath = "//div[contains(@class,'clamped')]/following-sibling::button//preceding-sibling::div/span")
    public WebElementFacade viewprofile_Summary;

    @FindBy(xpath = "//div[contains(@class,'clamped')]/following-sibling::button/ancestor::section[contains(@class,'up-card-section')]/following-sibling::section/div[@class='skills']//li//span[@class='up-skill-badge']")
    public List<WebElementFacade> viewprofile_SkillBadgeWithoutLink;

    @FindBy(xpath = "//div[contains(@class,'clamped')]/following-sibling::button/ancestor::section[contains(@class,'up-card-section')]/following-sibling::section/div[@class='skills']//li//a[contains(@class,'up-skill-badge')]")
    public List<WebElementFacade> viewprofile_SkillBadgewithLink;

}
