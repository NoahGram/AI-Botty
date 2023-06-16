package com.example.aiassistent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;

public abstract class BaseController implements LanguageChangeListener {
    @FXML
    protected Text title;
    @FXML
    protected Button backButton;
    // Add other common UI elements here

    @FXML
    private void initialize() {
        LanguageManager.addLanguageChangeListener(this::onLanguageChange);
    }

    @Override
    public void onLanguageChange() {
        updateUI();
    }

    protected abstract void updateUI();

    @FXML
    protected void changeTheme(ActionEvent event) {
        AssistentApplication.changeTheme();
    }

    // Add other common event handlers here
}