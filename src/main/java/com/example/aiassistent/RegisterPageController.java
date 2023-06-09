package com.example.aiassistent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
            invalid.setText("Gebruikersnaam is al in gebruik.");
        } else if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            invalid.setText("Voer alstublieft alle velden in.");
        } else if (!InputValidator.isValidUsername(username)) {
            invalid.setText("Gebruikersnaam moet minimaal 6 karakters zijn.");
        } else if (!InputValidator.isValidPassword(password)) {
            invalid.setText("Wachtwoord moet minimaal 6 karakters zijn en een hoofdletter en een cijfer bevatten.");
        } else if (!InputValidator.isValidEmail(email)) {
            invalid.setText("Ongeldige e-mail.");
        } else {
            userAccounts.addUser(new User(username, password, email));
            System.out.printf("Nieuw geregistreerde gebruiker:%nGebruikersnaam - %s%nE-mail - %s%nWachtwoord - %s", username, email, password);
            AssistentApplication.showLoginScene();
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
