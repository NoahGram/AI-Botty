package com.example.aiassistent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AssistentApplication extends Application {
    private static Stage primaryStage;
    private static Scene loginScene;
    private static Scene registerScene;
    private static Scene chatScene;
    private static Scene settingsScene;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        // Load the FXML files for the scenes
        FXMLLoader loginLoader = new FXMLLoader(AssistentApplication.class.getResource("login-view.fxml"));
        FXMLLoader registerLoader = new FXMLLoader(AssistentApplication.class.getResource("register-view.fxml"));
        FXMLLoader chatLoader = new FXMLLoader(AssistentApplication.class.getResource("chat-view.fxml"));
        FXMLLoader settingsLoader = new FXMLLoader(AssistentApplication.class.getResource("instellingen-view.fxml"));

        // Load the scene roots from the FXML files
        Parent loginRoot = loginLoader.load();
        Parent registerRoot = registerLoader.load();
        Parent chatRoot = chatLoader.load();
        Parent settingsRoot = settingsLoader.load();

        // Create the scenes
        loginScene = new Scene(loginRoot, 800, 600);
        registerScene = new Scene(registerRoot, 800, 600);
        chatScene = new Scene(chatRoot, 800, 600);
        settingsScene = new Scene(settingsRoot, 800, 600);

        // Load CSS
        settingsScene.getStylesheets().add(getClass().getResource("settings.css").toExternalForm());
        loginScene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        registerScene.getStylesheets().add(getClass().getResource("registerDark.css").toExternalForm());
        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Gilroy-ExtraBold.otf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/PlusJakartaSans-VariableFont_wght.ttf"), 13);

        // Set the initial scene
        primaryStage.setTitle("AI-Assistent");
        primaryStage.setScene(loginScene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        primaryStage.show();
    }

    // Methods to switch between scenes
    public static void showLoginScene() {
        primaryStage.setScene(loginScene);
    }
    public static void showRegisterScene() {
        primaryStage.setScene(registerScene);
    }
    public static void showSettingScene() {
        primaryStage.setScene(settingsScene);
    }
    public static void showChatScene() {
        primaryStage.setScene(chatScene);
    }

    public static void main(String[] args) {
        launch();
    }
}