package com.example.aiassistent;
import java.util.HashMap;

public class UserAccountSingleton {
    private static UserAccountSingleton instance;
    private User currentUser;
    private HashMap<String, User> userMap = new HashMap<>();

    private UserAccountSingleton() {
        CreateInitialAccounts();
    }

    private void CreateInitialAccounts() {
        addUser(new User("noah", "password1", "noah@outlook.com", true));
        addUser(new User("tim", "password2", "tim@outlook.com", true));
        addUser(new User("anita", "password3", "anita@outlook.com", true));
        addUser(new User("bob", "password4", "bob@outlook.com", false));
    }

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

    public void addUser(User user) {
        if (UserExists(user.getUsername())) {
            return;
        }

        userMap.put(user.getUsername(), user);
    }


    public boolean UserPasswordCorrect(String username, String password) {
        User user = getUser(username);
        return user != null && user.getPassword().equals(password);
    }

    public static void logOut() {
        AssistentApplication.showLoginScene();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User getUser(String username) {
        return userMap.get(username);
    }

    public void editUsername(String newUsername) {
        User user = getUser(getCurrentUser().getUsername());
        if (user != null) {
            User updatedUser = new User(newUsername, user.getPassword(), user.getEmail(), user.getAdmin());
            userMap.remove(user.getUsername());
            userMap.put(newUsername, updatedUser);
            setCurrentUser(updatedUser);
        }
    }

    public void editEmail(String username, String newEmail) {
        User user = getUser(username);
        if (user != null) {
            User updatedUser = new User(user.getUsername(), user.getPassword(), newEmail, user.getAdmin());
            userMap.put(username, updatedUser);
        }
    }

    public void editPassword(String username, String newPassword) {
        User user = getUser(username);
        if (user != null) {
            User updatedUser = new User(user.getUsername(), newPassword, user.getEmail(), user.getAdmin());
            userMap.put(username, updatedUser);
        }
    }

}
