package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class InstellingenController {
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    UserAccountSingleton userAccount = UserAccountSingleton.getInstance();

    public InstellingenController() {
        AssistentApplication.showSettingsScene();
        String currentUsername = userAccount.getCurrentUser();
        User currentUser = userAccount.getUser(currentUsername);
        if (currentUser != null) {
            usernameField.setText(currentUser.getUsername());
            emailField.setText(currentUser.getEmail());
        }
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
            String username = userAccount.getCurrentUser();
            String newEmail = emailField.getText();

            userAccount.editEmail(username, newEmail);

            emailField.clear();
        }
    }

    @FXML
    private void editPassword(ActionEvent event) {
        if (!passwordField.getText().isEmpty()) {
            String username = userAccount.getCurrentUser();
            String newPassword = passwordField.getText();

            userAccount.editPassword(username, newPassword);

            passwordField.clear();
        }
    }

    public void back(ActionEvent actionEvent) {
        AssistentApplication.showChatScene();
    }
}
