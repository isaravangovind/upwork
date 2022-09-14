package com.upwork.basepage;

import com.upwork.pages.HomePage;
import lombok.SneakyThrows;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage extends PageObject {

    public String getAttributeText(WebElementFacade searchTextBox, String attribute) {

        String getAttibuteText = searchTextBox.getAttribute(attribute);
        return getAttibuteText;
    }



    @SneakyThrows
    public void enterByChar(WebElementFacade element, String value) {
        char[] chars = value.toCharArray();
        for (char c : chars) {
            Thread.sleep(100);
            element.sendKeys(Character.toString(c));
        }
    }


//    public String getText(WebElementFacade element) {
//        waitForCondition().until(ExpectedConditions.stalenessOf(element));
//        return element.getText();
//    }
}
