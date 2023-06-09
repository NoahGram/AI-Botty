package com.example.aiassistent;

class ChatGptStrategy implements IQuestionAsker {
    private final ChatGptAdapter chatEngine;

    public ChatGptStrategy(ChatGptAdapter chatEngine) {
        this.chatEngine = chatEngine;
    }

    public Object askQuestion(String question) {
        chatEngine.askQuestion(question);
        return question;
    }
}

class ChatGptAdapter {
    public String askQuestion(String question) {
        return "This is a dummy answer to the question: " + question;
    }
}
