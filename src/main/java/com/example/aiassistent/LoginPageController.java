package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class LoginPageController implements LanguageChangeListener {

     public JFXButton loginbutton;
     public Button exitButton;
     public Text welkom;
     public Button registerButton;
     public JFXTextField usernamefield;
     public JFXPasswordField passwordfield;
     public Text invalid;
     public Button theme;
     public ImageView logo;
    public Text title;
    public Text gebruikersnaam;
    public Text wachtwoord;
    private UserAccountSingleton userAccounts = UserAccountSingleton.getInstance();


    @FXML
    private void initialize() {
        LanguageManager.addLanguageChangeListener(this::onLanguageChange);
    }

    @Override
    public void onLanguageChange() {
        updateUI();
    }

    private void updateUI() {
        // Update the Texts
        LanguageManager.getTranslation("title");
        title.setText(LanguageManager.getTranslation("LoginPageTitle"));
        welkom.setText(LanguageManager.getTranslation("welkom"));
        gebruikersnaam.setText(LanguageManager.getTranslation("username"));
        wachtwoord.setText(LanguageManager.getTranslation("password"));
        loginbutton.setText(LanguageManager.getTranslation("loginButton"));
        usernamefield.setPromptText(LanguageManager.getTranslation("usernameE"));
        passwordfield.setPromptText(LanguageManager.getTranslation("passwordE"));
        registerButton.setText(LanguageManager.getTranslation("createAccount"));


    }
    @FXML
    public void loginButtonEvent(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();


        if (LanguageManager.getTranslation("change").equals("Change")){
            if (userAccounts.UserPasswordCorrect(username, password)) {
                userAccounts.setCurrentUser(userAccounts.getUser(username));
                invalid.setText("");
                usernamefield.setText("");
                passwordfield.setText("");
                AssistentApplication.showChatScene();
            } else if (username.isEmpty() || password.isEmpty()) {
                invalid.setText("Please fill in all fields");
            } else {
                invalid.setText("");
                passwordfield.setText("");
                invalid.setText("The password or username is incorrect");
            }
        }
        else {

        if (userAccounts.UserPasswordCorrect(username, password)) {
            userAccounts.setCurrentUser(userAccounts.getUser(username));
            invalid.setText("");
            usernamefield.setText("");
            passwordfield.setText("");
            AssistentApplication.showChatScene();
        } else if (username.isEmpty() || password.isEmpty()) {
            invalid.setText("Voer alstublieft alle velden in");
        } else {
            invalid.setText("");
            passwordfield.setText("");
            invalid.setText("Het wachtwoord of de gebruikersnaam is incorrect");
        }}
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
    @FXML
    private void NL(ActionEvent event) {
        System.out.println("NL knop");
        LanguageManager.changeLanguage("nl");
    }
    @FXML
    private void EN(ActionEvent event) {
        System.out.println("EN button");
        LanguageManager.changeLanguage("en");
    }
}


