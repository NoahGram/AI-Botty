package com.example.aiassistent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.util.HashMap;

public class LoginPageController {
    public Button loginbutton;
    public TextField usernamefield;
    public TextField passwordfield;
    public TextField errorField;

    private HashMap<String, String> userMap = new HashMap<>();

    public LoginPageController() {
        // Initialize the userMap with some sample data
        userMap.put("alice", "password1");
        userMap.put("bob", "password2");
        userMap.put("charlie", "password3");
    }

    @FXML
    private void loginButtonEvent(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        if (userMap.containsKey(username) && userMap.get(username).equals(password)) {
            AssistentApplication.showChatScene();
        } else {
            errorField.setText("Invalid username or password.");
        }
    }

    @FXML
    private void registerButtonEvent(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        if (userMap.containsKey(username)) {
            errorField.setText("Username already taken.");
        } else {
            userMap.put(username, password);
            AssistentApplication.showChatScene();
        }
    }
}


