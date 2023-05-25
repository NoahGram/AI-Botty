package com.example.aiassistent;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InstellingenController {
    private JFXTextField emailField;
    private JFXTextField usernameField;
    private JFXPasswordField passwordField;

    @FXML
    private void settingPage(ActionEvent event) {
        AssistentApplication.showChatScene();
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void changeTheme(ActionEvent event) {
        AssistentApplication.changeTheme();
    }
}