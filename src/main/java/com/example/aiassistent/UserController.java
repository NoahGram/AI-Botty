package com.example.aiassistent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UserController {
    @FXML
    private void loginButtonEvent(ActionEvent event) {
        AssistentApplication.showChatScene();
    }

    @FXML
    private void registerButtonEvent(ActionEvent event) {
        AssistentApplication.showRegisterScene();
    }
}
