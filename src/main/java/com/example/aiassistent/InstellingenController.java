package com.example.aiassistent;

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
    @FXML
    private Button btnEnglish;

    @FXML
    private Button btnDutch;

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
        String emailTranslate = LanguageManager.getTranslation("email");
        String emailTranslate1 = LanguageManager.getTranslation("email2");
        emailField.setText(emailTranslate);
        email.setText(emailTranslate);
        title.setText(LanguageManager.getTranslation("settingsTitle"));

        // Update the language buttons' text
        btnEnglish.setText(LanguageManager.getTranslation("english"));
        btnDutch.setText(LanguageManager.getTranslation("dutch"));
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
}
