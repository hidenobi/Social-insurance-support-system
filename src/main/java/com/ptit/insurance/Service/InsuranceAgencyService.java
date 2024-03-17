package com.ptit.insurance.Service;

import com.ptit.insurance.Model.InsuranceAgency;
import com.ptit.insurance.Reponsitory.InsuranceAgencyReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceAgencyService {
    @Autowired
    private InsuranceAgencyReponsitory insuranceAgencyReponsitory;


    public List<InsuranceAgency> getAll(){
        List<InsuranceAgency> list = insuranceAgencyReponsitory.findAll();
        return list;
    }
}
