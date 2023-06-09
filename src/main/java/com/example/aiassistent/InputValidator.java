package com.example.aiassistent;

public class InputValidator {
    public static boolean isValidEmail(String email) {
        String pattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(pattern);
    }

    public static boolean isValidPassword(String password) {
        return password.matches("(?=.*[A-Z])(?=.*\\d).{6,}");
    }

    public static boolean isValidUsername(String username) {
        return username.length() >= 6;
    }
}
