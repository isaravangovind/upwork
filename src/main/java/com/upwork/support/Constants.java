package com.upwork.support;

public enum Constants {

    /**
     * Any Constants will be user across the application can be
     * declared here
     */


    TITLE("Title"),
    COUNTRY("CountryName"),
    RATE("Rate"),
    JOBPROGRESS("JobProgress"),
    SKILLS("Skills"),
    SUMMARY("Summary");

    private String profiledefinations;

    Constants(String profileDef) {
        this.profiledefinations = profileDef;
    }

    public String getProfileDef() {
        return profiledefinations;
    }
}
