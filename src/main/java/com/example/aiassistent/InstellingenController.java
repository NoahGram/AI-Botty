package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InstellingenController extends BaseController  {
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXButton changeIcon;

    static UserAccountSingleton userAccount = UserAccountSingleton.getInstance();

    @FXML
    private VBox vboxLanguages;
    @FXML
    private Text email;
    public Text title;
    public Text password;
    public  Text username;
    public JFXButton back;
    public JFXButton changePassword;
    public JFXButton changeEmail;
    public JFXButton changeUsername;


    @Override
    protected void updateUI() {
        // Update the Texts
        title.setText(LanguageManager.getTranslation("settingsTitle"));
        email.setText(LanguageManager.getTranslation("emailU"));
        changePassword.setText(LanguageManager.getTranslation("change"));
        password.setText(LanguageManager.getTranslation("passwordU"));
        passwordField.setPromptText(LanguageManager.getTranslation("password"));
        username.setText(LanguageManager.getTranslation("usernameU"));
        back.setText(LanguageManager.getTranslation("back"));
        usernameField.setPromptText(LanguageManager.getTranslation("username"));
        changeEmail.setText(LanguageManager.getTranslation("change"));
        changeUsername.setText(LanguageManager.getTranslation("change"));

    }

    @FXML
    private void changeLanguageEN() {
        LanguageManager.changeLanguage("en");
    }

    @FXML
    private void changeLanguageNL() {
        LanguageManager.changeLanguage("nl");
    }

    public InstellingenController() {
        AssistentApplication.showSettingsScene();
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
            String username = userAccount.getCurrentUser().getUsername();
            String newEmail = emailField.getText();

            userAccount.editEmail(username, newEmail);

            emailField.clear();
        }
    }

    @FXML
    private void editPassword(ActionEvent event) {
        if (!passwordField.getText().isEmpty()) {
            String username = userAccount.getCurrentUser().getUsername();
            String newPassword = passwordField.getText();

            userAccount.editPassword(username, newPassword);

            passwordField.clear();
        }
    }

    @FXML
    private void editIcon(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.png"));

        // Create a File object for the source image file
        File uploadedImageFile = fileChooser.showOpenDialog(null);

        // Create a File object for the target image file
        File oldImageFile = new File("src/main/resources/com/example/aiassistent/images", "logoa.png");

        // Copy the source image file to the target location
        Files.copy(uploadedImageFile.toPath(), oldImageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        // Get the login XML file
        FXMLLoader loginXML = new FXMLLoader(getClass().getResource("login-view.fxml"));

        // Load the file
        Parent loadedXML = loginXML.load();

        // Set the loaded file as a scene
        Scene scene = new Scene(loadedXML);

        // Get the logo from the login scene
        ImageView loginSceneLogo = (ImageView) loadedXML.lookup("#logo");

        if(loginSceneLogo != null) {
            // Create an Image object from the target image file
            Image newLogo = new Image(uploadedImageFile.toURI().toString());

            // Set the Image object as the image for the logo ImageView
            loginSceneLogo.setImage(newLogo);

            // Replaces the current login scene with the updated one and adds the css file that the current login scene uses
            AssistentApplication.setLoginScene(scene);
        } else {
            System.out.println("Logo ImageView not found in the scene.");
        }
    }

    public void back(ActionEvent actionEvent) {
        AssistentApplication.showChatScene();
    }
    @FXML
    private void NL(ActionEvent event) {
        System.out.println("NL knop");
        LanguageManager.changeLanguage("nl");
    }
    @FXML
    private void EN(ActionEvent event) {
        System.out.println("EN button");
        LanguageManager.changeLanguage("en");
    }
}
