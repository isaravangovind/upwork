package com.upwork.stepdef;

import com.upwork.basetest.BaseTest;
import com.upwork.steps.Home;
import com.upwork.steps.Profile;
import com.upwork.steps.Search;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

public class ProfileSteps extends BaseTest {


    @Steps
    Profile profile;

    @Steps
    Search search;



    @Then("Assert Selected Freelancer details Matches with Freelancer Details in First Page")
    public void assert_selected_freelancer_details_matches_with_freelancer_details_in_first_page() {
        String randomFreelancer = "Sohail I.";
        System.out.println("++++++++" + randomFreelancer);
        profile.assertFreelancerInfoMatches(randomFreelancer);

    }

    @And("Assert at least one attribute contains {string} in profilepage")
    public void assertAtLeastOneAttributeContainsInProfilepage(String skill) {
        String randomFreelancer = "Sohail I.";
        System.out.println("++++++++" + randomFreelancer);
        HashMap<String, String> freelancerProfile_ProfilePage = Serenity.sessionVariableCalled("FreeLancer_Profile_From_ProfilePage");
        search.keywordPresenceCheck(skill, randomFreelancer,  freelancerProfile_ProfilePage);
    }
}
