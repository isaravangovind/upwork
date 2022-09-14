package com.upwork.steps;

import com.upwork.pages.ProfilePage;
import com.upwork.support.Constants;
import com.upwork.support.SingleFreeLancerProfile;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

public class Profile {

    /**
     * This Class contains the steps and logic of your test cases
     * <p>
     * Page object from PROFILE class can be called from this class and do anu action
     * <p>
     * Any Declaration to store data / retrieve data can be done here
     *
     * @ManagedPages Annotation will inti the pages
     * If Any other pages has to to initiated then add it here
     * @Step is used to indicate this step in report
     * <p>
     *
     */

    private Logger logger = Logger.getLogger(MethodHandles.lookup().lookupClass());

    SoftAssertions soft = new SoftAssertions();

    @ManagedPages
    ProfilePage profilepage;

    SingleFreeLancerProfile singleProfile = new SingleFreeLancerProfile();


    @SneakyThrows
    @Step("Verify that Random profile clicked is matching with 1st page")
    public void assertFreelancerInfoMatches(String randomFreelancer) {

        logger.info("Random Freelancer selected by code is " + randomFreelancer);

        HashMap<String, String> profilepageContents = new HashMap<>();
//
//        Thread.sleep(10000);
        HashMap<String, String> freelancerProfile = Serenity.sessionVariableCalled("FreeLancer_Profile_From_1st_Page");
        logger.info("Freelancer details extracted from 1st page will be compared against this individual profile page of " + randomFreelancer);
        logger.info(freelancerProfile.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + e.getValue() + "")
                .collect(Collectors.joining(", \r\n")));


        /**
         *
         * Soft Assertion Random Freelancer Name picked by Code
         * is Matches with the Name in Profile page
         *
         */


        String profilePage_FreeLancerName = profilepage.viewprofile_freelancerName.getText();
        soft.assertThat(profilePage_FreeLancerName)
                .as("Freelancer Name in profile page Comparing Search page")
                .isEqualTo(randomFreelancer);

        logger.info("Individual Profile Page - Freelancer Name : " + profilePage_FreeLancerName + "\n"
                + "Name displayed in Search page : " + randomFreelancer);

        /**
         * Soft Assertion Random Freelancer Country picked by Code
         * is Matches with the Freelancer's Country in Profile page
         */

        String profileCountry = profilepage.viewprofile_countryName.getText();
        soft.assertThat(profileCountry)
                .as("Country check in profile page with main page")
                .isEqualTo(freelancerProfile.get(Constants.COUNTRY.getProfileDef()));

        logger.info("Individual Profile Page - Country of the Freelancer : " + profileCountry + "\n"
                + "Country displayed in Search page " + freelancerProfile.get(Constants.COUNTRY.getProfileDef()));

        /**
         * Soft Assertion Random Freelancer Success Rate picked by Code
         * is Matches with the Freelancer's Success Rate in Profile page
         */

        String successRateCheck = "";
        if (!profilepage.viewprofile_Job_successRate.isPresent()) {
            successRateCheck = "No Job Progress Rate available for " + randomFreelancer;
            logger.info("Individual Profile Page - does not contains Job Progress Rate for " + successRateCheck + "\n"
                    + "Job Progress status in Search Page :  " + freelancerProfile.get(Constants.JOBPROGRESS.getProfileDef()));
        } else {
            successRateCheck = profilepage.viewprofile_Job_successRate.getText();
            logger.info("Individual Profile Page - contains Job Progress Rate for " + successRateCheck + "\n"
                    + "Job Progress status in Search Page :  " + freelancerProfile.get(Constants.JOBPROGRESS.getProfileDef()));
        }



        soft.assertThat(successRateCheck)
                .as("Success Rate check in profile page with main page")
                .isEqualTo(freelancerProfile.get(Constants.JOBPROGRESS.getProfileDef()));

        /**
         * Soft Assertion Random Freelancer Rate/Hour picked by Code
         * is Matches with the Freelancer's Rate/Hour in Profile page
         *
         */


        String rate = profilepage.viewprofile_RatePerHour.getText();
        soft.assertThat(rate).as("Rate check in profile page with main page")
                .isEqualTo(freelancerProfile.get(Constants.RATE.getProfileDef()));

        logger.info("Individual Profile Page - Rate " + rate + "\n"
                + "Rate in Search Page :  " + freelancerProfile.get(Constants.RATE.getProfileDef()));

        /**
         * Soft Assertion Random Freelancer Title picked by Code
         * is Matches with the Freelancer's Title in Profile page
         *
         */


        String title = profilepage.viewprofile_Title.getText();
        soft.assertThat(title).as("Title check in profile page with main page")
                .isEqualTo(freelancerProfile.get(Constants.TITLE.getProfileDef()));

        logger.info("Individual Profile Page - Title " + rate + "\n"
                + "Title in Search Page :  " + freelancerProfile.get(Constants.TITLE.getProfileDef()));

        profilepageContents.put(Constants.TITLE.getProfileDef(), title);

        /**
         * Soft Assertion Random Freelancer Summary picked by Code
         * is Matches with the Freelancer's Summary in Profile page
         *
         */

        String profile_Summary = profilepage.viewprofile_Summary.getText()
                .replace("\n", "")
                .replace(" ", "")
                .trim();
        String shortSummary = freelancerProfile.get(Constants.SUMMARY.getProfileDef())
                .replace(" ", "").trim();

        logger.info("Individual Profile Page - Summary " + profile_Summary + "\n"
                + "Summary in Search Page :  " + shortSummary);

        profilepageContents.put(Constants.SUMMARY.getProfileDef(), profilepage.viewprofile_Summary.getText());

        soft.assertThat(profile_Summary).as("Summary check in profile page with main page")
                .isEqualTo(shortSummary);

        /**
         * Soft Assertion Random Freelancer Skills picked by Code
         * is Matches with the Freelancer's Skills in Profile page
         *
         */

        Set<String> skills = new HashSet<>();

        for (WebElementFacade skillswithLink : profilepage.viewprofile_SkillBadgeWithoutLink) {
            skillswithLink.getText();
            skills.add(skillswithLink.getText());
        }

        for (WebElementFacade skillswithoutLink : profilepage.viewprofile_SkillBadgewithLink) {
            skillswithoutLink.getText();
            skills.add(skillswithoutLink.getText());
        }


        String[] skillAsString = freelancerProfile.get(Constants.SKILLS.getProfileDef())
                .replace("[", "")
                .replace("]", "")
                .split(", ");
        Set<String> skillSet = new HashSet<>();
        Collections.addAll(skillSet, skillAsString);

        System.out.println("skills " + skills);
        System.out.println("skillSet " + skillSet);

        soft.assertThat(skills)
                .as("SkillSet check in profile page with main page")
                .isEqualTo(skillSet);
        profilepageContents.put(Constants.SKILLS.getProfileDef(), String.valueOf(skills));

//        singleProfile.setFreelancerProfile(profilepageContents);
        Serenity.setSessionVariable("FreeLancer_Profile_From_ProfilePage").to(profilepageContents);
        logger.info("Verified that randomly picked profile Attributes matches with the content displayed in 1st page");
        soft.assertAll();

    }


}
