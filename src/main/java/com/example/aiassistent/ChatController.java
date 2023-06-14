package com.example.aiassistent;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.scene.layout.HBox ;
import javafx.scene.layout.StackPane;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

import java.io.InputStream;
import java.util.*;
import java.util.function.UnaryOperator;

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
        chatButton.setStyle("-fx-padding: 0 0 0 15; -fx-alignment: baseline-left;");

        chatButton.setMinWidth(100);
        chatButton.setPrefWidth(200);

        Button editButton = addNewEditButton(chatButton);
        Button removeButton = addNewRemoveButton(chatButton);

        StackPane chatButtonStack = new StackPane(chatButton, removeButton, editButton);
        StackPane.setAlignment(chatButton, Pos.CENTER_LEFT);
        StackPane.setAlignment(removeButton, Pos.TOP_RIGHT);
        StackPane.setAlignment(editButton, Pos.TOP_RIGHT);
        StackPane.setMargin(editButton, new Insets(0, 40, 0, 0));

        Insets buttonMargin = new Insets(0, 0, 20, 0);
        HBox.setMargin(chatButton, buttonMargin);

        VBox conversationBox = new VBox();
        conversationBox.setPadding(new Insets(10));
        conversationBox.setFillWidth(true);

        chatButtons.add(chatButton);
        chatConversations.put(chatButton, conversationBox);
        chatButton.setOnAction(event -> switchChat(chatButton));
        chatButtonsContainer.getChildren().add(chatButtonStack);
        switchChat(chatButton);
    }

    @FXML
    private Button addNewEditButton(Button chatButton) {
        // initialize objects
        InputStream is = getClass().getResourceAsStream("images/stylus_edit.png");
        Image img = new Image(is);
        ColorAdjust colorAdjust = new ColorAdjust();
        ImageView view = new ImageView(img);

        // Change image visual properties
        colorAdjust.setBrightness(0.9);
        view.setFitHeight(20);
        view.setFitWidth(20);
        view.setBlendMode(BlendMode.DIFFERENCE);
        view.setEffect(colorAdjust);

        Button editButton = new JFXButton("");
        editButton.setOnAction(event -> editChatButton(chatButton));
        editButton.setGraphic(view);
        editButton.setPadding(new Insets(10));
        editButton.getStyleClass().add("editButton");
        return editButton;
    }

    private Button addNewRemoveButton(Button chatButton) {
        // Declare initial objects for later use
        InputStream is = getClass().getResourceAsStream("images/cross.png");
        Image img = new Image(is);
        ColorAdjust colorAdjust = new ColorAdjust();
        ImageView view = new ImageView(img);

        // Change image visual properties
        colorAdjust.setBrightness(0.9);
        view.setFitHeight(20);
        view.setFitWidth(20);
        view.setBlendMode(BlendMode.DIFFERENCE);
        view.setEffect(colorAdjust);
        // Create the remove button
        Button removeButton = new JFXButton("");
        removeButton.setOnAction(event -> removeChatButton(chatButton));
        removeButton.setPadding(new Insets(10));
        removeButton.setGraphic(view);
        return removeButton;
    }

    private void editChatButton(Button chatButton) {
        TextInputDialog dialog = new TextInputDialog(chatButton.getText());
        dialog.setTitle("Titel chat");
        dialog.setHeaderText(null);
        dialog.setContentText("Voer nieuwe titel in: ");

        // Set the maximum character limit to 10
        UnaryOperator<Change> characterFilter = change -> {
            int maxLength = 13;
            if (change.getControlNewText().length() <= maxLength) {
                return change;
            }
            return null;
        };

        dialog.getEditor().setTextFormatter(new TextFormatter<>(characterFilter));

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
        chatVBox.getChildren().clear();
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
