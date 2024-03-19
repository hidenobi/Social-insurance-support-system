package com.ptit.insurance.Service;

import com.ptit.insurance.Model.ExemptionLevel;
import com.ptit.insurance.Reponsitory.ExemptionLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
