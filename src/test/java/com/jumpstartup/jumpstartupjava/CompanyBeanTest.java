package com.jumpstartup.jumpstartupjava;

import com.jumpstartup.Company.CompanyBean;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentMap;

import static org.junit.jupiter.api.Assertions.*;

class CompanyBeanTest {

    @Test
    void testConstructorAndGettersAndSetters() {
        // Arrange
        String entrepreneurUUID = "123";
        String company_name = "Test Company";
        String is_registered = "Yes";
        String stakeholder = "Founder";
        String company_size = "Small";
        String funding_status = "Seed";
        String equity_offered = "10%";
        String assets = "$100,000";
        String open_to_negotiations = "Yes";
        String profits_in_last_fy = "$10,000";
        String pitch = "Test pitch";
        String UUID = "1234";

        // Act

        CompanyBean companyBeanTest = new CompanyBean();

        CompanyBean companyBean = new CompanyBean(entrepreneurUUID, company_name, is_registered, stakeholder, company_size, funding_status,
                equity_offered, assets, open_to_negotiations, profits_in_last_fy, pitch);

        // Assert
        assertEquals(entrepreneurUUID, companyBean.getEntrepreneurUUID());
        assertEquals(company_name, companyBean.getCompany_name());
        assertEquals(is_registered, companyBean.getIs_registered());
        assertEquals(stakeholder, companyBean.getStakeholder());
        assertEquals(company_size, companyBean.getCompany_size());
        assertEquals(funding_status, companyBean.getFunding_status());
        assertEquals(equity_offered, companyBean.getEquity_offered());
        assertEquals(assets, companyBean.getAssets());
        assertEquals(open_to_negotiations, companyBean.getOpen_to_negotiations());
        assertEquals(profits_in_last_fy, companyBean.getProfits_in_last_fy());
        assertEquals(pitch, companyBean.getPitch());

        // Act
        String newCompanyName = "New Test Company";
        companyBean.setCompany_name(newCompanyName);
        String newIsRegistered = "No";
        companyBean.setIs_registered(newIsRegistered);
        String newStakeholder = "Co-Founder";
        companyBean.setStakeholder(newStakeholder);
        String newCompanySize = "Medium";
        companyBean.setCompany_size(newCompanySize);
        String newFundingStatus = "Series A";
        companyBean.setFunding_status(newFundingStatus);
        String newEquityOffered = "20%";
        companyBean.setEquity_offered(newEquityOffered);
        String newAssets = "$200,000";
        companyBean.setAssets(newAssets);
        String newOpenToNegotiations = "No";
        companyBean.setOpen_to_negotiations(newOpenToNegotiations);
        String newProfitsInLastFY = "$20,000";
        companyBean.setProfits_in_last_fy(newProfitsInLastFY);
        String newPitch = "New Test pitch";
        companyBean.setPitch(newPitch);
        companyBean.setEntrepreneurUUID(UUID);

        // Assert
        assertEquals(newCompanyName, companyBean.getCompany_name());
        assertEquals(newIsRegistered, companyBean.getIs_registered());
        assertEquals(newStakeholder, companyBean.getStakeholder());
        assertEquals(newCompanySize, companyBean.getCompany_size());
        assertEquals(newFundingStatus, companyBean.getFunding_status());
        assertEquals(newEquityOffered, companyBean.getEquity_offered());
        assertEquals(newAssets, companyBean.getAssets());
        assertEquals(newOpenToNegotiations, companyBean.getOpen_to_negotiations());
        assertEquals(newProfitsInLastFY, companyBean.getProfits_in_last_fy());
        assertEquals(newPitch, companyBean.getPitch());
        assertEquals(UUID,companyBean.getEntrepreneurUUID());
    }
}
