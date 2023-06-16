package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterPageController extends BaseController {
    public TextField usernamefield;
    public TextField passwordfield;
    public TextField emailField;
    public Text invalid;
    public Text title;
    public JFXButton registerButton;
    public Text username;
    public Text password;
    public Button back;

    UserAccountSingleton userAccounts = UserAccountSingleton.getInstance();

    @Override
    protected void updateUI() {
        // Update the Texts
        LanguageManager.getTranslation("title");
        title.setText(LanguageManager.getTranslation("registerTitle"));
        registerButton.setText(LanguageManager.getTranslation("registerButton"));
        username.setText(LanguageManager.getTranslation("username"));
        password.setText(LanguageManager.getTranslation("password"));
        emailField.setPromptText(LanguageManager.getTranslation("emailE"));
        usernamefield.setPromptText(LanguageManager.getTranslation("usernameE"));
        passwordfield.setPromptText(LanguageManager.getTranslation("passwordE"));
        back.setText(LanguageManager.getTranslation("back"));


    }

    @FXML
    public void registerButtonEvent() {
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        String email = emailField.getText();
        if (LanguageManager.getTranslation("change").equals("Change")){
            if (userAccounts.UserExists(username)) {
                invalid.setText("This username already exists");
            } else if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                invalid.setText("Please fill in all fields");
            } else if (!InputValidator.isValidUsername(username)) {
                invalid.setText("Username must be at least 6 characters long");
            } else if (!InputValidator.isValidPassword(password)) {
                invalid.setText("Password has to be at least 6 characters and must contain a capital letter and a number");
            } else if (!InputValidator.isValidEmail(email)) {
                invalid.setText("Invalid e-mail");
            } else {
                userAccounts.addUser(new User(username, password, email, false));
                System.out.printf("Nieuw geregistreerde gebruiker:%nGebruikersnaam - %s%nE-mail - %s%nWachtwoord - %s", username, email, password);
                AssistentApplication.showLoginScene();
            }
        }else{
        if (userAccounts.UserExists(username)) {
            invalid.setText("Deze gebruikersnaam bestaat al.");
        } else if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            invalid.setText("Voer alstublieft alle velden in");
        } else if (!InputValidator.isValidUsername(username)) {
            invalid.setText("Gebruikersnaam moet minimaal 6 karakters lang zijn");
        } else if (!InputValidator.isValidPassword(password)) {
            invalid.setText("Wachtwoord moet minimaal 6 karakters zijn en een hoofdletter en een cijfer bevatten");
        } else if (!InputValidator.isValidEmail(email)) {
            invalid.setText("Ongeldige e-mail");
        } else {
            userAccounts.addUser(new User(username, password, email, false));
            System.out.printf("Nieuw geregistreerde gebruiker:%nGebruikersnaam - %s%nE-mail - %s%nWachtwoord - %s", username, email, password);
            AssistentApplication.showLoginScene();
        }}
    }


    @FXML
    private void onEnter() {
        registerButtonEvent();
    }

    @FXML
    private void loginPage() {
        AssistentApplication.showLoginScene();
        invalid.setText("");
        emailField.setText("");
        usernamefield.setText("");
        passwordfield.setText("");
    }

    @FXML
    private void exit() {
        System.exit(0);
    }
}
