package com.ptit.insurance.Service;

import com.ptit.insurance.Lib.newPassword;
import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Model.User;
import com.ptit.insurance.Reponsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserReponsitory userReponsitory;

    public String createUser(Personal personal){
        try{
            userReponsitory.save(new User(personal.getInsuranceCode(), newPassword.getNewPassword(personal)));
            return "success";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String updateUser(User user){
        try{
            userReponsitory.save(user);
            return "sucess";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public User findUserByInsuranceCode(String insuranceCode){
        return userReponsitory.findByInsuranceCode(insuranceCode).get();
    }

}
