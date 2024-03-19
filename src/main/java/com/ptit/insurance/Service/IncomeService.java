package com.ptit.insurance.Service;

import com.ptit.insurance.Model.Income;
import com.ptit.insurance.Reponsitory.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    public boolean save(Income income){
        try{
            incomeRepository.save(income);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
