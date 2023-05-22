package com.example.aiassistent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class RegisterPageController {
    public Button loginbutton;
    public TextField usernamefield;
    public TextField passwordfield;

    UserAccountSingleton userAccounts = UserAccountSingleton.getInstance();

    public void registerButtonEvent(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        if (userAccounts.UserExists(username)) {
            //errorField.setText("Username already taken.");
        } else {
            userAccounts.AddUser(new User(username, password, ""));
            AssistentApplication.showChatScene();
        }
    }

    @FXML
    private void loginPage(ActionEvent event) {
        AssistentApplication.showLoginScene();
    }
}
