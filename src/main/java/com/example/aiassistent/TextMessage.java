package com.example.aiassistent;

class TextMessage implements IQuestionAsker, IAnswerProvider {
    public String askQuestion(String question) {
        return formatAnswer(question);
    }

    public String formatAnswer(String question) {
        if (!question.endsWith("?")) {
            question = question + "?";
        }

        AnswerFormatter formatter = new AnswerFormatter();
        return formatter.formatAnswer(question);
    }
}

