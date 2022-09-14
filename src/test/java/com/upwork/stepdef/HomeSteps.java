package com.upwork.stepdef;

import com.upwork.basetest.BaseTest;
import com.upwork.steps.Home;
import com.upwork.support.AllFreelancersInfo;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.apache.log4j.Logger;
import org.assertj.core.api.AbstractSoftAssertions;

import java.lang.invoke.MethodHandles;
import java.util.Locale;


public class HomeSteps extends BaseTest {

    private Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * This Class is the Step definition contains Java method with an expression that links it to one or more Gherkin steps
     * When Cucumber executes a Gherkin step in a scenario, it will look for a matching step definition to execute.
     * <p>
     * In This Class,
     *
     * @Before used here to demonstrate another method to delete cookies
     * If any Test Data Prep / other Initialization can be done in Before Method
     * <p>
     * <p>
     * The Step definition are implemented only for Home Page
     * @Steps Annotation - Used by Serenity
     * Where the tester will write the actual implementation each steps defined here
     **/

    @Steps
    Home home;


    @Given("Launch upwork application")
    public void launch_upwork_application() {
        home.launchApp();
        logger.info("Upwork Application is Launched in Browser");

    }

    @When("Focus onto {string}")
    public void focus_onto(String category) {

        home.selectCategory(category);
        logger.info("Catalog is selected as " + category.toUpperCase(Locale.ROOT) );
    }

    @When("Enter {string} into the search and select from drop down")
    public void enter_into_the_search_and_select_from_drop_down(String skillKeyword) {
        home.searchForSkillset(skillKeyword);

        logger.info("Searching for professional containing keyword : " + skillKeyword.toUpperCase(Locale.ROOT));
    }


}
