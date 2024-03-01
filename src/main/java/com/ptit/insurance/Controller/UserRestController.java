package com.ptit.insurance.Controller;


import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Model.User;
import com.ptit.insurance.Service.PersonalService;
import com.ptit.insurance.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
@CrossOrigin
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private PersonalService personalService;
    @PostMapping("/register")
    public String register(@RequestBody Personal personal){
        try{
            personalService.Save(personal);
            userService.createUser(personal);
            return "success";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("/changePassword")
    public String changePassword(@RequestBody User user){
        try{
            userService.updateUser(user);
            return "success";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/login")
    public Personal login(@RequestBody User user){
            return personalService.findByInsuranceCode(user.getInsuranceCode());
    }
}
