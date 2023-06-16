package com.example.aiassistent;

import javafx.fxml.FXML;

public abstract class BaseController implements LanguageChangeListener {
    @FXML
    private void initialize() {
        LanguageManager.addLanguageChangeListener(this);
    }

    @Override
    public void onLanguageChange() {
        updateUI();
    }

    protected abstract void updateUI();

    @FXML
    protected void changeTheme() {
        AssistentApplication.changeTheme();
    }

    @FXML
    private void NL() {
        System.out.println("NL knop");
        LanguageManager.changeLanguage("nl");
    }
    @FXML
    private void EN() {
        System.out.println("EN button");
        LanguageManager.changeLanguage("en");
    }
}