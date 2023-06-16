package com.example.aiassistent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public abstract class BaseController implements LanguageChangeListener {
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
    protected void changeTheme(ActionEvent actionEvent) {
        AssistentApplication.changeTheme();
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