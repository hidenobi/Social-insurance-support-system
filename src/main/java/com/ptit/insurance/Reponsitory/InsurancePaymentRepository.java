package com.ptit.insurance.Reponsitory;

import com.ptit.insurance.Model.InsurancePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsurancePaymentRepository extends JpaRepository<InsurancePayment, String> {
    @Query("select ip from InsurancePayment ip where ip.personal.insuranceCode = :insuranceCode and ip.isPayment = :isPayment")
    List<InsurancePayment> retrieveUnpaidInvoicesIndividually(String insuranceCode, boolean isPayment);
    InsurancePayment findFirstById(String id);
}
