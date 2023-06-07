package com.example.aiassistent;

interface IChatEngineStrategy {
    public String AskQuestion(String question);
}

class SimpleChatStrategy implements IChatEngineStrategy {
    public String AskQuestion(String question) {
        return "I have no clue what you mean with: " + question;
    }
}

class SimplePictureChatStrategy implements IChatEngineStrategy {
    public String AskQuestion(String question) {
        return "Nice Picture: " + question;
    }
}

class ChatGptStrategy implements IChatEngineStrategy {

    private ChatGptAdapter chatEngine;

    public String AskQuestion(String question) {
        chatEngine.AskQuestion(question);
        return question;
    }
}

class ChatGptAdapter {
    public String AskQuestion(String question) {
        return "This is a dummy answer to the question: " + question;
    }
}