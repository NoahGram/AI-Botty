package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class InstellingenController {
    @FXML
    private JFXTextField emailField;
    @FXML
    private JFXTextField usernameField;
    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXButton changeIcon;

    static UserAccountSingleton userAccount = UserAccountSingleton.getInstance();

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
        File sourceImageFile = fileChooser.showOpenDialog(null);

        // Specify the target directory where you want to replace the image
        String targetDirectoryPath = "src/main/resources/com/example/aiassistent/images";

        // Specify the target file name
        String targetFileName = "logoa.png";

        // Create a File object for the target directory
        File targetDirectory = new File(targetDirectoryPath);

        // Create a File object for the target image file
        File targetImageFile = new File(targetDirectory, targetFileName);

        // Copy the source image file to the target location
        Files.copy(sourceImageFile.toPath(), targetImageFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

        // TODO - File gets replaced but app is not aware, look into file cache invalidation or something
    }

    public void back(ActionEvent actionEvent) {
        AssistentApplication.showChatScene();
    }
}
