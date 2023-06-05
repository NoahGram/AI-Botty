package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Popup;

import java.awt.*;
import java.net.URL;
import java.util.*;
import java.util.List;

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

        Button editButton = addNewEditButton(chatButton);
        HBox.setMargin(editButton, new Insets(0, 0, 0, -210));

        Button removeButton = addNewRemoveButton(chatButton);
        HBox.setMargin(removeButton, new Insets(0, 0, 0, -30));

        HBox chatButtonBox = new HBox(chatButton, removeButton, editButton);
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
        String currentTitle = chatButton.getText();
        HBox currentChatButtonBox = null;

        for (Node hbox : chatButtonsContainer.getChildren()) {
            if(hbox instanceof Parent) {
                HBox chatButtonBox = (HBox) hbox;
                for (Node element : chatButtonBox.getChildrenUnmodifiable()) {
                    if (element == chatButton) {
                        currentChatButtonBox = chatButtonBox;
                    }
                }
            }
        }

        int chatButtonIndex = chatButtonsContainer.getChildren().indexOf(currentChatButtonBox);

        HBox editTextInputField = addEditTextInputField(currentTitle);

        chatButtonsContainer.getChildren().add(chatButtonIndex + 1, editTextInputField);
    }

    private HBox addEditTextInputField(String currentTitle) {
        TextField textField = new TextField();
        textField.setPromptText(currentTitle);
        textField.setMaxWidth(200);
        textField.setPrefColumnCount(10);

        // Create the cancel button
        Button confirmChatButtonEdit = new JFXButton("");
        confirmChatButtonEdit.setOnAction(event -> editChatButtonTitle());
        confirmChatButtonEdit.setPadding(new Insets(10));
        confirmChatButtonEdit.getStyleClass().add("confirmEditButton");

        // Create the remove button
        Button cancelChatButtonEdit = new JFXButton("");
        cancelChatButtonEdit.setOnAction(event -> cancelChatButtonEdit());
        cancelChatButtonEdit.setPadding(new Insets(10));
        cancelChatButtonEdit.getStyleClass().add("cancelEditButton");

        return new HBox(textField, confirmChatButtonEdit, cancelChatButtonEdit);
    }

    private void editChatButtonTitle() {
        // TODO: actually update the text in the button
    }

    private void cancelChatButtonEdit() {
        // TODO: cancel the update action and remove the input field
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
