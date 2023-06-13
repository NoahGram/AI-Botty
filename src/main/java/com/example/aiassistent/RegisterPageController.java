package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterPageController {
    public TextField usernamefield;
    public TextField passwordfield;
    public TextField emailField;
    public Text invalid;
    public Button EN;
    public Button NL;
    private String TXT1 = "Maak jouw account aan";
    private String TXT2 = "registeren";
    private String TXT3 = "Voer hier uw emailadres in";
    private String TXT4 = "Voer hier uw gebruikersnaam in";
    private String TXT5 = "Voer hier uw wachtwoord in";
    private String TXT6 = "wachtwoord";
    private String TXT7 = "Keer terug";
    private String TXT8 = "gebruikersnaam";

    private String TXT1E = "Create your account";

    private String TXT2E = "Create your account";
    private String TXT3E = "enter emailadres here";
    private String TXT4E = "Enter username here";
    private String TXT5E = "Enter password here";
    private String TXT6E = "password";
    private String TXT7E = "Return";
    private String TXT8E = "username";

    @FXML
    public Text title;
    @FXML
    public JFXButton txt2Text;
    @FXML
    public Text txt6Text;
    @FXML
    public Button txt7Text;
    @FXML
    public Text txt8Text;
    UserAccountSingleton userAccounts = UserAccountSingleton.getInstance();

    @FXML
    public void registerButtonEvent(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        String email = emailField.getText();
        if(AssistentApplication.isEngels){
        if (userAccounts.UserExists(username)) {
            invalid.setText("This username already exists.");
        } else if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            invalid.setText("Please fill in all fields.");
        } else if (!InputValidator.isValidUsername(username)) {
            invalid.setText("Username has to be at least 6 characters.");
        } else if (!InputValidator.isValidPassword(password)) {
            invalid.setText("Password has to be at least 6 characters and must contain a capital letter and a number.");
        } else if (!InputValidator.isValidEmail(email)) {
            invalid.setText("Invalid e-mail.");
        } else {
            userAccounts.addUser(new User(username, password, email));
            System.out.printf("Nieuw geregistreerde gebruiker:%nGebruikersnaam - %s%nE-mail - %s%nWachtwoord - %s", username, email, password);
            AssistentApplication.showLoginScene();
        }}
        else {if (userAccounts.UserExists(username)) {
            invalid.setText("Deze gebruikersnaam is al in gebruik.");
        } else if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            invalid.setText("Voer alstublieft alle velden in.");
        } else if (!InputValidator.isValidUsername(username)) {
            invalid.setText("Gebruikersnaam moet minimaal 6 karakters zijn.");
        } else if (!InputValidator.isValidPassword(password)) {
            invalid.setText("Wachtwoord moet minimaal 6 karakters zijn en moet een hoofdletter en een cijfer bevatten.");
        } else if (!InputValidator.isValidEmail(email)) {
            invalid.setText("Ongeldige e-mail.");
        } else {
            userAccounts.addUser(new User(username, password, email));
            System.out.printf("Nieuw geregistreerde gebruiker:%nGebruikersnaam - %s%nE-mail - %s%nWachtwoord - %s", username, email, password);
            AssistentApplication.showLoginScene();
        }}

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
    @FXML
    private void NL(ActionEvent event) {
        AssistentApplication.isEngels = false;
        AssistentApplication.setLang(AssistentApplication.isEngels);
        System.out.println("NL knop");
        initialize();
    }
    @FXML
    private void EN(ActionEvent event) {
        AssistentApplication.isEngels = true;
        AssistentApplication.setLang(AssistentApplication.isEngels);
        System.out.println("EN button");
        initialize();
    }
    @FXML
    private void initialize() {
        if(AssistentApplication.isEngels){
            title.setText(TXT1E);
            txt2Text.setText(TXT2E);
            emailField.setPromptText(TXT3E);
            usernamefield.setPromptText(TXT4E);
            passwordfield.setPromptText(TXT5E);
            txt6Text.setText(TXT6E);
            txt7Text.setText(TXT7E);
            txt8Text.setText(TXT8E);
        }else{
            title.setText(TXT1);
            txt2Text.setText(TXT2);
            emailField.setPromptText(TXT3);
            usernamefield.setPromptText(TXT4);
            passwordfield.setPromptText(TXT5);
            txt6Text.setText(TXT6);
            txt7Text.setText(TXT7);
            txt8Text.setText(TXT8);
        }
    }
}
