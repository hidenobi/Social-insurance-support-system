package com.ptit.insurance.Reponsitory;

import com.ptit.insurance.Model.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income,String > {
}
