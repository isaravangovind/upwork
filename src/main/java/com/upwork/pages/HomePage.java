package com.upwork.pages;

import com.upwork.basepage.BasePage;
import lombok.SneakyThrows;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {

    /**
     * Home Page locators are defined here
     **/

    @FindBy(id = "px-captcha")
    public List<WebElement> recap;

    @FindBy(css = "div#nav-main button[data-cy='search-switch-btn']")
    public WebElementFacade catalogDropDown;

    @FindBy(xpath = "//div[@id='nav-main']//span[contains(@class,'query')]")
    public List<WebElementFacade> catalogQuery;


    @FindBy(css = "div#nav-main input[type='search']")
    public WebElementFacade searchTextBox;

    @FindBy(css = "a span.is-match")
    public List<WebElementFacade> searchMenuMatch;

    @SneakyThrows
    public void checkCaptcha() {
        if (recap.size() != 0) {
            Thread.sleep(60000);
        }
    }
}
