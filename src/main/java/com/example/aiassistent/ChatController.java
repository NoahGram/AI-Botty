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
}
