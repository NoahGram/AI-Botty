package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class LoginPageController {
    private String TXT1 = "Welkom terug!";
    private String TXT2 = "Log in met jouw account";
    private String TXT3 = "gebruikersnaam";
    private String TXT4 = "wachtwoord";
    private String TXT5 = "Inloggen";
    private String TXT6 = "Maak een account";
    private String TXT7 = "Voer hier uw gebruikersnaam in";
    private String TXT8 = "Voer hier uw wachtwoord in";

    private String TXT1E = "Welcome back!";
    private String TXT2E = "Log in with your account";
    private String TXT3E = "username";
    private String TXT4E = "password";
    private String TXT5E = "Log in";
    private String TXT6E = "Make an account";
    private String TXT7E = "Enter username here";
    private String TXT8E = "Enter password here";

    @FXML
    public Text txt1Text;
    @FXML
    public Text title;
    @FXML
    public Text txt3Text;
    @FXML
    public Text txt4Text;

     public JFXButton loginbutton;
     public Button exitButton;
     public Button registerButton;
     public JFXTextField usernamefield;
     public JFXPasswordField passwordfield;
     public Text invalid;
     public Button theme;
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
            txt1Text.setText(TXT1E);
            title.setText(TXT2E);
            txt3Text.setText(TXT3E);
            txt4Text.setText(TXT4E);
            loginbutton.setText(TXT5E);
            registerButton.setText(TXT6E);
            usernamefield.setPromptText(TXT7E);
            passwordfield.setPromptText(TXT8E);
        }else{
            txt1Text.setText(TXT1);
            title.setText(TXT2);
            txt3Text.setText(TXT3);
            txt4Text.setText(TXT4);
            loginbutton.setText(TXT5);
            registerButton.setText(TXT6);
            usernamefield.setPromptText(TXT7);
            passwordfield.setPromptText(TXT8);
        }

    }
}


