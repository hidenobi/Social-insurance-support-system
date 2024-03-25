package com.ptit.insurance.Service;

import com.ptit.insurance.Model.InsurancePayment;
import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Reponsitory.InsurancePaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsurancePaymentService {
    final private InsurancePaymentRepository insurancePaymentRepository;

    public List<InsurancePayment> RetrieveUnpaidInvoicesIndividually(String insuranceCode){
        try{
            return insurancePaymentRepository.retrieveUnpaidInvoicesIndividually(insuranceCode,false);
        }catch (Exception e){
            return null;
        }
    }

    public InsurancePayment findFirstByPersonalAndIsPayment(Personal personal, boolean isPayment){
        InsurancePayment insurancePayment = insurancePaymentRepository.findFirstByPersonalAndIsPayment(personal,isPayment);
        return insurancePayment;
    }


    public boolean Save(InsurancePayment insurancePayment){
        try{
            if(insurancePaymentRepository.findFirstById(insurancePayment.getId())==null) {
                insurancePaymentRepository.save(insurancePayment);
                return true;
            }
            else  return false;
        }catch (Exception e){
            return false;
        }
    }


    public void save(InsurancePayment insurancePaymentNew) {
        insurancePaymentRepository.save(insurancePaymentNew);
    }
}
