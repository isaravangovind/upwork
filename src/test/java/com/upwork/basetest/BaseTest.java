package com.upwork.basetest;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.webdriver.WebDriverFacade;

import static net.thucydides.core.annotations.ClearCookiesPolicy.BeforeEachTest;

public class BaseTest {

    // Driver initialized here to delete cookies before the test starts

    @Managed (uniqueSession=true, clearCookies=BeforeEachTest)
    public WebDriverFacade driver;



}
