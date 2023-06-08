package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.*;

import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import java.util.*;

public class ChatController {
    public JFXButton askButton;
    public GridPane background;
    public Button exitButton;
    public Button theme;
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

    private IChatEngineStrategy chatEngine = new SimpleChatStrategy();


    @FXML
    private void initialize() {
        askButton.setDisable(true);
        newChatButton.setOnAction(event -> addNewChatButton());
        addNewChatButton();

        // Checks if questionField has text, if not disable the send question button, otherwise enable.
        questionField.textProperty().addListener((observable, oldValue, newValue) -> askButton.setDisable(newValue.trim().isEmpty()));

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
        if (chatButtonsContainer.getChildren().isEmpty()) {
            addNewChatButton();
        }
        String question = questionField.getText();
        displayQuestion(question);
        String answer = chatEngine.AskQuestion(question);
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

    private void addNewChatButton() {
        Button chatButton = new JFXButton("Chat");

        chatButton.setPadding(new Insets(10));
        chatButton.getStyleClass().add("chatButton");
        chatButton.setTextFill(Paint.valueOf("#ffffff"));
        chatButton.setMinWidth(100);
        chatButton.setPrefWidth(200);

        HBox chatButtonBox = new HBox(chatButton);
        chatButtonBox.setAlignment(Pos.CENTER);
        chatButtonBox.setSpacing(10);

        // Set margin for bottom only
        Insets buttonMargin = new Insets(0, 0, 20, 0);
        HBox.setMargin(chatButton, buttonMargin);

        VBox conversationBox = new VBox();
        conversationBox.setPadding(new Insets(10));
        conversationBox.setFillWidth(true);

        chatButtons.add(chatButton);
        chatConversations.put(chatButton, conversationBox);
        chatButton.setOnAction(event -> switchChat(chatButton));
        chatButtonsContainer.getChildren().add(chatButtonBox);
    }

    public void changeTheme(ActionEvent actionEvent) {
        AssistentApplication.changeTheme();
    }

    public void openSettings(ActionEvent actionEvent) {
        AssistentApplication.showSettingsScene();
    }

    public void logOut(ActionEvent actionEvent) {
        chatButtonsContainer.getChildren().clear();
        UserAccountSingleton.logOut();
    }
}
