package com.ptit.insurance.Reponsitory;

import com.ptit.insurance.Model.Income;
import com.ptit.insurance.Model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IncomeRepository extends JpaRepository<Income,String > {
    @Query("select i from Income i where i.personal=:personal and :date between i.beginAt and i.endAt")
    Income findFirstByPersonalInTime(Personal personal, Date date);


}
