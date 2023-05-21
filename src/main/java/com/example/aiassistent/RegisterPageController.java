package com.example.aiassistent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.HashMap;

public class RegisterPageController {
    public Button loginbutton;
    public TextField usernamefield;
    public TextField passwordfield;

    UserLogin userLogin = UserLogin.getInstance();
    HashMap<String, String> map = userLogin.getUserMap();

    public void registerButtonEvent(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        if (map.containsKey(username)) {
            //errorField.setText("Username already taken.");
        } else {
            map.put(username, password);
            AssistentApplication.showChatScene();
        }
    }

    @FXML
    private void loginPage(ActionEvent event) {
        AssistentApplication.showLoginScene();
    }
}
