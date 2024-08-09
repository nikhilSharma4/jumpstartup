package com.jumpstartup.Company;

public class CompanyBean {

    private String entrepreneurUUID;
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

//    company_name;
//    is_registered;
//    stakeholder;
//    company_size;
//    funding_status;
//    equity_offered;
//    assets;
//    open_to_negotiations;
//    profits_in_last_fy;
//    pitch;

    public CompanyBean() {}

    public CompanyBean(String entrepreneurUUID, String company_name, String is_registered, String stakeholder, String company_size,
                       String funding_status, String equity_offered, String assets, String open_to_negotiations,
                       String profits_in_last_fy, String pitch) {

        this.entrepreneurUUID = entrepreneurUUID;
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

    public String getEntrepreneurUUID() {
        return entrepreneurUUID;
    }

    public void setEntrepreneurUUID(String entrepreneurUUID) {
        this.entrepreneurUUID = entrepreneurUUID;
    }
}
