package com.upwork.support;

import java.util.HashMap;

public class SingleFreeLancerProfile {
    String freelancer = "";

    public String getFreelancer() {
        return freelancer;
    }

    public void setFreelancer(String freelancer) {
        this.freelancer = freelancer;
    }

    public HashMap<String, String> getFreelancerProfile() {
        return freelancerProfile;
    }

    public void setFreelancerProfile(HashMap<String, String> freelancerProfile) {
        this.freelancerProfile = freelancerProfile;
    }

    HashMap<String, String> freelancerProfile = new HashMap<>();
}
