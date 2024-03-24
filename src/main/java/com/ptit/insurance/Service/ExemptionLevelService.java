package com.ptit.insurance.Service;

import com.ptit.insurance.Model.ExemptionLevel;
import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Reponsitory.ExemptionLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;

@Service
public class ExemptionLevelService {
    @Autowired
    private ExemptionLevelRepository exemptionLevelRepository;

    public boolean save(ExemptionLevel exemptionLevel){
        try{
            exemptionLevelRepository.save(exemptionLevel);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ExemptionLevel findByPersonalInTime(Personal personal, Time time){
        ExemptionLevel exemptionLevel = exemptionLevelRepository.findFirstByPersonalInTime(personal,time);
        return exemptionLevel;
    }

}
