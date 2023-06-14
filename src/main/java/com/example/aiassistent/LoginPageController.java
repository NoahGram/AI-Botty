package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class LoginPageController implements LanguageChangeListener {

     public JFXButton loginbutton;
     public Button exitButton;
     public Button registerButton;
     public JFXTextField usernamefield;
     public JFXPasswordField passwordfield;
     public Text invalid;
     public Button theme;
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
        gebruikersnaam.setText(LanguageManager.getTranslation("username"));
        wachtwoord.setText(LanguageManager.getTranslation("password"));
        loginbutton.setText(LanguageManager.getTranslation("loginButton"));

    }
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


