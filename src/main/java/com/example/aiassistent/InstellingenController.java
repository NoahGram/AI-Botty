package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InstellingenController {

    @FXML
    private JFXTextField emailField;

    @FXML
    private JFXButton changeEmail;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXButton changeUsername;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXButton changePassword;

    @FXML
    private JFXButton back;

    @FXML
    private void settingPage(ActionEvent event) {
        AssistentApplication.showLoginScene();
    }

}