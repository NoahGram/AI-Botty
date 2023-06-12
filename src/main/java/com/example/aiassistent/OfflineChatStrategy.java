package com.example.aiassistent;

class OfflineChatStrategy implements IQuestionAsker {
    public Object askQuestion(String question) {
        if(question.contains("logo van 42") || question.contains("logo of 42")) {
            PictureMessage pictureMessage = new PictureMessage();
            return pictureMessage.askQuestion(question);
        }
        else {
            TextMessage textMessage = new TextMessage();
            return textMessage.askQuestion(question);
        }
    }
}
