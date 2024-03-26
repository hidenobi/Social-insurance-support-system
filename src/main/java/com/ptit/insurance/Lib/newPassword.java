package com.ptit.insurance.Lib;

import com.ptit.insurance.Model.Personal;

import java.time.LocalTime;
import java.util.Random;

public class newPassword {


    public static String getNewPassword(Personal personal) {
        Random random = new Random();
        String newPassword = "";
        newPassword += LocalTime.now().toString().replace(":", random.nextInt() + "");
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

}