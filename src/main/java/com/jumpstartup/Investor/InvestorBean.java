package com.jumpstartup.Investor;

public class InvestorBean {

    private String uuid;
    private String firstName;
    private String lastName;
    private String phone_number;
    private String domain;
    private String emailId;
    private String linkedin_link;
    private String funding_available;
    private String brands_built;

    // EDUCATION
    private String institution;
    private String degree;
    private String major;
    private String year_of_completion;

    // WORK EXPERIENCE
    private String work_experience;


    public InvestorBean() {}

    public InvestorBean(String uuid, String firstName, String lastName, String phone_number,
                        String domain, String emailId, String linkedin_link, String funding_available,
                        String brands_built, String institution, String degree, String major, String year_of_completion, String work_experience) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone_number = phone_number;
        this.domain = domain;
        this.emailId = emailId;
        this.linkedin_link = linkedin_link;
        this.funding_available = funding_available;
        this.brands_built = brands_built;
        this.institution = institution;
        this.degree = degree;
        this.major = major;
        this.year_of_completion = year_of_completion;
        this.work_experience = work_experience;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFunding_available() {
        return funding_available;
    }

    public void setFunding_available(String funding_available) {
        this.funding_available = funding_available;
    }

    public String getBrands_built() {
        return brands_built;
    }

    public void setBrands_built(String brands_built) {
        this.brands_built = brands_built;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getLinkedin_link() {
        return linkedin_link;
    }

    public void setLinkedin_link(String linkedin_link) {
        this.linkedin_link = linkedin_link;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getYear_of_completion() {
        return year_of_completion;
    }

    public void setYear_of_completion(String year_of_completion) {
        this.year_of_completion = year_of_completion;
    }

    public String getWork_experience() {
        return work_experience;
    }

    public void setWork_experience(String work_experience) {
        this.work_experience = work_experience;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}


