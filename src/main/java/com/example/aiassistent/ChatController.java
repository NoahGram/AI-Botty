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
import java.util.*;

public class ChatController {
    private List<Button> chatButtons = new ArrayList<>();
    private Map<Button, VBox> chatConversations = new HashMap<>();

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
        newChatButton.setOnAction(event -> addNewChatButton());
        addNewChatButton();

        // Set the initial active chat
        Button initialChatButton = chatButtons.get(0);
        switchChat(initialChatButton);
    }

    private void switchChat(Button chatButton) {
        chatVBox.getChildren().clear();

        VBox conversationBox = chatConversations.get(chatButton);
        chatVBox.getChildren().add(conversationBox);
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
        questionText.setStyle("-fx-font-size: 16; -fx-fill: #333333;");

        VBox activeConversationBox = (VBox) chatVBox.getChildren().get(0);
        activeConversationBox.getChildren().add(questionText);
    }

    private void displayAnswer(String answer) {
        Text answerText = new Text(answer);
        answerText.setStyle("-fx-font-size: 14; -fx-fill: #666666;");

        VBox activeConversationBox = (VBox) chatVBox.getChildren().get(0);
        activeConversationBox.getChildren().add(answerText);

        // Add space between the question and answer blocks
        VBox.setMargin(answerText, new Insets(10, 0, 0, 0));
    }



    private String generateAnswer(String question) {
        // Generate a dummy answer based on the user's input
        // Implement your own logic here
        return "This is a dummy answer to the question: " + question;
    }


    @FXML
    private void addNewChatButton() {
        int chatNumber = chatButtons.size() + 1;

        Button chatButton = new JFXButton("Chat " + chatNumber);
        chatButton.setPadding(new Insets(10));
        chatButton.getStyleClass().add("chatButton");
        chatButton.setTextFill(Paint.valueOf("#ffffff"));

        // Create the remove button
        Button removeButton = new JFXButton("Remove");
        removeButton.setOnAction(event -> removeChatButton(chatButton));

        HBox chatButtonBox = new HBox(chatButton, removeButton);
        chatButtonBox.setAlignment(Pos.CENTER);
        chatButtonBox.setSpacing(10);

        VBox conversationBox = new VBox();
        conversationBox.setPadding(new Insets(10));
        conversationBox.setFillWidth(true);

        chatButtons.add(chatButton);
        chatConversations.put(chatButton, conversationBox);

        chatButton.setOnAction(event -> switchChat(chatButton));

        chatButtonsContainer.getChildren().add(chatButtonBox);
    }


    private void removeChatButton(Button chatButton) {
        VBox conversationBox = chatConversations.get(chatButton);
        chatButtons.remove(chatButton);
        chatConversations.remove(chatButton);

        chatButtonsContainer.getChildren().removeIf(node -> node instanceof HBox &&
                ((HBox) node).getChildren().contains(chatButton));

        chatVBox.getChildren().remove(conversationBox);
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
