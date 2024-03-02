package com.ptit.insurance.Service;

import com.ptit.insurance.Reponsitory.InsuranceAgencyReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceAgencyService {
    @Autowired
    private InsuranceAgencyReponsitory insuranceAgencyReponsitory;
}
