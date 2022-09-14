package com.upwork.stepdef;

import com.upwork.steps.Search;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.util.Locale;

public class SearchSteps {

    @Steps
    Search search;

    @Then("Get all FreeLancer Info")
    public void get_all_free_lancer_info() {
        search.getAllFreeLancerInfo();
        System.out.println("All Freelancers details are stored in Map");
    }


    @Then("Assert at least one attribute contains {string}")
    public void assert_at_least_one_attribute_contains(String skillSet) {

        search.verifyAtLeastOneAttributeContainKeywork(skillSet);
        System.out.println("Verify atleast one of the attributes contains keyword " + skillSet.toUpperCase(Locale.ROOT));


    }


    @When("Click on random freelancer's title")
    public void click_on_random_freelancer_s_title() {
        search.clickonrandomfreelancerprofile();
        System.out.println("Click on Random Freelancer ");
    }
}
