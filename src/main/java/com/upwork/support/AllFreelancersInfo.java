package com.upwork.support;

import java.util.LinkedHashMap;

public class AllFreelancersInfo {

    LinkedHashMap<String, LinkedHashMap<String, String>> freelancerMap = new LinkedHashMap<>();

    public LinkedHashMap<String, LinkedHashMap<String, String>> getFreelancerMap() {
        return freelancerMap;
    }

    public void setFreelancerMap(LinkedHashMap<String, LinkedHashMap<String, String>> freelancerMap) {
        this.freelancerMap = freelancerMap;
    }
}
