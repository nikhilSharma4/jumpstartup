package com.jumpstartup.Jobs;

import java.util.UUID;

public class JobsBean {

    private String jobUuid;
    private String entrepreneurUuid;
    private String description;
    private String isActive;
    private String numberOfOpenings;
    private String skills;
    private String payEstimate;
    private String type;
    private String postingDate;

    public JobsBean() {
        this.jobUuid = UUID.randomUUID().toString();
    }
    
    public JobsBean ( String entrepreneurUuid, String description, String isActive, String numberOfOpenings, 
                      String skills, String payEstimate, String type, String postingDate ) {

        this.jobUuid = UUID.randomUUID().toString();
        this.entrepreneurUuid = entrepreneurUuid;
        this.description = description;
        this.isActive = isActive;
        this.numberOfOpenings = numberOfOpenings;
        this.skills = skills;
        this.payEstimate = payEstimate;
        this.type = type;
        this.postingDate = postingDate;

    }

    public String getJobUuid() {
        return jobUuid;
    }

    public void setJobUuid(String jobUuid) {
        this.jobUuid = jobUuid;
    }

    public String getEntrepreneurUuid() {
        return entrepreneurUuid;
    }

    public void setEntrepreneurUuid(String entrepreneurUuid) {
        this.entrepreneurUuid = entrepreneurUuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getNumberOfOpenings() {
        return numberOfOpenings;
    }

    public void setNumberOfOpenings(String numberOfOpenings) {
        this.numberOfOpenings = numberOfOpenings;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPayEstimate() {
        return payEstimate;
    }

    public void setPayEstimate(String payEstimate) {
        this.payEstimate = payEstimate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }
}
