package com.example.robotqabackend.infra.util;

public class TokenUtils {
    public static String extractToken(String authorizationHeader) {
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        } else {
            return "";
        }
    }
}
