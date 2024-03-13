package com.ptit.insurance.Lib;

import com.ptit.insurance.Model.Organization;
import com.ptit.insurance.Model.Personal;


import java.time.LocalTime;

public class newPassword {

    public static String getNewPassword(Personal personal) {
        String newPassword = "";
        newPassword += LocalTime.now();
        String fullName = personal.getFullName().toUpperCase();
        newPassword += fullName.charAt(0);
        for (int i = 1; i < fullName.length(); i++) {
            if (fullName.charAt(i - 1) == ' ') {
                newPassword += fullName.charAt(i);
            }
        }
        System.out.println("new password: " + newPassword);
        return newPassword;
    }

    public static String getNewPassword(Organization organization) {
        String newPassword = "";
        newPassword += LocalTime.now();
        String fullName = organization.getName().toUpperCase();
        newPassword += fullName.charAt(0);
        for (int i = 1; i < fullName.length(); i++) {
            if (fullName.charAt(i - 1) == ' ') {
                newPassword += fullName.charAt(i);
            }
        }
        System.out.println(newPassword);
        return newPassword;
    }
}
