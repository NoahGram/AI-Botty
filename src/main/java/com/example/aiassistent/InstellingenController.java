package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class InstellingenController {
    private String TXT1 = "Instellingen";
    private String TXT2 = "Wijzig";
    private String TXT3 = "Verander uw e-mailadres";
    private String TXT4 = "Verander uw gebruikersnaam";
    private String TXT5 = "Verander uw wachtwoord";
    private String TXT6 = "Terug";
    private String TXT7 = "Gebruikersnaam";

    private String TXT1E = "Settings";
    private String TXT2E = "Change";
    private String TXT3E = "Change your e-mailadres";
    private String TXT4E = "Change your username";
    private String TXT5E = "Change your password";
    private String TXT6E = "Back";
    private String TXT7E = "Username";

    @FXML
    public Text title;
    @FXML
    public JFXButton back;

    @FXML
    public JFXButton changeEmail;
    @FXML
    public JFXButton changeUsername;
    @FXML
    public JFXButton changePassword;
    @FXML
    public Text txt4Text;
    @FXML
    public Text txt3Text;
    @FXML
    public Text txt5Text;
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    static UserAccountSingleton userAccount = UserAccountSingleton.getInstance();

    public InstellingenController() {
        AssistentApplication.showSettingsScene();
    }

    @FXML
    private void changeTheme(ActionEvent event) {
        AssistentApplication.changeTheme();
    }

    @FXML
    private void editUsername(ActionEvent event) {
        if (!usernameField.getText().isEmpty()) {
            String newUsername = usernameField.getText();

            userAccount.editUsername(newUsername);

            usernameField.clear();
            usernameField.setText(newUsername);
        }
    }

    @FXML
    private void editEmail(ActionEvent event) {
        if (!emailField.getText().isEmpty()) {
            String username = userAccount.getCurrentUser().getUsername();
            String newEmail = emailField.getText();

            userAccount.editEmail(username, newEmail);

            emailField.clear();
        }
    }

    @FXML
    private void editPassword(ActionEvent event) {
        if (!passwordField.getText().isEmpty()) {
            String username = userAccount.getCurrentUser().getUsername();
            String newPassword = passwordField.getText();

            userAccount.editPassword(username, newPassword);

            passwordField.clear();
        }
    }

    public void back(ActionEvent actionEvent) {
        AssistentApplication.showChatScene();
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
            changeEmail.setText(TXT2E);
            changeUsername.setText(TXT2E);
            changePassword.setText(TXT2E);
            txt3Text.setText(TXT3E);
            txt4Text.setText(TXT4E);
            txt5Text.setText(TXT5E);
            back.setText(TXT6E);
            usernameField.setText(TXT7E);
        }else{
            title.setText(TXT1);
            changeEmail.setText(TXT2);
            changeUsername.setText(TXT2);
            changePassword.setText(TXT2);
            txt3Text.setText(TXT3);
            txt4Text.setText(TXT4);
            txt5Text.setText(TXT5);
            back.setText(TXT6);
            usernameField.setText(TXT7);
        }
    }
}
