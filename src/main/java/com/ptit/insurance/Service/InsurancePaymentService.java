package com.ptit.insurance.Service;

import com.ptit.insurance.Model.InsurancePayment;
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

}
