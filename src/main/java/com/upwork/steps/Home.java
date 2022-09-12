package com.upwork.steps;

import com.upwork.pages.HomePage;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Step;

public class Home {

    // @ManagedPages Annotation will inti the pages
    // If Any other pages has to to initiated then add it here

    @ManagedPages
    HomePage homepage;


    // @Step is used to indicate this step in report
    // Here Open is the method is used to pass the URL in the browser
    // YThis step is similar to driver.get(url)
    // the url is from serenity.properties

    @Step("Launch the browser")
    public void launchApp() {
        homepage.open();
    }

    @Step("Select Category from the DropDown {category}")
    public void selectCategory(String category) {
    }

    @Step("Search for the Skill set with keyword {}")
    public void searchForSkillset(String skillKeyword) {
    }
}
