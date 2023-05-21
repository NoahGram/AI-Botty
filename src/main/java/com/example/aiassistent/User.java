package com.example.aiassistent;

public class User {
    private String username;
    public String GetUsername() { return username; }

    private String password;
    public String GetPassword() { return password; }
    private String email;


    public User(
            String username,
            String password,
            String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
