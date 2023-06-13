package com.example.aiassistent;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Screen;
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
    public static boolean isEngels = true;


    @Override
    public void start(Stage primaryStage) throws IOException {
        AssistentApplication.primaryStage = primaryStage;

        loadScenes();
        loadFonts();

        setInitialScene();

        primaryStage.show();
    }

    private void loadScenes() throws IOException {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenHeight = screenBounds.getHeight();

        double taskbarHeight = screenHeight - screenBounds.getHeight();

        loginScene = loadScene("login-view.fxml", 800, 600);
        registerScene = loadScene("register-view.fxml", 800, 600);
        chatScene = loadScene("chat-view.fxml", 1920, 1080 - taskbarHeight);
        settingsScene = loadScene("instellingen-view.fxml", 800, 600);

        loadCSS(loginScene, currentTheme);
        loadCSS(registerScene, currentTheme);
        loadCSS(chatScene, currentTheme);
        loadCSS(settingsScene, currentTheme);

    }

    private Scene loadScene(String fxmlFile, double width, double height) throws IOException {
        FXMLLoader loader = new FXMLLoader(AssistentApplication.class.getResource(fxmlFile));
        Parent root = loader.load();
        return new Scene(root, width, height);
    }

    private void loadFonts() {
        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/Gilroy-ExtraBold.otf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/resources/fonts/PlusJakartaSans-VariableFont_wght.ttf"), 13);
    }

    private static void loadCSS(Scene scene, String cssFile) {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(AssistentApplication.class.getResource(cssFile)).toExternalForm());
    }

    private void setInitialScene() {
        primaryStage.setTitle("AI-Assistant");
        primaryStage.setScene(loginScene);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
    }

    public static void changeTheme() {
        if (currentTheme.equals("light.css")) {
            setTheme("dark.css");
        } else {
            setTheme("light.css");
        }
        System.out.println("Current theme: " + currentTheme);
    }
    public static void setLang(boolean switchToEnglish){if(switchToEnglish){isEngels = true;}else {isEngels = false;}}

    private static void setTheme(String theme) {
        loadCSS(loginScene, theme);
        loadCSS(registerScene, theme);
        loadCSS(chatScene, theme);
        loadCSS(settingsScene, theme);
        currentTheme = theme;
    }

    public static void showLoginScene() {
        primaryStage.setScene(loginScene);
    }

    public static void showRegisterScene() {
        primaryStage.setScene(registerScene);
    }

    public static void showSettingsScene() {
        primaryStage.setScene(settingsScene);
    }

    public static void showChatScene() { primaryStage.setScene(chatScene);  primaryStage.setMaximized(true); }

    public static void main(String[] args) {
        launch();
    }
}
