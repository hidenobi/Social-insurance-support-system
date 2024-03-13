package com.ptit.insurance.Service;

import com.ptit.insurance.Model.Organization;
import com.ptit.insurance.Reponsitory.OrganizationReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationReponsitory organizationReponsitory;

    public boolean Save(Organization organization) {
        try {
            organizationReponsitory.save(organization);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public Organization findByInsuranceCode(String insuranceCode) {
        try {
            Organization organization = organizationReponsitory.findByInsuranceCode(insuranceCode);
            return organization;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void Delete(Organization organization) {
        if (organizationReponsitory.findByInsuranceCode(organization.getInsuranceCode()) != null) {
            organizationReponsitory.delete(organization);
        }
    }

}
