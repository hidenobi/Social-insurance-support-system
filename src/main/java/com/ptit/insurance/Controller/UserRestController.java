package com.ptit.insurance.Controller;


import com.ptit.insurance.Lib.EmailSender;
import com.ptit.insurance.Lib.newPassword;
import com.ptit.insurance.Model.*;
import com.ptit.insurance.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final PersonalService personalService;
    private final JwtService jwtService;
    private final EmailSender emailSender;
    private final OrganizationService organizationService;
    private final InsuranceAgencyService insuranceAgencyService;
    @GetMapping("/insurance_payment_for_register")
    public ResponseEntity<?> insurancePayment(){
        List<InsuranceAgency> insuranceAgencyList = insuranceAgencyService.getAll();
        if(insuranceAgencyList==null) ResponseEntity.badRequest().body("Can't find insurance agency");
        else{
            return ResponseEntity.ok().body(insuranceAgencyList);
        }
        return ResponseEntity.badRequest().body("Server can't response");
    }


    @PostMapping("/register/personal")
    public ResponseEntity<String> registerPersonal(@RequestBody Personal personal) {
        if (!personal.Check()) return ResponseEntity.status(HttpStatus.CONFLICT).body("Incorrect personal format");
        String insuranceCode = personal.getInsuranceCode();
        Personal personalCheck = personalService.findByInsuranceCode(insuranceCode);
        if (personalCheck != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The personal already exists in the database");
        } else {
            try {
                if (personalService.Save(personal) && userService.createUser(personal)) {
                    String newPass = newPassword.getNewPassword(personal);
                    if (emailSender.sendEmail(personal.getEmail(), "New password", "New password: " + newPass))
                        return ResponseEntity.status(HttpStatus.OK).body("register success");
                    else {
                        personalService.Delete(personal);
                        userService.Delete(personal);
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can't register");
                    }
                } else {
                    personalService.Delete(personal);
                    userService.Delete(personal);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can't register");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't register");
            }
        }
    }

    @PostMapping("/register/organization")
    public ResponseEntity<String> registerOrganization(@RequestBody Organization organization) {
        if (!organization.Check())
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Incorrect organization format");
        String insuranceCode = organization.getInsuranceCode();
        Organization personalCheck = organizationService.findByInsuranceCode(insuranceCode);
        if (personalCheck != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The organization already exists in the database");
        } else {
            try {
                if (organizationService.Save(organization) && userService.createUser(organization)) {
                    String newPass = newPassword.getNewPassword(organization);
                    if (emailSender.sendEmail(organization.getEmail(), "New password", "New password: " + newPass))
                        return ResponseEntity.status(HttpStatus.OK).body("register success");
                    else {
                        organizationService.Delete(organization);
                        userService.Delete(organization);
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can't register");
                    }
                } else {
                    organizationService.Delete(organization);
                    userService.Delete(organization);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Can't register");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't register");
            }
        }
    }


    @PostMapping("/change_password")
    public ResponseEntity<String> changePassword(@RequestBody User user) {
        try {
            if (userService.findUserByInsuranceCode(user.getInsuranceCode()) == null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("The user does not exist in the database ");
            userService.updateUser(user);
            return ResponseEntity.ok("Update successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't update user");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User uCheck = userService.findUserByInsuranceCode(user.getInsuranceCode());
        if (uCheck == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        if (user.getPassword().equals(uCheck.getPassword())) {
            User userJWT = userService.findUserByInsuranceCode(user.getInsuranceCode());
            String JWT = this.jwtService.generateToken(userJWT);
            System.out.println(user.getInsuranceCode() + " token: " + JWT);
            Token token = new Token(JWT, new Date(System.currentTimeMillis() + (1000 * 60 * 24)));
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        try {
            String username = jwtService.getUsernameFromJwt(request);
            if (username == null) ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can't find user from token");
            User user = userService.findUserByInsuranceCode(username);
            if (user == null) ResponseEntity.status(HttpStatus.NOT_FOUND).body("User does not exist in database");
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Can't find user");
        }
    }


}
