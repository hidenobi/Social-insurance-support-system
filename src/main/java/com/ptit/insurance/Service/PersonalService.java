package com.ptit.insurance.Service;

import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Reponsitory.PersonalReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalService {
    @Autowired
    private PersonalReponsitory personalReponsitory;

    public String Save(Personal personal){
    try {
        personalReponsitory.save(personal);
        return "success";
    }catch (Exception e){
        return e.getMessage();
        }
    }

    public Personal findByInsuranceCode(String insuranceCode){
        return personalReponsitory.findFirstByInsuranceCode(insuranceCode);
    }
}
