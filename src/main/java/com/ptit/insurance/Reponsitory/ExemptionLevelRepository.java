package com.ptit.insurance.Reponsitory;

import com.ptit.insurance.Model.ExemptionLevel;
import com.ptit.insurance.Model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;

@Repository
public interface ExemptionLevelRepository extends JpaRepository<ExemptionLevel,String> {
    @Query("select el from ExemptionLevel el where el.personal=:personal and :time between el.beginAt and el.endAt")
    ExemptionLevel findFirstByPersonalInTime(Personal personal, Time time);
}
