package com.ptit.insurance.Controller;

import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/personal")
public class PersonalRestController {
    private final PersonalService personalService;
    @PostMapping("/information_declaration")
    public ResponseEntity<?> informationDeclaration(@RequestBody Personal personal){
        if(personal.CheckDeclaration()) return ResponseEntity.status(HttpStatus.CONFLICT).body("Declaration missing information");
        try{
            Personal personalCheck = personalService.findByInsuranceCode(personal.getInsuranceCode());
            if(personalCheck==null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Personal does not exist in database");
            if(personal.checkDeclaration(personalCheck))
                ResponseEntity.status(HttpStatus.CONFLICT).body("Declaration information does not match");
            personalService.Save(personal);
            return ResponseEntity.status(HttpStatus.OK).body("Declaration success");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't declaration");
        }

    }



}
