package com.example.aiassistent;

import javafx.scene.image.ImageView;

interface IChatEngineStrategy {
    Object askQuestion(String question);
}

class SimpleChatStrategy implements IChatEngineStrategy {

    public Object askQuestion(String question) {
        if (question.contains("logo van 42")) {
            PictureMessage pictureMessage = new PictureMessage();
            return pictureMessage.askQuestion(question);
        } else {
            TextMessage textMessage = new TextMessage();
            return textMessage.askQuestion(question);
        }
    }
}

class PictureMessage implements IChatEngineStrategy {
    public ImageView askQuestion(String question) {
        return formatAnswer(question);
    }

    public ImageView formatAnswer(String question) {

        ImageView image = new ImageView("https://media.licdn.com/dms/image/C4D0BAQHX1qomlcFEUA/company-logo_200_200/0/1548250566657?e=2147483647&v=beta&t=uXtCqDfNta4julyp-JYgX3X2n2pDgF8tesPnDZuGtk8");
        return image;
    }
}

class TextMessage implements IChatEngineStrategy {
    String answer;
    public String askQuestion(String question) {
        answer = formatAnswer(question);
        return this.answer;
    }

    public String formatAnswer(String question) {
        if (!question.endsWith("?")) {
            question = question + "?";
        }

        return switch (question) {
            case "wat is het telefoonnummer van 42?" -> "Het telefoonnummer van 42 is 088 424 2042.";
            case "hoe eet ik een mango?" -> "Je kunt een mango als een banaan pellen. Pel de vrucht vanaf de top, " +
                    "eet vervolgens het deel van het vruchtvlees op dat vrij is gekomen van de schil.";
            case "wie zijn de leden van het projectgroepje?" -> "Noah, Anita, William, Tim, Jimmy en Nout.";

            default -> "kan geen antwoord vinden op de vraag " + question;
        };
    }
}

class ChatGptStrategy implements IChatEngineStrategy {
    private ChatGptAdapter chatEngine;

    public Object askQuestion(String question) {
        chatEngine.askQuestion(question);
        return question;
    }

    public String prepareAnswer(String question) {
        return null;
    }
}

class ChatGptAdapter {
    public String askQuestion(String question) {
        return "This is a dummy answer to the question: " + question;
    }
}
