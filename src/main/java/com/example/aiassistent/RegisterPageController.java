package com.example.aiassistent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.HashMap;

public class RegisterPageController {
    @FXML public GridPane background;
    @FXML public TextField emailField;
    @FXML public TextField usernamefield;
    @FXML public TextField passwordfield;
    @FXML public Text invalid;
    public boolean isLightMode = false;
    UserAccountSingleton userAccounts = UserAccountSingleton.getInstance();

    @FXML
    public void registerButtonEvent(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        String email = emailField.getText();
        if (userAccounts.UserExists(username)) {
            invalid.setText("Gebruikersnaam is al genomen");
        } else if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            invalid.setText("Voer alstublieft alle velden in.");
        } else {
            userAccounts.AddUser(new User(username, password, email));
            AssistentApplication.showChatScene();
        }
    }

    @FXML
    private void loginPage(ActionEvent event) {
        AssistentApplication.showLoginScene();
        invalid.setText("");
    }

    @FXML
    private void onEnter(ActionEvent event) {
        registerButtonEvent(event);
    }

    @FXML
    private void settingPage(ActionEvent event) {
        AssistentApplication.showSettingScene();
        invalid.setText("");
    }

    @FXML
    private void changeTheme(ActionEvent event) {
        if (isLightMode) {
            background.getStylesheets().remove(getClass().getResource("registerLight.css").toExternalForm());
            background.getStylesheets().add(getClass().getResource("registerDark.css").toExternalForm());
            isLightMode = false;
        } else {
            background.getStylesheets().remove(getClass().getResource("registerDark.css").toExternalForm());
            background.getStylesheets().add(getClass().getResource("registerLight.css").toExternalForm());
            isLightMode = true;
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
}
