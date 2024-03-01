package com.ptit.insurance.Service;

import com.ptit.insurance.Model.InsurancePayment;
import com.ptit.insurance.Reponsitory.InsurancePaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsurancePaymentService {
    @Autowired
    private InsurancePaymentRepository insurancePaymentRepository;

}
