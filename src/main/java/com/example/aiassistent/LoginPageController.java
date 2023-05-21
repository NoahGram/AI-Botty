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
    //public TextField errorField;

    UserLogin userLogin = UserLogin.getInstance();
    HashMap<String, String> map = userLogin.getUserMap();

    public LoginPageController() {
        // Initialize the userMap with some sample data
        map.put("noah", "password1");
        map.put("tim", "password2");
        map.put("anita", "password3");
    }

    @FXML
    public void loginButtonEvent(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        if (map.containsKey(username) && map.get(username).equals(password)) {
            AssistentApplication.showChatScene();
        } else {
            //errorField.setText("Invalid username or password.");
        }
    }

    @FXML
    private void registerPage(ActionEvent event) {
        AssistentApplication.showRegisterScene();
    }

}


