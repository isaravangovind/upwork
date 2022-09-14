package com.upwork.pages;

import com.upwork.basepage.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

public class IndividualProfilePage extends BasePage {

    @FindBy(css = "div>section.up-card-section div>h1")
    public WebElementFacade viewprofile_freelancerName;

    @FindBy(css = "div>section.up-card-section span[itemprop='country-name']")
    public WebElementFacade viewprofile_countryName;

    @FindBy(css = "div>section.up-card-section div.d-flex h2")
    public WebElementFacade viewprofile_Title;

    @FindBy(css = "div>section.up-card-section div.d-flex div[class*='job-success'] h3[role='presentation']")
    public WebElementFacade viewprofile_Job_successRate;

    @FindBy(css = "div>section.up-card-section div.d-flex h3[role='presentation']>span")
    public WebElementFacade viewprofile_RatePerHour;

    @FindBy(xpath = "(//section[contains(@class,'up-card-section')]//span[contains(@class,'break')])[1]")
    public WebElementFacade viewprofile_Summary;

    @FindBy(xpath = "(//section[contains(@class,'up-card-section')]//div[@class='skills'])[1]//li//span[@class='up-skill-badge']")
    public List<WebElementFacade> viewprofile_SkillBadgeWithoutLink;

    @FindBy(xpath = "(//section[contains(@class,'up-card-section')]//div[@class='skills'])[1]//li//a[contains(@class,'up-skill-badge')]")
    public List<WebElementFacade> viewprofile_SkillBadgewithLink;


}
