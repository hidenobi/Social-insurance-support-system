package com.ptit.insurance.Reponsitory;

import com.ptit.insurance.Model.PersonalOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalOrganizationRepository extends JpaRepository<PersonalOrganization,String> {
}
