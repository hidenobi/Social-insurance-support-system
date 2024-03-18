package com.ptit.insurance.Controller;

import com.ptit.insurance.Lib.TypeInsurance;
import com.ptit.insurance.Lib.UUID;
import com.ptit.insurance.Model.ExemptionLevel;
import com.ptit.insurance.Model.Income;
import com.ptit.insurance.Model.InsurancePayment;
import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/personal")
public class PersonalRestController {
    private final PersonalService personalService;
    private final JwtService jwtService;
    private final InsurancePaymentService insurancePaymentService;
    private final IncomeService incomeService;
    private final ExemptionLevelService exemptionLevelService;
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
            Date currentDate = new Date();
            Time currentTime = new Time(currentDate.getTime());
            Time endTime = new Time(currentTime.getTime()+ (long) personal.getTimeMethodPayment()*24*60*60*1000);
            int money = (int) ((0.22*personal.getIncome())*(personal.getTimeMethodPayment())*(1- personal.getExemptionLevel()));
            InsurancePayment insurancePayment = new InsurancePayment(UUID.generateUUID(),personal,currentTime,endTime,0,money,false);
            Income income = new Income(UUID.generateUUID(),personal,personal.getIncome(),currentTime,null);
            ExemptionLevel exemptionLevel = new ExemptionLevel(UUID.generateUUID(),personal, personal.getExemptionLevel(), currentTime,null, personal.getExemptionLevelUrlImg());
            if(incomeService.save(income)&&insurancePaymentService.Save(insurancePayment)&&personalService.Save(personal)&&exemptionLevelService.save(exemptionLevel)){
                return ResponseEntity.status(HttpStatus.OK).body("Declaration success");
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't declaration");
            }

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
