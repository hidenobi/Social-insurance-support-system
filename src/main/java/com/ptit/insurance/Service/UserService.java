package com.ptit.insurance.Service;

import com.ptit.insurance.Lib.Role;
import com.ptit.insurance.Lib.RoleUser;
import com.ptit.insurance.Lib.newPassword;
import com.ptit.insurance.Model.Personal;
import com.ptit.insurance.Model.User;
import com.ptit.insurance.Reponsitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userReponsitory;


    public void Delete(Personal personal) {
        User u = userReponsitory.findByInsuranceCode(personal.getInsuranceCode()).get();
        if (u != null) {
            userReponsitory.delete(u);
        }
    }



    public boolean createUser(Personal personal) {
        try {
            User user = new User(personal.getInsuranceCode(), newPassword.getNewPassword(personal), Role.USER, RoleUser.PERSONAL);
            userReponsitory.save(user);
            System.out.println("Save user from personal success");
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public String updateUser(User user) {
        try {
            userReponsitory.save(user);
            return "sucess";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public User findUserByInsuranceCode(String insuranceCode) {
        try {
            User user = userReponsitory.findByInsuranceCode(insuranceCode).get();
            return user;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
