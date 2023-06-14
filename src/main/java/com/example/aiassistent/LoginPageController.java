package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class LoginPageController {
    @FXML
    private JFXButton loginbutton;
    @FXML
    private Button exitButton;
    @FXML
    private Button registerButton;
    @FXML
    private JFXTextField usernamefield;
    @FXML
    private JFXPasswordField passwordfield;

    public void setUsernamefield(String usernamefield) {
        this.usernamefield.setText(usernamefield);
    }

    public void setPasswordfield(String passwordfield) {
        this.passwordfield.setText(passwordfield);
    }

    @FXML
    public Text invalid;
    @FXML
    private Button theme;

    private UserAccountSingleton userAccounts = UserAccountSingleton.getInstance();


    @FXML
    public void loginButtonEvent(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();

        if (userAccounts.UserPasswordCorrect(username, password)) {
            userAccounts.setCurrentUser(userAccounts.getUser(username));
            invalid.setText("");
            usernamefield.setText("");
            passwordfield.setText("");
            AssistentApplication.showChatScene();
        } else if (username.isEmpty() || password.isEmpty()) {
            invalid.setText("Voer alstublieft alle velden in.");
        } else {
            invalid.setText("");
            passwordfield.setText("");
            invalid.setText("De verstrekte inloggegevens zijn incorrect.");
        }
    }

    @FXML
    private void registerPage(ActionEvent event) {
        AssistentApplication.showRegisterScene();
        invalid.setText("");
        usernamefield.setText("");
        passwordfield.setText("");
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


