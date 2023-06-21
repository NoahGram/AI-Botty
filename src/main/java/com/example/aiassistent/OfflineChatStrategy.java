package com.example.aiassistent;

class OfflineChatStrategy implements IQuestionAsker {
    public Object askQuestion(String question) {
        PictureMessage pictureMessage = new PictureMessage();
        if(question.contains("logo van 42") || question.contains("logo of 42")) {
            return pictureMessage.askQuestion(question);
        } else if (question.contains("meme")) {
            return pictureMessage.askMeme(question);
        }
        else {
            TextMessage textMessage = new TextMessage();
            return textMessage.askQuestion(question);
        }
    }
}
