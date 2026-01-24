package org.example.fitness.utill;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;

public class TopToBottomLayeredTransitionUtility {

    /**
     * Loads a new FXML frame and applies a top-to-bottom layered transition for all elements in the Pane.
     *
     * @param pane     The Pane to load the new FXML into and apply the transition.
     * @param fxmlPath The path to the FXML file to be loaded.
     * @throws IOException If the FXML file cannot be loaded.
     */
    public static void loadFrameWithLayeredTransition(Pane pane, String fxmlPath) throws IOException {
        try {
            // Clear the Pane and load new content
            pane.getChildren().clear();
            Pane newContent = FXMLLoader.load(TopToBottomLayeredTransitionUtility.class.getResource(fxmlPath));
            pane.getChildren().add(newContent);

            // Apply top-to-bottom layered transition for all elements in the Pane
            int nodeCount = newContent.getChildren().size();
            double delay = 0; // Start delay for the first node
            for (int i = 0; i < nodeCount; i++) {
                Node node = newContent.getChildren().get(i);

                // Store the original position
                double originalY = node.getLayoutY();

                // Set the starting position below the Pane
                node.setTranslateY(pane.getHeight() - originalY);

                // Create a transition for the element
                TranslateTransition transition = new TranslateTransition(Duration.seconds(0.7), node);
                transition.setFromY(pane.getHeight() - originalY);
                transition.setToY(0);
                transition.setCycleCount(1);
                transition.setInterpolator(javafx.animation.Interpolator.EASE_OUT);

                // Calculate delay for reverse-layer effect
                transition.setDelay(Duration.seconds((nodeCount - 1 - i) * 0.1)); // Reverse order of delay

                // Play the transition
                transition.play();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}