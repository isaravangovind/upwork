package com.upwork.pages;

import com.upwork.basepage.BasePage;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = "div#nav-main button[data-cy='search-switch-btn']")
    public WebElementFacade catalogDropDown;

    @FindBy(xpath = "//div[@id='nav-main']//span[contains(@class,'query')]")
    public List<WebElementFacade> catalogQuery;


    @FindBy(css = "div#nav-main input[type='search']")
    public WebElementFacade searchTextBox;

    @FindBy(css = "a span.is-match")
    public List<WebElementFacade> searchMenuMatch;

}
