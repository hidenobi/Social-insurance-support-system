package com.ptit.insurance.Service;

import com.ptit.insurance.Model.InsurancePayment;
import com.ptit.insurance.Model.MonthPayment;
import com.ptit.insurance.Reponsitory.MonthPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthPaymentService {
    @Autowired
    private MonthPaymentRepository monthPaymentRepository;

    public MonthPayment findFirstByOrderByMonth(){
        MonthPayment monthPayment = monthPaymentRepository.findFirstByOrderByMonth();
        return monthPayment;
    }

    public List<MonthPayment> getMonthPaymentByInsurancePayment(InsurancePayment insurancePayment){
        List<MonthPayment> monthPaymentList = monthPaymentRepository.getMonthPaymentByInsurancePayment(insurancePayment);
        return  monthPaymentList;
    }

    public void save(MonthPayment monthPaymentNew) {
        monthPaymentRepository.save(monthPaymentNew);
    }
}
