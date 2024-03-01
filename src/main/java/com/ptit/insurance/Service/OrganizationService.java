package com.ptit.insurance.Service;

import com.ptit.insurance.Reponsitory.OrganizationReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationReponsitory organizationReponsitory;
}
