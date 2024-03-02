package com.ptit.insurance.Controller;


import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Model.User;
import com.ptit.insurance.Service.JwtService;
import com.ptit.insurance.Service.PersonalService;
import com.ptit.insurance.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final PersonalService personalService;
    private final JwtService jwtService;

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
    public String login(@RequestBody User user){
            return this.jwtService.generateToken(userService.findUserByInsuranceCode(user.getInsuranceCode()));
    }
}
