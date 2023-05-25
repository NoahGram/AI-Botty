package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController {

    public Button settings;

    @FXML
    public JFXButton newChatButton;
    public ColumnConstraints tabs;
    public ScrollPane chatScreen;
    public VBox chatVBox;

    @FXML
    private VBox chatButtonsContainer;
    @FXML
    private TextField questionField;

    @FXML
    private Button askButton;


    @FXML
    private void initialize() {
        // Add event handler to the "Nieuwe chat" button
        newChatButton.setOnAction(event -> addNewChatButton());
        addNewChatButton();
    }

    @FXML
    private void askQuestion() {
        String question = questionField.getText();
        displayQuestion(question);
        String answer = generateAnswer(question);
        displayAnswer(answer);
    }

    private void displayQuestion(String question) {
        Text questionText = new Text(question);
        questionText.setStyle("-fx-font-size: 16; -fx-fill: #333333;"); // Apply CSS styles
        VBox questionBox = new VBox(questionText);
        questionBox.setPadding(new Insets(10)); // Add padding
        chatVBox.getChildren().add(questionBox);
    }

    private void displayAnswer(String answer) {
        Text answerText = new Text(answer);
        answerText.setStyle("-fx-font-size: 14; -fx-fill: #666666;"); // Apply CSS styles
        VBox answerBox = new VBox(answerText);
        answerBox.setPadding(new Insets(10)); // Add padding
        chatVBox.getChildren().add(answerBox);

        // Add space between the question and answer blocks
        HBox.setMargin(answerBox, new Insets(10, 0, 0, 0));
    }


    private String generateAnswer(String question) {
        // Generate a dummy answer based on the user's input
        // Implement your own logic here
        return "This is a dummy answer to the question: " + question;
    }


    @FXML
    private void addNewChatButton() {
        HBox chatButtonBox = new HBox();
        chatButtonBox.setSpacing(10);
        chatButtonBox.setAlignment(Pos.CENTER_LEFT);

        Button chatButton = new JFXButton("Chat " + (chatButtonsContainer.getChildren().size() + 1));
        chatButton.setPadding(new Insets(10));
        chatButton.getStyleClass().add("chatButton");
        chatButton.setTextFill(Paint.valueOf("#ffffff"));

        Button removeButton = new Button("X");
        removeButton.getStyleClass().add("removeButton");
        removeButton.setBackground(Background.EMPTY);
        removeButton.setTextFill(Paint.valueOf("#FFFFFF"));
        removeButton.setOnAction(event -> removeChatButton(chatButtonBox));

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(chatButton, removeButton);
        StackPane.setAlignment(removeButton, Pos.TOP_RIGHT);
        StackPane.setMargin(removeButton, new Insets(10));

        chatButtonBox.getChildren().add(stackPane);

        chatButtonsContainer.getChildren().add(chatButtonBox);
    }

    private void removeChatButton(HBox chatButtonBox) {
        chatButtonsContainer.getChildren().remove(chatButtonBox);
    }




    public void changeTheme(ActionEvent actionEvent) {
        AssistentApplication.changeTheme();
    }

    public void openSettings(ActionEvent actionEvent) {
    }

    public void logOut(ActionEvent actionEvent) {
        chatButtonsContainer.getChildren().clear();
        UserAccountSingleton.logOut();
    }
}
