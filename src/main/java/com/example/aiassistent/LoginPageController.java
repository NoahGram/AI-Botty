package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class LoginPageController {

    @FXML
    public JFXButton loginbutton;

    @FXML
    public Button registerButton;

    @FXML
    public Button settingButton;

    @FXML
    public JFXTextField usernamefield;

    @FXML
    public JFXPasswordField passwordfield;

    //public TextField errorField;

    UserAccountSingleton userAccounts = UserAccountSingleton.getInstance();

    public void initialize(URL url, ResourceBundle rb) {
        usernamefield.setStyle("-fx-text-inner-color: #BA55D3;");
    }

    @FXML
    public void loginButtonEvent(ActionEvent event) {
        String username = usernamefield.getText();
        String password = passwordfield.getText();

        if (userAccounts.UserPasswordCorrect(username, password)) {
            AssistentApplication.showChatScene();
        } else {
            //errorField.setText("Invalid username or password.");
        }
    }

    @FXML
    private void registerPage(ActionEvent event) {
        AssistentApplication.showRegisterScene();
    }
  
    @FXML
    private void settingPage(ActionEvent event) {
        AssistentApplication.showSettingScene();
    }
}


