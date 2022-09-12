package com.upwork.stepdef;

import com.upwork.basetest.BaseTest;
import com.upwork.steps.Home;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.webdriver.WebDriverFacade;


public class HomeSteps extends BaseTest {

    /**
     *
     * This Class is the Step definition contains Java method with an expression that links it to one or more Gherkin steps
     * When Cucumber executes a Gherkin step in a scenario, it will look for a matching step definition to execute.
     *
     * In This Class,
     * @Before used here to demonstrate another method to delete cookies
     * If any Test Data Prep / other Initialization can be done in Before Method
     *
     *
     * The Step definition are implemented only for Home Page
     *
     * @Steps Annotation - Used by Serenity
     * Where the tester will write the actual implementation each steps defined here

     **/






    @Steps
    Home home;


    @Before
    public void deleteCookies(){

    }

    @Given("Launch upwork application")
    public void launch_upwork_application() {
            home.launchApp();
    }

    @When("Focus onto {string}")
    public void focus_onto(String category) {
        home.selectCategory(category);
    }

    @When("Enter {string} into the search and select from drop down")
    public void enter_into_the_search_and_select_from_drop_down(String skillKeyword) {
        home.searchForSkillset(skillKeyword);
    }

    @Then("Get all FreeLancer Info")
    public void get_all_free_lancer_info() {

    }


    @Then("Assert at least one attribute contains {string}")
    public void assert_at_least_one_attribute_contains(String string) {


    }


    @When("Click on random freelancer's title")
    public void click_on_random_freelancer_s_title() {


    }
    @When("Get into that freelancer's profile")
    public void get_into_that_freelancer_s_profile() {


    }
    @Then("Assert Selected Freelancer details Matches with Freelancer Details in First Page")
    public void assert_selected_freelancer_details_matches_with_freelancer_details_in_first_page() {


    }



}
