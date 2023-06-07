package com.example.aiassistent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AssistentApplication extends Application {
    private static Stage primaryStage;
    private static Scene loginScene;
    private static Scene registerScene;
    private static Scene chatScene;
    private static Scene settingsScene;

    public static String currentTheme = "dark.css";

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws IOException {
        AssistentApplication.primaryStage = primaryStage;

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
        chatScene = new Scene(chatRoot, 1920, 1080);
        settingsScene = new Scene(settingsRoot, 800, 600);

        // Load CSS
        loadCSS(loginScene, currentTheme);
        loadCSS(registerScene, currentTheme);
        loadCSS(chatScene, currentTheme);
        loadCSS(settingsScene, currentTheme);


        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Gilroy-ExtraBold.otf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/PlusJakartaSans-VariableFont_wght.ttf"), 13);

        // Set the initial scene
        primaryStage.setTitle("AI-Assistant");
        primaryStage.setScene(loginScene);
//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setResizable(true);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);

        // Make the window draggable
        loginRoot.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        loginRoot.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        registerRoot.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        registerRoot.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        chatRoot.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        chatRoot.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        settingsRoot.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        settingsRoot.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        primaryStage.show();
    }

    // Method to load the CSS, clear the current CSS first
    private static void loadCSS(Scene scene, String cssFile) {
        scene.getStylesheets().clear(); // Clear the existing stylesheets
        scene.getStylesheets().add(Objects.requireNonNull(AssistentApplication.class.getResource(cssFile)).toExternalForm());
    }

    // Change the theme for all scenes
    public static void changeTheme() {
        if (currentTheme.equals("light.css")) {
            loadCSS(loginScene, "dark.css");
            loadCSS(registerScene, "dark.css");
            loadCSS(chatScene, "dark.css");
            loadCSS(settingsScene, "dark.css");
            currentTheme = "dark.css";
            System.out.println("Current theme: " + currentTheme);
        } else {
            loadCSS(loginScene, "light.css");
            loadCSS(registerScene, "light.css");
            loadCSS(chatScene, "light.css");
            loadCSS(settingsScene, "light.css");
            currentTheme = "light.css";
            System.out.println("Current theme: " + currentTheme);
        }
    }

    // Switching between scenes
    public static void showLoginScene() {
        primaryStage.setScene(loginScene);
    }

    public static void showRegisterScene() {
        primaryStage.setScene(registerScene);
    }

    public static void showSettingsScene() {
        primaryStage.setScene(settingsScene);
    }

    public static void showChatScene() {
        primaryStage.setMaximized(true);
        primaryStage.setScene(chatScene);
    }

    public static void main(String[] args) {
        launch();
    }

}