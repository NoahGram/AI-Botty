package com.example.aiassistent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UserController {
    public Button loginbutton;
    public TextField usernamefield;
    public TextField passwordfield;

    @FXML
    private void loginButtonEvent(ActionEvent event) {
        AssistentApplication.showChatScene();
    }

    @FXML
    private void registerButtonEvent(ActionEvent event) {
        AssistentApplication.showRegisterScene();
    }
}
