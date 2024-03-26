package com.ptit.insurance.Service;

import com.ptit.insurance.Model.ExemptionLevel;
import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Reponsitory.ExemptionLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class ExemptionLevelService {
    @Autowired
    private ExemptionLevelRepository exemptionLevelRepository;

    public boolean save(ExemptionLevel exemptionLevel) {
        try {
            exemptionLevelRepository.save(exemptionLevel);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public ExemptionLevel findByPersonalInTime(Personal personal, Date time) {
        List<ExemptionLevel> exemptionLevels = exemptionLevelRepository.findAll();
        for (int i = 0; i < exemptionLevels.size(); i++) {
            ExemptionLevel exemptionLevelTmp = exemptionLevels.get(i);
            boolean isPersonal = exemptionLevelTmp.getPersonal().getInsuranceCode().equals(personal.getInsuranceCode());
            boolean isTimeBetween = false;
            if (exemptionLevelTmp.getEndAt() == null) {
                isTimeBetween = time.after(exemptionLevelTmp.getBeginAt()) || time.equals(exemptionLevelTmp.getBeginAt());
            } else {
                isTimeBetween = (time.after(exemptionLevelTmp.getBeginAt()) || time.equals(exemptionLevelTmp.getBeginAt())) && time.before(exemptionLevelTmp.getEndAt());
            }
            if (isPersonal && isTimeBetween) return exemptionLevelTmp;
        }
        return null;
    }

}
