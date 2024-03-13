package com.ptit.insurance.Controller;

import com.ptit.insurance.Model.Organization;
import com.ptit.insurance.Service.JwtService;
import com.ptit.insurance.Service.OrganizationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organization")
@CrossOrigin
@RequiredArgsConstructor
public class OrganizationRestController {
    private final OrganizationService organizationService;
    private final JwtService jwtService;

    @GetMapping("/calculate_Insurance/{insurance_code}")
    public ResponseEntity<?> calculateInsurance(@PathVariable("insurance_code") String insuranceCode, HttpServletRequest request) {
        String insuranceCodeCheck = jwtService.getUsernameFromJwt(request);
        if (!insuranceCode.equals(insuranceCodeCheck))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Unable to view insurance calculation");
        Organization organization = organizationService.findByInsuranceCode(insuranceCode);


        return ResponseEntity.ok("");
    }
}
