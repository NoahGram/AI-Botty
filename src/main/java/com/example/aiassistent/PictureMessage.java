package com.example.aiassistent;
import javafx.scene.image.ImageView;

class PictureMessage implements IQuestionAsker, IAnswerProvider {
    public ImageView askQuestion(String question) {
        return formatAnswer(question);
    }

    public ImageView formatAnswer(String question) {
        if (question.contains("project requirements")) {
            return new ImageView("https://i.ibb.co/JcW2K1d/Screenshot-1.jpg");
        }

        return new ImageView("https://media.licdn.com/dms/image/C4D0BAQHX1qomlcFEUA/company-logo_200_200/0/1548250566657?e=2147483647&v=beta&t=uXtCqDfNta4julyp-JYgX3X2n2pDgF8tesPnDZuGtk8");
    }
}
