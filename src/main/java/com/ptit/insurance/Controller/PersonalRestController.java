package com.ptit.insurance.Controller;

import com.ptit.insurance.Lib.TypeInsurance;
import com.ptit.insurance.Model.InsurancePayment;
import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Service.InsurancePaymentService;
import com.ptit.insurance.Service.JwtService;
import com.ptit.insurance.Service.PersonalService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/personal")
public class PersonalRestController {
    private final PersonalService personalService;
    private final JwtService jwtService;
    private final InsurancePaymentService insurancePaymentService;

    @GetMapping("/get_personal")
    public ResponseEntity<?> getPersonalByUser(HttpServletRequest request){
        String insuranceCode = jwtService.getUsernameFromJwt(request);
        if (insuranceCode == null) return ResponseEntity.badRequest().body("Can't find the user");
        Personal personal = personalService.findByInsuranceCode(insuranceCode);
        if(personal==null) return ResponseEntity.status(HttpStatus.CONFLICT).body("Can't find the personal");
        return ResponseEntity.ok().body(personal);
    }


    @PostMapping("/information_declaration")
    public ResponseEntity<?> informationDeclaration(@RequestBody Personal personal) {
        System.out.println("information_declaration");
        if (!personal.CheckDeclaration())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Declaration missing information");
        try {
            Personal personalCheck = personalService.findByInsuranceCode(personal.getInsuranceCode());
            if (personalCheck == null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Personal does not exist in database");
            if (!personalCheck.getTypeInsurance().equals(TypeInsurance.NONE))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User can't declare");
            if (personal.checkDeclaration(personalCheck))
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Declaration information does not match");
            personalService.Save(personal);


            return ResponseEntity.status(HttpStatus.OK).body("Declaration success");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't declaration");
        }
    }

    @GetMapping("/calculate_Insurance/{insurance_code}")
    public ResponseEntity<?> calculateInsurance(@PathVariable("insurance_code") String insuranceCode, HttpServletRequest request) {
        String insuranceCodeCheck = jwtService.getUsernameFromJwt(request);
        long amountToPay = 0;
        if (insuranceCodeCheck == null) return ResponseEntity.badRequest().body("Can't find the user");
        if (!insuranceCode.equals(insuranceCodeCheck))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Unable to view insurance calculation");
        Personal personal = personalService.findByInsuranceCode(insuranceCode);
        if (personal.getTypeInsurance().equals(TypeInsurance.NONE))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The user has not declared insurance");
        if (personal.isForeigner()) {
            List<InsurancePayment> insurancePaymentList = insurancePaymentService.RetrieveUnpaidInvoicesIndividually(insuranceCode);
            if(insurancePaymentList==null){
                InsurancePayment insurancePayment = new InsurancePayment();

            }else {

            }

            return ResponseEntity.ok().body("");
        } else {
            List<InsurancePayment> insurancePaymentList = insurancePaymentService.RetrieveUnpaidInvoicesIndividually(insuranceCode);
            if(insurancePaymentList==null){
                InsurancePayment insurancePayment = new InsurancePayment();

            }else{

            }
            return ResponseEntity.ok().body("");
        }

    }

}
