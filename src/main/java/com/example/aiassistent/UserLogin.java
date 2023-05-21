package com.example.aiassistent;
import java.util.HashMap;
public class UserLogin {
    private static UserLogin instance;
    private HashMap<String, String> userMap;

    private UserLogin() {
        userMap = new HashMap<>();
    }

    // static block initialization for exception handling
    public static UserLogin getInstance() {
        if (instance == null) {
            instance = new UserLogin();
        }
        return instance;
    }

    public HashMap<String, String> getUserMap() {
        return userMap;
    }
}
