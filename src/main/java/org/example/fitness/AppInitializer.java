package org.example.fitness;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//        try {
//            // Load the loading view
//            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoadingView/LoadingView.fxml"))));
//            stage.show();
//
//            // Create a background task to load the main scene
//            Task<Scene> loadingTask = new Task<>() {
//                @Override
//                protected Scene call() throws Exception {
//                    try {
//                        // Load the main layout (LoginView) from FXML
//                        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/Login/LoginView.fxml"));
//                        return new Scene(fxmlLoader.load());
//                    } catch (Exception e) {
//                        e.printStackTrace(); // Log the exception
//                        throw new RuntimeException("Failed to load the main layout.", e);
//                    }
//                }
//            };
//
//            // What to do when loading is successful
//            loadingTask.setOnSucceeded(event -> {
//                Scene value = loadingTask.getValue();
//
//                stage.setTitle("Login");
//                stage.setMaximized(true);
//
//                // Switch to the main scene
//                stage.setScene(value);
//            });
//
//            // What to do in case of loading failure
//            loadingTask.setOnFailed(event -> {
//                Throwable exception = loadingTask.getException();
//                System.err.println("Failed to load the main layout: " + exception.getMessage());
//                exception.printStackTrace();  // Print stack trace to understand the issue
//            });
//
//            // Start the task in a separate thread
//            new Thread(loadingTask).start();
//
//        } catch (Exception e) {
//            e.printStackTrace();  // Handle any issues with setting up the loading screen
//            System.err.println("Error initializing the app: " + e.getMessage());
//        }

        FXMLLoader fxmlLoader = new FXMLLoader(AppInitializer.class.getResource("/view/Dashboard/Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Supermarket FX");

       // Image image = new Image(getClass().getResourceAsStream("/images/app_icon.png"));
        //stage.getIcons().add(image);

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
