package com.ptit.insurance.Service;

import com.ptit.insurance.Model.ExemptionLevel;
import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Reponsitory.ExemptionLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
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

    public ExemptionLevel findByPersonalInTime(Personal personal, LocalDate time) {
        List<ExemptionLevel> exemptionLevels = exemptionLevelRepository.findAll();
        for (int i = 0; i < exemptionLevels.size(); i++) {
            ExemptionLevel exemptionLevelTmp = exemptionLevels.get(i);
            Date beginAt = exemptionLevelTmp.getBeginAt();
            LocalDate beginAtL = LocalDate.of(beginAt.getYear(),beginAt.getMonth(),1);
            Date endAt = exemptionLevelTmp.getEndAt();
            boolean isPersonal = exemptionLevelTmp.getPersonal().getInsuranceCode().equals(personal.getInsuranceCode());
            boolean isTimeBetween = false;
            if (endAt == null) {
                isTimeBetween = time.isAfter(beginAtL) || time.equals(beginAtL);
            } else {
                LocalDate endAtL = LocalDate.of(endAt.getYear(),endAt.getMonth(),1);
                isTimeBetween = (time.isAfter(beginAtL) || time.equals(beginAtL)) && time.isBefore(endAtL);
            }
            if (isPersonal && isTimeBetween) return exemptionLevelTmp;
        }
        return null;
    }


}
