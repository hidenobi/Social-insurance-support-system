package com.ptit.insurance.Reponsitory;

import com.ptit.insurance.Model.InsurancePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsurancePaymentRepository extends JpaRepository<InsurancePayment,String> {
}
