package com.ptit.insurance.Lib;

public class UUID {

    public static String generateUUID(){
        java.util.UUID uuid = java.util.UUID.randomUUID();
        return uuid.toString();
    }
}
