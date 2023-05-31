package com.example.aiassistent;

public interface IChatEngineStrategy {
    public String AskQuestion(String question);
}

public class SimpleChatStrategy implements IChatEngineStrategy {
    public String AskQuestion(String question) {
        return "I have no clue what you mean with: " + question;
    }
}

public class ChatGptStrategy implements IChatEngineStrategy {

    private ChatGptAdapter chatEngine;

    public String AskQuestion(String question) {
        chatEngine.AskQuestion(question);
    }
}

public class ChatGptAdapter {

    public String AskQuestion(String question) {
        return "This is a dummy answer to the question: " + question;
    }
}
