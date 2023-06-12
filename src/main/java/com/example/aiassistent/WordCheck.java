package com.example.aiassistent;

public class WordCheck {
    public static boolean containsEnglishWord(String question) {
        String[] englishWords = {
                "what",
                "has",
                "why",
                "can",
                "who",
                "how"
        };

        for (String word : englishWords) {
            if (question.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
