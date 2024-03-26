package com.ptit.insurance.Service;

import com.ptit.insurance.Model.Income;
import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Reponsitory.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
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

    public Income getByPersonalInTime(Personal personal, Date time) {
        List<Income> incomes = incomeRepository.findAll();
        for (int i = 0; i < incomes.size(); i++) {
            Income incomeTmp = incomes.get(i);
            boolean isPersonal = incomeTmp.getPersonal().getInsuranceCode().equals(personal.getInsuranceCode());
            boolean isTimeBetween = false;
            if (incomeTmp.getEndAt() == null) {
                isTimeBetween = time.after(incomeTmp.getBeginAt()) || time.equals(incomeTmp.getBeginAt());
            } else {
                isTimeBetween = (time.after(incomeTmp.getBeginAt()) || time.equals(incomeTmp.getBeginAt())) && time.before(incomeTmp.getEndAt());
            }
            if (isPersonal && isTimeBetween) return incomeTmp;
        }
        return null;
    }

}
