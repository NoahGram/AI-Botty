package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private VBox activeConversationBox;


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
    private void askQuestionClick() {
        submit();
    }

    public void askQuestionEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            submit();
        }
    }

    public void submit() {
        if (chatButtonsContainer.getChildren().isEmpty()) {
            addNewChatButton();
        }
        String question = questionField.getText();
        displayQuestion(question);
        Object answer = chatEngine.askQuestion(question);
        displayAnswer(answer);
        questionField.setText("");
    }

    private void displayQuestion(String question) {
        Text questionText = new Text(UserAccountSingleton.getInstance().getCurrentUser().getUsername() + ": " + question);
        questionText.setStyle("-fx-font-size: 16; -fx-fill: #ffffff; -fx-padding: 20;");
        questionText.setBlendMode(BlendMode.DIFFERENCE);
        activeConversationBox = (VBox) chatVBox.getChildren().get(0);
        activeConversationBox.getChildren().add(questionText);
    }

    private void displayAnswer(Object answer) {

        if (answer instanceof String) {
            Text answerText = new Text("AI Assistent: " + (String) answer);
            answerText.setBlendMode(BlendMode.DIFFERENCE);
            answerText.setStyle("-fx-font-size: 16; -fx-fill: #ffffff; -fx-padding: 20;");
            activeConversationBox.getChildren().add(answerText);
        }

        if (answer instanceof ImageView answerImage) {
            Text aiName = new Text("AI Assistent: ");
            aiName.setStyle("-fx-font-size: 16; -fx-fill: #ffffff; -fx-padding: 20;");
            aiName.setBlendMode(BlendMode.DIFFERENCE);
            VBox imageContainer = new VBox(answerImage);
            activeConversationBox.getChildren().addAll(aiName, imageContainer);
        }

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
        activeConversationBox.getChildren().clear();
        UserAccountSingleton.logOut();
    }
}
