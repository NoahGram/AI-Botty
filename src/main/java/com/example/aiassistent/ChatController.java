package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.application.Platform;

import java.util.*;

public class ChatController {
    @FXML
    private JFXButton askButton;
    @FXML
    private List<Button> chatButtons = new ArrayList<>();
    @FXML
    private Map<Button, VBox> chatConversations = new HashMap<>();
    @FXML
    private JFXButton newChatButton;
    @FXML
    private ScrollPane chatScreen;
    @FXML
    private VBox chatVBox;
    @FXML
    private VBox chatButtonsContainer;
    @FXML
    private TextField questionField;

    private final IQuestionAsker chatEngine = new OfflineChatStrategy();
    private VBox activeConversationBox;

    @FXML
    private void initialize() {
        askButton.setDisable(true);
        newChatButton.setOnAction(event -> addNewChatButton());
        addNewChatButton();

        questionField.textProperty().addListener((observable, oldValue, newValue) -> askButton.setDisable(newValue.trim().isEmpty()));

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

    @FXML
    private void askQuestionEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            submit();
        }
    }

    private void submit() {
        if (chatButtonsContainer.getChildren().isEmpty()) {
            addNewChatButton();
        }
        String question = questionField.getText();
        displayQuestion(question);
        Object answer = chatEngine.askQuestion(question);
        displayAnswer(answer);

        questionField.setText("");

        Platform.runLater(() -> chatScreen.setVvalue(1.0));
    }

    private void displayQuestion(String question) {
        String username = UserAccountSingleton.getInstance().getCurrentUser().getUsername();
        String firstLetter = username.substring(0, 1);

        Text questionText = new Text(question);
        questionText.setFill(Color.WHITE);
        questionText.setStyle("-fx-font-size: 16; -fx-padding: 10px;");
        questionText.setBlendMode(BlendMode.DIFFERENCE);

        Rectangle square = new Rectangle(40, 40);
        square.setFill(Color.rgb(43, 45, 49));
        square.setArcWidth(10);
        square.setArcHeight(10);

        Text usernameText = new Text(firstLetter.toUpperCase());
        usernameText.setFill(Color.WHITE);
        usernameText.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

        StackPane usernameContainer = new StackPane();
        usernameContainer.getChildren().addAll(square, usernameText);

        HBox hbox = new HBox(usernameContainer, questionText);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(40, 20, 20, 20));
        hbox.setAlignment(Pos.CENTER_LEFT);

        StackPane stackPane = new StackPane(hbox);
        stackPane.setAlignment(Pos.CENTER_LEFT);
        stackPane.setPadding(new Insets(10));
        activeConversationBox = (VBox) chatVBox.getChildren().get(0);
        activeConversationBox.getChildren().add(stackPane);

    }

    private void displayAnswer(Object answer) {
        StackPane stackPane = new StackPane();
        stackPane.setId("answer-pane");

        if (answer instanceof String) {
            Text answerText = new Text((String) answer);

            answerText.setFill(Color.WHITE);
            answerText.setStyle("-fx-font-size: 16; -fx-padding: 10px;");
            answerText.setBlendMode(BlendMode.DIFFERENCE);

            Rectangle square = new Rectangle(40, 40);
            square.setFill(Color.valueOf("#19C37D"));
            square.setArcWidth(10);
            square.setArcHeight(10);

            Text aiText = new Text("AI");
            aiText.setFill(Color.WHITE);
            aiText.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

            StackPane aiContainer = new StackPane();
            aiContainer.getChildren().addAll(square, aiText);

            HBox hbox = new HBox(aiContainer, answerText);
            hbox.setAlignment(Pos.CENTER_LEFT); // Center the text next to the rectangle
            hbox.setSpacing(10);
            hbox.setPadding(new Insets(20));

            StackPane.setMargin(aiText, new Insets(10));
            stackPane.getChildren().addAll(hbox);

        }


        if (answer instanceof ImageView answerImage) {

            Rectangle square = new Rectangle(40, 40);
            square.setFill(Color.valueOf("#19C37D"));
            square.setArcWidth(10);
            square.setArcHeight(10);

            Text aiText = new Text("AI");
            aiText.setFill(Color.WHITE);
            aiText.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

            StackPane aiContainer = new StackPane();
            aiContainer.getChildren().addAll(square, aiText);

            HBox hbox = new HBox(aiContainer, answerImage);
            hbox.setSpacing(10);
            hbox.setPadding(new Insets(20));

            StackPane.setMargin(aiText, new Insets(10));
            stackPane.getChildren().addAll(hbox);
        }

        stackPane.setAlignment(Pos.CENTER_LEFT);
        stackPane.setPadding(new Insets(10));
        activeConversationBox.getChildren().add(stackPane);
    }

    @FXML
    private void addNewChatButton() {
        Button chatButton = new JFXButton("Chat");

        chatButton.setPadding(new Insets(10));
        chatButton.getStyleClass().add("chatButton");
        chatButton.setTextFill(Paint.valueOf("#ffffff"));
        chatButton.setMinWidth(100);
        chatButton.setPrefWidth(200);

        Button editButton = addNewEditButton(chatButton);
        Button removeButton = addNewRemoveButton(chatButton);

        StackPane chatButtonStack = new StackPane(chatButton, editButton, removeButton);
        StackPane.setAlignment(removeButton, Pos.TOP_RIGHT);
        StackPane.setAlignment(editButton, Pos.TOP_LEFT);

        Insets buttonMargin = new Insets(0, 0, 20, 0);
        HBox.setMargin(chatButton, buttonMargin);

        VBox conversationBox = new VBox();
        conversationBox.setPadding(new Insets(10));
        conversationBox.setFillWidth(true);

        chatButtons.add(chatButton);
        chatConversations.put(chatButton, conversationBox);
        chatButton.setOnAction(event -> switchChat(chatButton));
        chatButtonsContainer.getChildren().add(chatButtonStack);
    }

    private Button addNewEditButton(Button chatButton) {
        // Create the remove button
        Button editButton = new JFXButton("");
        editButton.setOnAction(event -> editChatButton(chatButton));
        editButton.setPadding(new Insets(10));
        editButton.getStyleClass().add("editButton");
        return editButton;
    }

    private Button addNewRemoveButton(Button chatButton) {
        // Create the remove button
        Button removeButton = new JFXButton("");
        removeButton.setOnAction(event -> removeChatButton(chatButton));
        removeButton.setPadding(new Insets(10));
        removeButton.getStyleClass().add("removeButton");
        return removeButton;
    }

    private void editChatButton(Button chatButton) {
        TextInputDialog dialog = new TextInputDialog(chatButton.getText());
        dialog.setTitle("Edit Chat Button");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter the new title:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(newTitle -> {
            chatButton.setText(newTitle);
        });
    }

    private void removeChatButton(Button chatButton) {
        StackPane chatButtonStack = (StackPane) chatButton.getParent();
        chatButtonsContainer.getChildren().remove(chatButtonStack);
        chatButtons.remove(chatButton);
        chatConversations.remove(chatButton);
    }

    @FXML
    private void changeTheme(ActionEvent actionEvent) {
        AssistentApplication.changeTheme();
    }

    @FXML
    private void openSettings(ActionEvent actionEvent) {
        AssistentApplication.showSettingsScene();
    }

    @FXML
    private void logOut(ActionEvent actionEvent) {
        if (!chatButtonsContainer.getChildren().isEmpty() || (activeConversationBox != null && !activeConversationBox.getChildren().isEmpty())) {
            chatButtonsContainer.getChildren().clear();
            if (activeConversationBox != null) {
                activeConversationBox.getChildren().clear();
            }
        }

        UserAccountSingleton.logOut();
    }
}
