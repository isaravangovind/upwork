package com.upwork.support;

public enum ProfileDef {



    TITLE("'s Title"),
    COUNTRY("'s CountryName"),
    RATE("'s Rate"),
    JOBPROGRESS("'s JobProgress"),
    SKILLS("'s Skills"),
    SUMMARY("'s Summary");

    private String profiledefinations;

    ProfileDef(String profileDef) {
        this.profiledefinations = profileDef;
    }

    public String getProfileDef() {
        return profiledefinations;
    }
}
