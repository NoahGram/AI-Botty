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
            case "why is ann so thicc?" -> "uwu";
            default -> "Cannot find an answer to the question: " + question;
        };
    }

    public String formatDutchAnswer(String question) {
        return switch (question.toLowerCase()) {
            case "wat is het telefoonnummer van 42?" -> "Het telefoonnummer van 42 is 088 424 2042.";
            case "hoe eet ik een mango?" -> "Je kunt een mango als een banaan pellen. Pel de vrucht vanaf de top, " +
                    "eet vervolgens het deel van het vruchtvlees op dat vrij is gekomen van de schil.";
            case "wie zijn de leden van het projectgroepje?" -> "Noah, Anita, William, Tim, Jimmy en Nout.";
            case "waarom is ann zo thicc?" -> "uwu";
            default -> "Kan geen antwoord vinden op de vraag: " + question;
        };
    }
}
