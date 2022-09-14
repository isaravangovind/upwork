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


    /**
     * Getter and Setter Method or POJO classes initialized below
     * Easy to get access to data
     * allFreelancersInfo - Master class contains all freelancer info from search page
     * freelancerList - List of freelancers Names
     *
     */

    public AllFreelancersInfo allFreelancersInfo = new AllFreelancersInfo();
    public FreelancerList freelancerList = new FreelancerList();

    /**
     * freelancerMap - Master Map contains all freelancer info from search page
     */

    LinkedHashMap<String, LinkedHashMap<String, String>> freelancerMap = new LinkedHashMap<>();
    List<String> freeLancer = new ArrayList<>();

    SoftAssertions soft = new SoftAssertions();


    @SneakyThrows
    @Step("Launch the browser")
    public void launchApp() {
        homepage.open();
        logger.info("Application is Launched in the Browser");
        homepage.checkCaptcha();
    }

    @Step("Select {0} from the Catalog DropDown ")
    public void selectCategory(String catalog) {
        homepage.click(homepage.catalogDropDown)
                .selectCatalog(catalog);
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

    public void getAllFreeLancerInfo() {

        for (int i = 0; i < homepage.freelancerCard.size(); i++) {

            /**
             *
             * SKILLS - of the freelancer is declared in the SET
             * In This set all skills from the UI will be stored
             *
             * LinkedHashMap<String, String> freelancerDetails
             * This Map will have details of individual freelancer
             * Keys as Title, Country, Job Progress, Rate per hour Skills, Summary
             * Respective values will be retrived from UI and stored here
             */
            Set<String> skills = new HashSet<>();

            LinkedHashMap<String, String> freelancerDetails = new LinkedHashMap<>();

            /**
             *
             * Getting Freelancer Name, Title, Rate, JobProgress
             * SkillSet and Summary from UI
             *
             */
            String freelancerName = homepage.freelancerName.get(i).getText().trim();
            String freelancerTitle = homepage.freelancerTitle.get(i).getText().trim();
            String freelancerCountryName = homepage.freelancerCountryName.get(i).getText().trim();
            String freelancerRate = homepage.freelancerRateinDollars.get(i).getText().trim()
                    + homepage.freelancerRatePerhour.get(i).getText().trim();
            String freelancerJobprogress = homepage.getJobProgress(freelancerName);

            List<WebElement> getFreelancerSkills = homepage.getSkillsWebElement(freelancerName);

            for (WebElement skill : getFreelancerSkills) {
                skills.add(skill.getText().trim());
            }

            WebElement getFreelancerOverview = homepage.getOverviewWebelement(freelancerName);
            String summary = getFreelancerOverview.getText();

            freelancerDetails.put(Constants.TITLE.getProfileDef(), freelancerTitle);
            freelancerDetails.put(Constants.COUNTRY.getProfileDef(), freelancerCountryName);
            freelancerDetails.put(Constants.RATE.getProfileDef(), freelancerRate);
            freelancerDetails.put(Constants.JOBPROGRESS.getProfileDef(), freelancerJobprogress);
            freelancerDetails.put(Constants.SKILLS.getProfileDef(), String.valueOf(skills));
            freelancerDetails.put(Constants.SUMMARY.getProfileDef(), summary);

            String content = freelancerDetails.entrySet()
                    .stream()
                    .map(e -> e.getKey() + "=" + e.getValue() + "")
                    .collect(Collectors.joining(", \r\n"));

            Serenity.recordReportData().withTitle(freelancerName).andContents(content);

            /**
             *
             * freelancerMap - is the Master Map
             * Where All Freelancers Details in 1st page are stored
             *
             *
             * Key = Freelancer Name
             * Value = freelancerDetails [Title, Rate, JobProgress, SkillSet and Summary]
             */

            freelancerMap.put(freelancerName, freelancerDetails);
        }

        allFreelancersInfo.setFreelancerMap(freelancerMap);
        String content = freelancerMap.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + e.getValue() + "")
                .collect(Collectors.joining(", \r\n"));

        logger.info("Get all freelancer info on search page and stores in freelancerMap" + content);
        Serenity.recordReportData().withTitle("All Freelancer Details in the 1st page").andContents(content);
    }

    @Step("Validate At least on Attribute (Title / Summary / Skills) contains the keyword {0}")
    public void verifyAtLeastOneAttributeContainKeywork(String skillSet) {

        /**
         *  To verify the keyword to present in any of the the attributes
         *
         *  1. Need to get each freelancer's profile Map containing the Attribute
         *          We already set all freelancer detail Map in AllFreelancerInfo Class
         *          User getFreelancerMap to retrieve all information
         *
         *  2. Take each freelancer's attribute and check whether the keyword is present
         *
         *
         */

        /**
         *
         * listofFreeLancers = Taking all list of freelancer
         *
         */
        List<String> listofFreeLancers = new ArrayList<>(allFreelancersInfo.getFreelancerMap().keySet());
        freelancerList.setFreelancerList(listofFreeLancers);

        HashMap<String, String> freelancerProfile;

        for (String freelancer : listofFreeLancers) {

            freelancerProfile = allFreelancersInfo.getFreelancerMap().get(freelancer);
            keywordPresenceCheck(skillSet, freelancer, freelancerProfile);


        }
    }

    public void keywordPresenceCheck(String skillSet, String freelancer, HashMap<String, String> freelancerProfile) {

        boolean titleCheck = Pattern.compile(Pattern.quote(skillSet),
                Pattern.CASE_INSENSITIVE).matcher(freelancerProfile.get(Constants.TITLE.getProfileDef())).find();

        boolean skillCheck = Pattern.compile(Pattern.quote(skillSet),
                Pattern.CASE_INSENSITIVE).matcher(freelancerProfile.get(Constants.SKILLS.getProfileDef())).find();

        boolean summaryCheck = Pattern.compile(Pattern.quote(skillSet),
                Pattern.CASE_INSENSITIVE).matcher(freelancerProfile.get(Constants.SUMMARY.getProfileDef())).find();

        List<String> presenceOfKeyword = new ArrayList<>();
        List<Boolean> assertCheck = new ArrayList<>();
        assertCheck.add(titleCheck);
        assertCheck.add(skillCheck);
        assertCheck.add(summaryCheck);

        if (titleCheck) {

            //   System.out.println("The title of " + freelancer + " contains the keyword " + skillSet.toUpperCase(Locale.ROOT));
            presenceOfKeyword.add("The title of " + freelancer + " contains the keyword " + skillSet.toUpperCase(Locale.ROOT));


        } else {

//                System.out.println("The title of " + freelancer + " does not contain " + skillSet.toUpperCase(Locale.ROOT));
            presenceOfKeyword.add("The title of " + freelancer + " does not contain " + skillSet.toUpperCase(Locale.ROOT));

        }

        if (skillCheck) {

//                System.out.println(skillSet.toUpperCase(Locale.ROOT) + " is present in one or more skill set of " + freelancer);
            presenceOfKeyword.add(skillSet.toUpperCase(Locale.ROOT) + " is present in one or more skill set of " + freelancer);

        } else {

//                System.out.println(skillSet.toUpperCase(Locale.ROOT) + " is not present in any skill set of " + freelancer);
            presenceOfKeyword.add(skillSet.toUpperCase(Locale.ROOT) + " is not present in any skill set of " + freelancer);

        }

        if (summaryCheck) {

//                System.out.println(skillSet.toUpperCase(Locale.ROOT) + " is present summary of " + freelancer);
            presenceOfKeyword.add(skillSet.toUpperCase(Locale.ROOT) + " is present summary of " + freelancer);

        } else {

//                System.out.println(skillSet.toUpperCase(Locale.ROOT) + " is not present summary of " + freelancer);
            presenceOfKeyword.add(skillSet.toUpperCase(Locale.ROOT) + " is not present summary of " + freelancer);

        }

        String result = presenceOfKeyword.toString()
                .replace(", ", "\r\n")
                .replace("[", "")
                .replace("]", "");

        System.out.println(freelancer + "\r\n" + result);
        logger.info("Checking the presence of " + skillSet.toUpperCase(Locale.ROOT) + "Keyword in each attributes" +result);

        Serenity.recordReportData().withTitle(freelancer)
                .andContents(result);

        soft.assertThat(assertCheck.contains(true)).as("No Attribute has the keyword").isEqualTo(true);
        logger.info("Result Status => \r\n " + assertCheck.contains(true));
        soft.assertAll();

    }


    public static String randomFreelancer = "";

    @Step("click on a random freelancer")
    public void clickonrandomfreelancerprofile() {

        Random rand = new Random();
        freeLancer = freelancerList.getFreelancerList();
        randomFreelancer = freeLancer.get(rand.nextInt(freeLancer.size()));
        logger.info("Random freelancer is selected");

        Serenity.setSessionVariable("FreeLancer_Profile_From_1st_Page").to(allFreelancersInfo.getFreelancerMap().get(randomFreelancer));

        homepage.clickOnRandomFreelancer(randomFreelancer);
        logger.info("Random freelancer is clicked");
        Serenity.recordReportData().withTitle(randomFreelancer.toUpperCase(Locale.ROOT) + "Profile is clicked")
                .andContents("The selected Random FreeLancer is  " + randomFreelancer.toUpperCase(Locale.ROOT));


    }
}

