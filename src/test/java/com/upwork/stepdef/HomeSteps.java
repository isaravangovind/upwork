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
import org.assertj.core.api.AbstractSoftAssertions;

import java.util.Locale;


public class HomeSteps extends BaseTest {

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

    AllFreelancersInfo allFreelancersInfo = new AllFreelancersInfo();

    @Before
    public void deleteCookies() {

    }


    @Given("Launch upwork application")
    public void launch_upwork_application() {
        home.launchApp();
        System.out.println("Upwork Application is Launched in Browser");

    }

    @When("Focus onto {string}")
    public void focus_onto(String category) {

        home.selectCategory(category);
        System.out.println("Catalog is selected as " + category.toUpperCase(Locale.ROOT) );
    }

    @When("Enter {string} into the search and select from drop down")
    public void enter_into_the_search_and_select_from_drop_down(String skillKeyword) {
        home.searchForSkillset(skillKeyword);

        System.out.println("Searching for professional containing keyword : " + skillKeyword.toUpperCase(Locale.ROOT));
    }

   /* @Then("Get all FreeLancer Info")
    public void get_all_free_lancer_info() {
        home.getAllFreeLancerInfo();
        System.out.println("All Freelancers details are stored in Map");
    }


    @Then("Assert at least one attribute contains {string}")
    public void assert_at_least_one_attribute_contains(String skillSet) {

        home.verifyAtLeastOneAttributeContainKeywork(skillSet);
        System.out.println("Verify atleast one of the attributes contains keyword " + skillSet.toUpperCase(Locale.ROOT));


    }


    @When("Click on random freelancer's title")
    public void click_on_random_freelancer_s_title() {
        home.clickonrandomfreelancerprofile();
        System.out.println("Click on Random Freelancer ");
    }*/


}
