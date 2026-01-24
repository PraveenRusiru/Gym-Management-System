package org.example.fitness.utill;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;

public class BottomToTopTransitionUtility {

    /**
     * Loads a new FXML frame and applies a bottom-to-top transition for all elements in the Pane.
     *
     * @param pane     The Pane to load the new FXML into and apply the transition.
     * @param fxmlPath The path to the FXML file to be loaded.
     * @throws IOException If the FXML file cannot be loaded.
     */
    public static void loadFrameWithBottomToTopTransition(Pane pane, String fxmlPath) throws IOException {
        try {
            // Clear the Pane and load new content
            pane.getChildren().clear();
            Pane newContent = FXMLLoader.load(BottomToTopTransitionUtility.class.getResource(fxmlPath));
            pane.getChildren().add(newContent);

            // Apply bottom-to-top transition for all elements in the Pane
            for (Node node : newContent.getChildren()) {
                // Store the original position
                double originalY = node.getLayoutY();

                // Set the starting position below the Pane
                node.setTranslateY(pane.getHeight() - originalY);

                // Create a transition for the element
                TranslateTransition transition = new TranslateTransition(Duration.seconds(0.8), node);
                transition.setFromY(pane.getHeight() - originalY);
                transition.setToY(0);
                transition.setCycleCount(1);
                transition.setInterpolator(javafx.animation.Interpolator.EASE_OUT);

                // Play the transition
                transition.play();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}