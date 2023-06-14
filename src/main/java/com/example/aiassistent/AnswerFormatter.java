package com.example.aiassistent;

class AnswerFormatter implements IAnswerProvider {
    public String formatAnswer(String question) {
        if (WordCheck.containsEnglishWord(question)) {
            return formatEnglishAnswer(question);
        } else {
            return formatDutchAnswer(question);
        }
    }

    public String formatEnglishAnswer(String question) {
        return switch (question.toLowerCase()) {
            case "what is the phone number of 42?" -> "The phone number of 42 is 088 424 2042.";
            case "how do i eat a mango?" -> "You can peel a mango like a banana. Peel the fruit from the top, " +
                    "then eat the portion of the flesh that has been freed from the skin.";
            case "who are the members of the project group?" -> "Noah, Anita, William, Tim, Jimmy, and Nout.";
            case "what are we proud of?" -> "We are proud of the graphical interface and the ability to provide an image as an answer to a question. We are also very proud of the possibility of answering questions in English.";
            default -> "Cannot find an answer to the question: " + question;
        };
    }

    public String formatDutchAnswer(String question) {
        return switch (question.toLowerCase()) {
            case "wat is het telefoonnummer van 42?" -> "Het telefoonnummer van 42 is 088 424 2042.";
            case "hoe eet ik een mango?" -> "Je kunt een mango als een banaan pellen. Pel de vrucht vanaf de top, " +
                    "eet vervolgens het deel van het vruchtvlees op dat vrij is gekomen van de schil.";
            case "wie zijn de leden van het projectgroepje?" -> "Noah, Anita, William, Tim, Jimmy en Nout.";
            case "waar zijn wij trots op?" -> "Wij zijn trots op de grafische interface en de mogelijkheid om een afbeelding als antwoord te geven op een vraag. Ook het beantwoorden van vragen in het engels.";
            default -> "Kan geen antwoord vinden op de vraag: " + question;
        };
    }
}
