package com.ptit.insurance.Reponsitory;

import com.ptit.insurance.Model.InsurancePayment;
import com.ptit.insurance.Model.MonthPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonthPaymentRepository extends JpaRepository<MonthPayment ,String> {
    List<MonthPayment> getMonthPaymentByInsurancePayment(InsurancePayment insurancePayment);
    @Query("select m  from MonthPayment m ORDER BY m.month DESC ")
    MonthPayment findFirstByOrderByMonth();
}
