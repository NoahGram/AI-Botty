package com.example.aiassistent;
import java.util.HashMap;

public class UserAccountSingleton {
    private static UserAccountSingleton instance;
    private HashMap<String, User> userMap = new HashMap<>();

    private UserAccountSingleton() {
        CreateInitialAccounts();
    }

    private void CreateInitialAccounts() {
        AddUser(new User("noah", "password1", "noah@outlook.com"));
        AddUser(new User("tim", "password2", "tim@outlook.com"));
        AddUser(new User("anita", "password3", "anita@outlook.com"));
    }

    // static block initialization for exception handling
    public static synchronized UserAccountSingleton getInstance() {
        if (instance == null) {
            instance = new UserAccountSingleton();
        }
        return instance;
    }

    public boolean UserExists(String username)
    {
        return userMap.containsKey(username);
    }

    public boolean AddUser(User user) {
        if (UserExists(user.GetUsername())) {
            return false;
        }

        userMap.put(user.GetUsername(), user);
        return true;
    }

    public User GetUser(String username)
    {
        return userMap.get(username);
    }

    public boolean UserPasswordCorrect(String username, String password)
    {
        return
            UserExists(username) &&
            GetUser(username).GetPassword().equals(password);
    }

    public static void logOut() {
        LoginPageController.currentUser = null;
        AssistentApplication.showLoginScene();
    }
}
