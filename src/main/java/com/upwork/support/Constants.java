package com.upwork.support;

public enum Constants {



//    TITLE("'s Title"),
//    COUNTRY("'s CountryName"),
//    RATE("'s Rate"),
//    JOBPROGRESS("'s JobProgress"),
//    SKILLS("'s Skills"),
//    SUMMARY("'s Summary");


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
