package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginPageController {

    @FXML public JFXButton loginbutton;
    @FXML public Button exitButton;
    @FXML public Button registerButton;
    @FXML public Button settingButton;
    @FXML public JFXTextField usernamefield;
    @FXML public JFXPasswordField passwordfield;
    @FXML public Text invalid;
    @FXML public Button theme;
    public Text title;
    public GridPane menu;
    public GridPane background;

    UserAccountSingleton userAccounts = UserAccountSingleton.getInstance();

    @FXML
    public void loginButtonEvent(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();

        if (userAccounts.UserPasswordCorrect(username, password)) {
            UserAccountSingleton.currentUser = username;
            AssistentApplication.showChatScene();
        } else {
            invalid.setText("De verstrekte inloggegevens zijn incorrect");
        }
    }

    @FXML
    private void registerPage(ActionEvent event) {
        AssistentApplication.showRegisterScene();
        invalid.setText("");
    }

    @FXML
    private void settingPage(ActionEvent event) {
        AssistentApplication.showSettingScene();
        invalid.setText("");
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void onEnter(ActionEvent event) {
        loginButtonEvent(event);
    }

    @FXML
    private void changeTheme(ActionEvent event) {
        AssistentApplication.changeTheme();
    }
}


