package com.upwork.support;

import java.util.ArrayList;
import java.util.List;

public class FreelancerList {

    public List<String> getFreelancerList() {
        return freelancerList;
    }

    public void setFreelancerList(List<String> freelancerList) {
        this.freelancerList = freelancerList;
    }

    List<String> freelancerList = new ArrayList<>();
}
