package com.example.SolutionDemos.password;

import java.util.UUID;

public class PasswordGenerator {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        String pas = uuid.toString().replace("-","");
        System.out.println(pas.substring(4,17));
    }
}
