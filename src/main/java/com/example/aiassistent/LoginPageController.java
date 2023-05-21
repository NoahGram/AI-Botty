package com.example.aiassistent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class LoginPageController {
    public Button loginbutton;
    public TextField usernamefield;
    public TextField passwordfield;
    public Button settingButton;

    //public TextField errorField;

    UserAccountSingleton userAccounts = UserAccountSingleton.getInstance();

    @FXML
    public void loginButtonEvent(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();

        if (userAccounts.UserPasswordCorrect(username, password)) {
            AssistentApplication.showChatScene();
        } else {
            //errorField.setText("Invalid username or password.");
        }
    }

    @FXML
    private void registerPage(ActionEvent event) {
        AssistentApplication.showRegisterScene();
    }

    @FXML
    private void settingPage(ActionEvent event) {
        AssistentApplication.showSettingScene();
    }
}


