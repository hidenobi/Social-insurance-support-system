package com.ptit.insurance.Service;

import com.ptit.insurance.Model.Income;
import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Reponsitory.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    public boolean save(Income income) {
        try {
            incomeRepository.save(income);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Income getByPersonalInTime(Personal personal, LocalDate time) {
        System.out.println(time);
        List<Income> incomes = incomeRepository.findAll();
        for(Income income : incomes){
            System.out.println(income);
        }
        for (int i = 0; i < incomes.size(); i++) {
            Income incomeTmp = incomes.get(i);
            System.out.println(incomeTmp.getBeginAt()+" "+incomeTmp.getEndAt());
            Date beginAt = incomeTmp.getBeginAt();
            Date endDat = incomeTmp.getEndAt();
            boolean isPersonal = incomeTmp.getPersonal().getInsuranceCode().equals(personal.getInsuranceCode());
            boolean isTimeBetween = false;
            if (endDat == null) {
                isTimeBetween = time.isAfter(LocalDate.of(beginAt.getYear(), beginAt.getMonth(),1) ) || time.equals(LocalDate.of(beginAt.getYear(), beginAt.getMonth(),1));
            } else {
                isTimeBetween = (time.isAfter(LocalDate.of(beginAt.getYear(), beginAt.getMonth(),1)) || time.equals(LocalDate.of(beginAt.getYear(), beginAt.getMonth(),1))) && (time.isBefore(LocalDate.of(endDat.getYear(), endDat.getMonth(),1))||time.equals(LocalDate.of(endDat.getYear(), endDat.getMonth(),1)));
            }
            if (isPersonal && isTimeBetween) return incomeTmp;
        }
        return null;
    }

}
