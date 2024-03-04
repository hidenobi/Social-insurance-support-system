package com.ptit.insurance.Service;

import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Reponsitory.PersonalReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalService {
    @Autowired
    private PersonalReponsitory personalReponsitory;

    public void Delete(Personal personal){
        if(personalReponsitory.findByInsuranceCode(personal.getInsuranceCode())!=null)
            personalReponsitory.delete(personal);
    }


    public boolean Save(Personal personal){
    try {
        personalReponsitory.save(personal);
        System.out.println("Save personal success");
        return true;
    }catch (Exception e){
        System.out.println(e.getMessage());
        return false;
    }
    }

    public Personal findByInsuranceCode(String insuranceCode){
        return personalReponsitory.findByInsuranceCode(insuranceCode);
    }
}
