package org.example.fitness.utill;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;

public class BottomToTopLayeredTransitionUtility {

    /**
     * Loads a new FXML frame and applies a bottom-to-top layered transition for all elements in the Pane.
     *
     * @param pane     The Pane to load the new FXML into and apply the transition.
     * @param fxmlPath The path to the FXML file to be loaded.
     * @throws IOException If the FXML file cannot be loaded.
     */
    public static void loadFrameWithLayeredTransition(Pane pane, String fxmlPath) throws IOException {
        try {
            // Clear the Pane and load new content
            pane.getChildren().clear();
            Pane newContent = FXMLLoader.load(BottomToTopLayeredTransitionUtility.class.getResource(fxmlPath));
            pane.getChildren().add(newContent);

            // Apply bottom-to-top layered transition for all elements in the Pane
            double delay = 0; // Start with no delay
            for (Node node : newContent.getChildren()) {
                // Store the original position
                double originalY = node.getLayoutY();

                // Set the starting position below the Pane
                node.setTranslateY(pane.getHeight() - originalY);

                // Create a transition for the element
                TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), node);
                transition.setFromY(pane.getHeight() - originalY);
                transition.setToY(0);
                transition.setCycleCount(1);
                transition.setInterpolator(javafx.animation.Interpolator.EASE_OUT);

                // Add delay for the layered effect
                transition.setDelay(Duration.seconds(delay));
                delay += 0.3; // Increment delay for the next node

                // Play the transition
                transition.play();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}