package com.upwork.steps;

import com.upwork.pages.HomePage;
import com.upwork.support.FreelancerList;
import com.upwork.support.FreelancersDetails;
import com.upwork.support.Constants;
import com.upwork.support.SingleFreeLancerProfile;
import io.cucumber.java.ro.Si;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.regex.Pattern;

public class Home {

    /**
     *
     * This Class contains the steps and logic of your test cases
     *
     * Page object from HOMWPAGE class can be called from this class and do anu action
     *
     * Any Declaration to store data / retrieve data can be done here
     *
     *
     *
     * @ManagedPages Annotation will inti the pages
     * If Any other pages has to to initiated then add it here
     *
     *
     * @Step is used to indicate this step in report
     *
     * Here "Open" is the serenity inbuilt method is used to pass the URL in the browser
     * "Open" similar to driver.get(url)
     * the url and driver is from serenity.properties
     *
     * To try with different browser
     * Change - webdriver.driver=chrome to webdriver.driver=firefox
     * Testers can ignore downloading chromedriver.exe / geckodriver.exe
     * Serenity will take care downloading driver automatically
     *
     *
     */


    @ManagedPages
    HomePage homepage;

    FreelancersDetails freelancersDetails = new FreelancersDetails();
    FreelancerList freelancerList = new FreelancerList();
    SingleFreeLancerProfile singleFreeLancerProfile = new SingleFreeLancerProfile();

    LinkedHashMap<String, LinkedHashMap<String, String>> freelancerMap = new LinkedHashMap<>();
    List<String> freeLancer = new ArrayList<>();


    @SneakyThrows
    @Step("Launch the browser")
    public void launchApp() {
        homepage.open();
        Thread.sleep(5000);
        System.out.println("Application is Launched in the Browser");
//        homepage.checkCaptcha();
    }

    @Step("Select {0} from the Catalog DropDown ")
    public void selectCategory(String catalog) {
//        homepage.click(homepage.catalogDropDown)
//                .selectCatalog(catalog);
        System.out.println("Catalog " + catalog.toUpperCase(Locale.ROOT) + " selected");
    }

    @SneakyThrows
    @Step("Type {0} Skill Set in Search Text Box and Select the respective Keyword from the Suggested DropDown")
    public void searchForSkillset(String skillKeyword) {

        if (homepage.getAttributeText(homepage.searchTextBox, "placeholder").equals("Search Talent")) {
            homepage.enterByChar(homepage.searchTextBox, skillKeyword);
            homepage.searchTextBox.sendKeys(Keys.ENTER);
            System.out.println("No SkillSet suggestion dropdown is displayed");
        } else {
            homepage.enterByChar(homepage.searchTextBox, skillKeyword);
            Thread.sleep(5000);
            if(homepage.searchMenuMatch.size() !=0) {
                System.out.println(homepage.searchMenuMatch.get(0).getText());
                homepage.searchMenuMatch.get(0).click();
                System.out.println("SkillSet suggestion dropdown is displayed and selected " + skillKeyword.toUpperCase(Locale.ROOT));
            } else {
                System.out.println("No SkillSet suggestion dropdown is displayed");
                homepage.searchTextBox.sendKeys(Keys.ENTER);
            }

            Thread.sleep(5000);
        }

        homepage.checkCaptcha();
    }

    public void setAllFreeLancerInfo() {

        for (int i = 0; i < homepage.freelancerCard.size(); i++) {
            Set<String> skills = new HashSet<>();

            LinkedHashMap<String, String> freelancerDetails = new LinkedHashMap<>();

            // Get Name of the freelancer
            String freelancerName = homepage.freelancerName.get(i).getText().trim();
            String freelancerTitle = homepage.freelancerTitle.get(i).getText().trim();
            String freelancerRate = homepage.freelancerRateinDollars.get(i).getText().trim()
                    + homepage.freelancerRatePerhour.get(i).getText().trim();
            String freelancerJobprogress = homepage.getJobProgress(freelancerName);
            String freelancerCountryName = homepage.freelancerCountryName.get(i).getText().trim();

            System.out.println(freelancerName);


            WebElement getFreelancerOverview = homepage.getOverviewWebelement(freelancerName);

            // Get Summary of freelancer's
            String summary = getFreelancerOverview.getText();

            // Get the freelancer's skill set in a List
            List<WebElement> getFreelancerSkills = homepage.getSkillsWebElement(freelancerName);

            for (WebElement skill : getFreelancerSkills) {
                skills.add(skill.getText().trim());
            }

            freelancerDetails.put(freelancerName.concat(Constants.TITLE.getProfileDef()), freelancerTitle);
            freelancerDetails.put(freelancerName.concat(Constants.COUNTRY.getProfileDef()), freelancerCountryName);
            freelancerDetails.put(freelancerName.concat(Constants.RATE.getProfileDef()), freelancerRate);
            freelancerDetails.put(freelancerName.concat(Constants.JOBPROGRESS.getProfileDef()), freelancerJobprogress);
            freelancerDetails.put(freelancerName.concat(Constants.SKILLS.getProfileDef()), String.valueOf(skills));
            freelancerDetails.put(freelancerName.concat(Constants.SUMMARY.getProfileDef()), summary);

            freelancerMap.put(freelancerName, freelancerDetails);
        }
        System.out.println(freelancerMap);

        freelancersDetails.setFreelancerMap(freelancerMap);

    }

    @Step
    public void verifyAtLeastOneAttributeContainKeywork(String skillSet) {


        List<String> listofFreeLancers = new ArrayList<>(freelancersDetails.getFreelancerMap().keySet());
        freelancerList.setFreelancerList(listofFreeLancers);

        HashMap<String, String> freelancerProfile;
        System.out.println(freelancerList);

        for (String freelancer : listofFreeLancers) {
            System.out.println(freelancer);


            freelancerProfile = freelancersDetails.getFreelancerMap().get(freelancer);

            boolean titleCheck = Pattern.compile(Pattern.quote(skillSet),
                    Pattern.CASE_INSENSITIVE).matcher(freelancerProfile.get(freelancer + Constants.TITLE.getProfileDef())).find();

            boolean skillCheck = Pattern.compile(Pattern.quote(skillSet),
                    Pattern.CASE_INSENSITIVE).matcher(freelancerProfile.get(freelancer + Constants.SKILLS.getProfileDef())).find();

            boolean summaryCheck = Pattern.compile(Pattern.quote(skillSet),
                    Pattern.CASE_INSENSITIVE).matcher(freelancerProfile.get(freelancer+ Constants.SUMMARY.getProfileDef())).find();

            if (titleCheck) {
                System.out.println("The title of " + freelancer + " contains the keyword " + skillSet.toUpperCase(Locale.ROOT));
                Serenity.recordReportData().withTitle(freelancer.concat(Constants.TITLE.getProfileDef()))
                        .andContents("The title of " + freelancer + " contains the keyword "
                        + skillSet.toUpperCase(Locale.ROOT));
            } else {

                System.out.println("The title of " + freelancer + " does not contain " + skillSet.toUpperCase(Locale.ROOT));
                Serenity.recordReportData().withTitle(freelancer.concat(Constants.TITLE.getProfileDef()))
                        .andContents("The title of " + freelancer + " does not contain "
                        + skillSet.toUpperCase(Locale.ROOT));
            }

            if (skillCheck) {

                System.out.println(skillSet.toUpperCase(Locale.ROOT) + " is present in one or more skill set of " + freelancer);
                Serenity.recordReportData().withTitle(freelancer.concat(Constants.SKILLS.getProfileDef()))
                        .andContents(skillSet.toUpperCase(Locale.ROOT) + " is present in one or more skill set of " + freelancer);
            } else {

                System.out.println(skillSet.toUpperCase(Locale.ROOT) + " is not present in any skill set of " + freelancer);
                Serenity.recordReportData().withTitle(freelancer.concat(Constants.SKILLS.getProfileDef()))
                        .andContents(skillSet.toUpperCase(Locale.ROOT) + " is not present in any skill set of " + freelancer);
            }

            if (summaryCheck) {

                System.out.println(skillSet.toUpperCase(Locale.ROOT) + " is present summary of " + freelancer);
                Serenity.recordReportData().withTitle(freelancer.concat(Constants.SUMMARY.getProfileDef()))
                        .andContents(skillSet.toUpperCase(Locale.ROOT) + " is present summary of " + freelancer);
            } else {

                System.out.println(skillSet.toUpperCase(Locale.ROOT) + " is present summary of " + freelancer);
                Serenity.recordReportData().withTitle(freelancer.concat(Constants.SUMMARY.getProfileDef()))
                        .andContents(skillSet.toUpperCase(Locale.ROOT) + " is present summary of " + freelancer);
            }

        }



    }

    @Step("click on a random freelancer")
    public void clickonrandomfreelancerprofile() {

        Random rand = new Random();
        freeLancer = freelancerList.getFreelancerList();
        String randomFreelancer = freeLancer.get(rand.nextInt(freeLancer.size()));
        System.out.println(randomFreelancer);
        singleFreeLancerProfile.setFreelancer(randomFreelancer);
        singleFreeLancerProfile.setFreelancerProfile(freelancersDetails.getFreelancerMap().get(randomFreelancer));
        homepage.clickOnRandomFreelancer(randomFreelancer);
        Serenity.recordReportData().withTitle("Random FreeLancer")
                .andContents("The Random FreeLancer clicked  "+ randomFreelancer.toUpperCase(Locale.ROOT));
        
//
//        homepage.clickOnRandomFreelancer(randomElement);
//        homepage.verifyTheprofile(randomElement, getFreelancerMap.get(randomElement));
    }

    @Step("Verify that {0} profile is matching with 1st page")
    public void assertFreelancerInfoMatches() {

        HashMap<String, String> freelancerProfile = singleFreeLancerProfile.getFreelancerProfile();



    }
}

