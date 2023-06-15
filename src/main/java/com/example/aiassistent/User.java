package com.example.aiassistent;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String email;
    private boolean admin;
    private List<String> chats;

    public User(String username, String password, String email, boolean admin) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
        this.chats = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public boolean getAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<String> getChats() {
        return chats;
    }

    public void addChat(String chat) {
        chats.add(chat);
    }
}
