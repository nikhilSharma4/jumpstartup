package com.jumpstartup.Entrepreneur;

public class EntrepreneurBean {

    private String uuid;
    private String firstName;
    private String lastName;
    private String phone_number;
    private String domain;
    private String emailId;
    private String linkedin_link;

    // EDUCATION
    private String institution;
    private String degree;
    private String major;
    private String year_of_completion;

    //Work Ex
    private String work_experience;

    //Company Details
    private String company_name;
    private String is_registered;
    private String stakeholder;
    private String company_size;
    private String funding_status;
    private String equity_offered;
    private String assets;
    private String open_to_negotiations;
    private String profits_in_last_fy;
    private String pitch;

    public EntrepreneurBean() {}

    public EntrepreneurBean(String uuid, String firstName, String lastName, String phone_number, String domain, String emailId,
                            String linkedin_link, String institution, String degree, String major, String year_of_completion,
                            String work_experience, String company_name, String is_registered, String stakeholder,
                            String company_size, String funding_status, String equity_offered, String assets,
                            String open_to_negotiations, String profits_in_last_fy, String pitch) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone_number = phone_number;
        this.domain = domain;
        this.emailId = emailId;
        this.linkedin_link = linkedin_link;
        this.institution = institution;
        this.degree = degree;
        this.major = major;
        this.year_of_completion = year_of_completion;
        this.work_experience = work_experience;
        this.company_name = company_name;
        this.is_registered = is_registered;
        this.stakeholder = stakeholder;
        this.company_size = company_size;
        this.funding_status = funding_status;
        this.equity_offered = equity_offered;
        this.assets = assets;
        this.open_to_negotiations = open_to_negotiations;
        this.profits_in_last_fy = profits_in_last_fy;
        this.pitch = pitch;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getIs_registered() {
        return is_registered;
    }

    public void setIs_registered(String is_registered) {
        this.is_registered = is_registered;
    }

    public String getStakeholder() {
        return stakeholder;
    }

    public void setStakeholder(String stakeholder) {
        this.stakeholder = stakeholder;
    }

    public String getCompany_size() {
        return company_size;
    }

    public void setCompany_size(String company_size) {
        this.company_size = company_size;
    }

    public String getFunding_status() {
        return funding_status;
    }

    public void setFunding_status(String funding_status) {
        this.funding_status = funding_status;
    }

    public String getEquity_offered() {
        return equity_offered;
    }

    public void setEquity_offered(String equity_offered) {
        this.equity_offered = equity_offered;
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

    public String getOpen_to_negotiations() {
        return open_to_negotiations;
    }

    public void setOpen_to_negotiations(String open_to_negotiations) {
        this.open_to_negotiations = open_to_negotiations;
    }

    public String getProfits_in_last_fy() {
        return profits_in_last_fy;
    }

    public void setProfits_in_last_fy(String profits_in_last_fy) {
        this.profits_in_last_fy = profits_in_last_fy;
    }

    public String getPitch() {
        return pitch;
    }

    public void setPitch(String pitch) {
        this.pitch = pitch;
    }
}


