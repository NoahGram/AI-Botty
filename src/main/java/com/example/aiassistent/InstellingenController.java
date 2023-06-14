package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InstellingenController implements LanguageChangeListener {
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    static UserAccountSingleton userAccount = UserAccountSingleton.getInstance();

    @FXML
    private VBox vboxLanguages;
    @FXML
    private Text email;
    public Text title;
    public Text password;
    public  Text username;
    public JFXButton back;
    public JFXButton changePassword;
    public JFXButton changeEmail;
    public JFXButton changeUsername;


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
        title.setText(LanguageManager.getTranslation("settingsTitle"));
        email.setText(LanguageManager.getTranslation("emailU"));
        changePassword.setText(LanguageManager.getTranslation("change"));
        password.setText(LanguageManager.getTranslation("passwordU"));
        passwordField.setPromptText(LanguageManager.getTranslation("password"));
        username.setText(LanguageManager.getTranslation("usernameU"));
        back.setText(LanguageManager.getTranslation("back"));
        usernameField.setPromptText(LanguageManager.getTranslation("username"));
        changeEmail.setText(LanguageManager.getTranslation("change"));
        changeUsername.setText(LanguageManager.getTranslation("change"));

    }

    @FXML
    private void changeLanguageEN() {
        LanguageManager.changeLanguage("en");
    }

    @FXML
    private void changeLanguageNL() {
        LanguageManager.changeLanguage("nl");
    }

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
        System.out.println("NL knop");
        LanguageManager.changeLanguage("nl");
    }
    @FXML
    private void EN(ActionEvent event) {
        System.out.println("EN button");
        LanguageManager.changeLanguage("en");
    }
}
