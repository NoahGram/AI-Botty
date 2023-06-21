package com.example.aiassistent;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private static ResourceBundle resourceBundle;
    private static List<LanguageChangeListener> languageChangeListeners = new ArrayList<>();

    public static void changeLanguage(String languageCode) {
        resourceBundle = ResourceBundle.getBundle("languages.messages", new Locale(languageCode));
        notifyLanguageChange();
    }

    public static String getTranslation(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (Exception e) {
            return "???" + key + "???";
        }
    }

    public static void addLanguageChangeListener(LanguageChangeListener listener) {
        languageChangeListeners.add(listener);
    }

    public static void removeLanguageChangeListener(LanguageChangeListener listener) {
        languageChangeListeners.remove(listener);
    }

    private static void notifyLanguageChange() {
        for (LanguageChangeListener listener : languageChangeListeners) {
            listener.onLanguageChange();
        }
    }
}

interface LanguageChangeListener {
    void onLanguageChange();
}
