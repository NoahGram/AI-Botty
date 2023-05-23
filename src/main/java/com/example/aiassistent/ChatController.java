package com.example.aiassistent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChatController {
    public Button backButton;
    @FXML
    private void backButtonEvent(ActionEvent event) {
        AssistentApplication.showLoginScene();
    }

    public void changeTheme(ActionEvent actionEvent) {
        AssistentApplication.changeTheme();
    }

    public void openSettings(ActionEvent actionEvent) {
    }

    public void logOut(ActionEvent actionEvent) {
        UserAccountSingleton.logOut();
    }
}
