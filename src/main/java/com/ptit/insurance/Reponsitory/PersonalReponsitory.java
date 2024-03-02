package com.ptit.insurance.Reponsitory;

import com.ptit.insurance.Model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalReponsitory extends JpaRepository<Personal,String> {
    Personal findFirstByInsuranceCode(String insuranceCode);
}
