package com.upwork.steps;

import com.upwork.pages.HomePage;
import com.upwork.support.FreelancerList;
import com.upwork.support.AllFreelancersInfo;
import com.upwork.support.Constants;
import com.upwork.support.SingleFreeLancerProfile;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Step;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Home {

    /**
     * This Class contains the steps and logic of your test cases
     * <p>
     * Page object from HOMWPAGE class can be called from this class and do anu action
     * <p>
     * Any Declaration to store data / retrieve data can be done here
     *
     * @ManagedPages Annotation will inti the pages
     * If Any other pages has to to initiated then add it here
     * @Step is used to indicate this step in report
     * <p>
     * Here "Open" is the serenity inbuilt method is used to pass the URL in the browser
     * "Open" similar to driver.get(url)
     * the url and driver is from serenity.properties
     * <p>
     * To try with different browser
     * Change - webdriver.driver=chrome to webdriver.driver=firefox
     * Testers can ignore downloading chromedriver.exe / geckodriver.exe
     * Serenity will take care downloading driver automatically
     */


    @ManagedPages
    HomePage homepage;

    private Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass());





    @SneakyThrows
    @Step("Launch the browser")
    public void launchApp() {
        homepage.open();
        logger.info("Application is Launched in the Browser");
        homepage.checkCaptcha();
    }

    @Step("Select {0} from the Catalog DropDown ")
    public void selectCategory(String catalog) {
        homepage.click(homepage.catalogDropDown);
        homepage.selectCatalog(catalog);
        logger.info(catalog.toUpperCase(Locale.ROOT) + "is selected from dropdown");
    }

    @SneakyThrows
    @Step("Type {0} Skill Set in Search Text Box and Select the respective Keyword from the Suggested DropDown")
    public void searchForSkillset(String skillKeyword) {

        if (homepage.getAttributeText(homepage.searchTextBox, "placeholder").equals("Search Talent")) {
            homepage.enterByChar(homepage.searchTextBox, skillKeyword);
            homepage.searchTextBox.sendKeys(Keys.ENTER);
            logger.info("Search for professionals with SkillSet " + skillKeyword);
        } else {
            homepage.enterByChar(homepage.searchTextBox, skillKeyword);
            Thread.sleep(5000);
            if (homepage.searchMenuMatch.size() != 0) {
                homepage.searchMenuMatch.get(0).click();
                logger.info("Search for professionals with SkillSet " + skillKeyword.toUpperCase(Locale.ROOT));
            } else {
                logger.info("No SkillSet suggestion dropdown is displayed so pressing enter");
                homepage.searchTextBox.sendKeys(Keys.ENTER);
            }

            Thread.sleep(5000);
        }

        homepage.checkCaptcha();
    }
}

