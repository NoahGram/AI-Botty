package com.example.aiassistent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AssistentApplication extends Application {
    private static Stage primaryStage;
    private static Scene loginScene;
    private static Scene registerScene;
    private static Scene chatScene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        // Load the FXML files for the scenes
        FXMLLoader loginLoader = new FXMLLoader(AssistentApplication.class.getResource("login-view.fxml"));
        FXMLLoader registerLoader = new FXMLLoader(AssistentApplication.class.getResource("register-view.fxml"));
        FXMLLoader chatLoader = new FXMLLoader(AssistentApplication.class.getResource("chat-view.fxml"));

        // Load the scene roots from the FXML files
        Parent loginRoot = loginLoader.load();
        Parent registerRoot = registerLoader.load();
        Parent chatRoot = chatLoader.load();

        // Create the scenes
        loginScene = new Scene(loginRoot, 800, 600);
        registerScene = new Scene(registerRoot, 800, 600);
        chatScene = new Scene(chatRoot, 800, 600);

        // Set the initial scene
        primaryStage.setTitle("AI-Assistent");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    // Methods to switch between scenes
    public static void showLoginScene() {
        primaryStage.setScene(loginScene);
    }
    public static void showRegisterScene() {
        primaryStage.setScene(registerScene);
    }

    public static  void showChatScene() {
        primaryStage.setScene(chatScene);
    }

    public static void main(String[] args) {
        launch();
    }
}