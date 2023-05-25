package com.example.aiassistent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.HashMap;

public class RegisterPageController {
    public TextField usernamefield;
    public TextField passwordfield;
    public TextField emailField;
    public Text invalid;

    UserAccountSingleton userAccounts = UserAccountSingleton.getInstance();

    @FXML
    public void registerButtonEvent(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        String email = emailField.getText();
        if (userAccounts.UserExists(username)) {
            invalid.setText("Gebruikersnaam is al genomen");
        } else if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            invalid.setText("Voer alstublieft alle velden in.");
        } else {
            userAccounts.AddUser(new User(username, password, email));
            System.out.printf("New registered user: %n Username - %s%n Email - %s%n Password - %s", username, email, password);
            AssistentApplication.showChatScene();
        }
    }

    @FXML
    private void onEnter(ActionEvent event) {
        registerButtonEvent(event);
    }

    @FXML
    private void loginPage(ActionEvent event) {
        AssistentApplication.showLoginScene();
        invalid.setText("");
        emailField.setText("");
        usernamefield.setText("");
        passwordfield.setText("");
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
