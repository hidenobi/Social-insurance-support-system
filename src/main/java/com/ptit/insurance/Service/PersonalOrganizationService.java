package com.ptit.insurance.Service;

import com.ptit.insurance.Reponsitory.PersonalOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalOrganizationService {
    @Autowired
    private PersonalOrganizationRepository personalOrganizationRepository;
}
