package com.example.aiassistent;
import javafx.scene.image.ImageView;

class PictureMessage implements IQuestionAsker, IAnswerProvider {
    public ImageView askQuestion(String question) {
        return formatAnswer(question);
    }

    public ImageView formatAnswer(String question) {
        return new ImageView("https://media.licdn.com/dms/image/C4D0BAQHX1qomlcFEUA/company-logo_200_200/0/1548250566657?e=2147483647&v=beta&t=uXtCqDfNta4julyp-JYgX3X2n2pDgF8tesPnDZuGtk8");
    }
}
