package com.fixsys.ctfyphcd.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptPassword {

    public static void main(String[] args) {

        String password ="123";
        System.out.println("password sin encriptar: " + password);
        System.out.println("password encriptada: " + encriptarPassword(password));
    }

    public static String encriptarPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
