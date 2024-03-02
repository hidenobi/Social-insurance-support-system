package com.ptit.insurance.Reponsitory;

import com.ptit.insurance.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReponsitory extends JpaRepository<User,String> {
    Optional<User> findByInsuranceCode(String insuranceCode);
}
