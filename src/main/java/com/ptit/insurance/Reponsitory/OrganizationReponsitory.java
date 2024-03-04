package com.ptit.insurance.Reponsitory;

import com.ptit.insurance.Model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationReponsitory extends JpaRepository<Organization,String> {
   Organization findByInsuranceCode(String insuranceCode);
}
