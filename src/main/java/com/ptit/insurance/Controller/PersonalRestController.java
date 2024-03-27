package com.ptit.insurance.Controller;

import com.ptit.insurance.Lib.TypeInsurance;
import com.ptit.insurance.Lib.UUID;
import com.ptit.insurance.Model.*;
import com.ptit.insurance.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
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
    private final MonthPaymentService monthPaymentService;

    @GetMapping("/get_personal")
    public ResponseEntity<?> getPersonalByUser(HttpServletRequest request) {
        String insuranceCode = jwtService.getUsernameFromJwt(request);
        if (insuranceCode == null) {
            return ResponseEntity.badRequest().body("Can't find the user");
        }
        Personal personal = personalService.findByInsuranceCode(insuranceCode);
        if (personal == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Can't find the personal");
        }
        return ResponseEntity.ok().body(personal);
    }


    @PostMapping("/information_declaration")
    public ResponseEntity<?> informationDeclaration(@RequestBody Personal personal) {
        System.out.println("information_declaration");
        if (!personal.CheckDeclaration())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Declaration missing information");
        try {
            Personal personalCheck = personalService.findByInsuranceCode(personal.getInsuranceCode());
            if (personalCheck == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Personal does not exist in database");
            }
            if (!personalCheck.getTypeInsurance().equals(TypeInsurance.NONE)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User can't declare");
            }
            if (personal.checkDeclaration(personalCheck)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Declaration information does not match");
            }

            if (personalService.Save(personal)) {
                Date currentTime = personal.getBeginAt();
                Income income = new Income(UUID.generateUUID(), personal, personal.getIncome(), currentTime, null);
                ExemptionLevel exemptionLevel = new ExemptionLevel(UUID.generateUUID(), personal, personal.getExemptionLevel(), currentTime, null, personal.getExemptionLevelUrlImg());
                incomeService.save(income);
                exemptionLevelService.save(exemptionLevel);
                return ResponseEntity.status(HttpStatus.OK).body("Declaration success");
            } else {
                //TODO
                System.out.println("Can't save personal");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't declaration");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //TODO
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't declaration");
        }
    }

    @GetMapping("/calculate_Insurance/{insurance_code}")
    public ResponseEntity<?> calculateInsurance(@PathVariable("insurance_code") String insuranceCode, HttpServletRequest request) {
        try {
            String insuranceCodeCheck = jwtService.getUsernameFromJwt(request);
            if (insuranceCodeCheck == null) {
                return ResponseEntity.badRequest().body("Can't find the user");
            }
            if (!insuranceCode.equals(insuranceCodeCheck)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Unable to view insurance calculation");
            }
            Personal personal = personalService.findByInsuranceCode(insuranceCode);
            System.out.println("TAG-calculate_Insurance: " + personal.getTypeInsurance());
            if (!personal.getTypeInsurance().equals(TypeInsurance.VOLUNTARY)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("The user has not declared insurance");
            }
            InsurancePayment insurancePayment = insurancePaymentService.findFirstByPersonalAndIsPayment(personal, true);
            Date beginAt;
            if (insurancePayment == null) {
                beginAt = personal.getBeginAt();
            } else {
                List<MonthPayment> monthPayments = monthPaymentService.getMonthPaymentByInsurancePayment(insurancePayment);
                beginAt = monthPayments.get(0).getMonth();
                for (MonthPayment mp : monthPayments) {
                    if (beginAt.before(mp.getMonth())) {
                        beginAt = mp.getMonth();
                    }
                }
            }
            //----------------------------------------------
            Date currentTime = new Date(System.currentTimeMillis());

            List<LocalDate> dateList = getMonthsBetween(beginAt, currentTime,personal.getTimeMethodPayment());
            //TODO: cần sửa
            //------------------------------------
            List<MonthPayment> monthPaymentList = new ArrayList<>();
            InsurancePayment insurancePaymentNew = new InsurancePayment(UUID.generateUUID(), personal, 0, false);
//            insurancePaymentService.save(insurancePaymentNew);
            double monthPayment = 0;
            for (LocalDate date : dateList) {
                Income income = incomeService.getByPersonalInTime(personal, date);
                ExemptionLevel exemptionLevel = exemptionLevelService.findByPersonalInTime(personal, date);
                monthPayment += 0.22 * income.getIncome() * (1 - exemptionLevel.getExemptionLevel());

                MonthPayment monthPaymentNew = new MonthPayment(UUID.generateUUID(), income, exemptionLevel, personal.getTimeMethodPayment(), new Date(date.toEpochDay()), insurancePaymentNew);
                monthPaymentList.add(monthPaymentNew);
//                monthPaymentService.save(monthPaymentNew);
            }
            insurancePaymentNew.setTotalPayment((long) monthPayment);
            insurancePaymentNew.setMonthPaymentList(monthPaymentList);
//            insurancePaymentService.save(insurancePaymentNew);
            System.out.println("TAG-idInsurancePayment: " + insurancePaymentNew.getId());
            return ResponseEntity.status(HttpStatus.OK).body(insurancePaymentNew);
        } catch (Exception e) {
            System.out.println("TAG-error: " + e.getCause());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getCause());
        }

    }

    private static List<LocalDate> getMonthsBetween(Date mStart, Date mEnd, int timePaymentMethod) {
        List<YearMonth> monthsList = new ArrayList<>();
        YearMonth start = YearMonth.of(mStart.getYear(), mStart.getMonth());
        YearMonth end = YearMonth.of(mEnd.getYear(), mEnd.getMonth());
        YearMonth current = start;
        while (!current.isAfter(end)) {
            monthsList.add(current);
            current = current.plusMonths(1);
        }

        int monthBonus = 0;
        int monthDismiss = monthsList.size() % timePaymentMethod;
        if (monthDismiss != 0) {
            monthBonus = timePaymentMethod - monthDismiss;
        }
        while ((monthBonus-- > 0)) {
            monthsList.add(current);
            current = current.plusMonths(1);
        }
        ArrayList<LocalDate> answer = new ArrayList<>();
        monthsList.forEach(yearMonth -> {
            LocalDate date = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
            answer.add(date);
        });
        return answer;
    }
}
