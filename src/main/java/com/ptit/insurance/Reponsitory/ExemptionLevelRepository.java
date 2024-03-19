package com.ptit.insurance.Reponsitory;

import com.ptit.insurance.Model.ExemptionLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemptionLevelRepository extends JpaRepository<ExemptionLevel,String> {
}
