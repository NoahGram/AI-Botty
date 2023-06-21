package com.example.aiassistent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Random;

class PictureMessage implements IQuestionAsker, IAnswerProvider {
    public ImageView askQuestion(String question) {
        return formatAnswer(question);
    }
    public ImageView askMeme(String question) {
        return formatMeme(question);
    }

    public ImageView formatAnswer(String question) {
        return new ImageView("https://media.licdn.com/dms/image/C4D0BAQHX1qomlcFEUA/company-logo_200_200/0/1548250566657?e=2147483647&v=beta&t=uXtCqDfNta4julyp-JYgX3X2n2pDgF8tesPnDZuGtk8");
    }

    public ImageView formatMeme(String question) {
        String folderPath = "src/main/resources/com/example/aiassistent/memes"; // Replace with the actual folder path

        File folder = new File(folderPath);

        // Check if the specified folder exists
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("Folder does not exist or is not a directory.");
            return null;
        }

        File[] imageFiles = folder.listFiles();

        // Check if the folder is empty or contains no image files
        if (imageFiles == null || imageFiles.length == 0) {
            System.out.println("Folder is empty or does not contain image files.");
            return null;
        }

        // Generate a random index within the range of available image files
        Random random = new Random();
        int randomIndex = random.nextInt(imageFiles.length);

        // Retrieve the randomly selected image file
        File randomImageFile = imageFiles[randomIndex];

        // Create an Image object with the random image file
        Image image = new Image(randomImageFile.toURI().toString());
        ImageView imageView = new ImageView(image);

        return imageView;
    }
}
