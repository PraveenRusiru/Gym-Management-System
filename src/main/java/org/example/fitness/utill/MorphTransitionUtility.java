package org.example.fitness.utill;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
public class MorphTransitionUtility {
    /**
     * Loads a new FXML frame with a morph transition effect.
     *
     * @param pane      The Pane to apply the transition and load the new FXML.
     * @param fxmlPath  The path to the FXML file to be loaded.
     * @throws IOException If the FXML file cannot be loaded.
     */
    public static void loadFrameWithMorph(Pane pane, String fxmlPath) throws IOException {
        // Create a morph transition for the Pane
        Timeline morphTransition = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(pane.opacityProperty(), 1.0),
                        new KeyValue(pane.scaleXProperty(), 1.0),
                        new KeyValue(pane.scaleYProperty(), 1.0)
                ),
                new KeyFrame(Duration.seconds(1.0),
                        new KeyValue(pane.opacityProperty(), 0.0),
                        new KeyValue(pane.scaleXProperty(), 0.8),
                        new KeyValue(pane.scaleYProperty(), 0.8)
                )
        );

        // Set an event to load the new frame after the morph transition
        morphTransition.setOnFinished(event -> {
            try {
                // Load the new FXML into the Pane
                pane.getChildren().clear();
                pane.getChildren().add(FXMLLoader.load(MorphTransitionUtility.class.getResource(fxmlPath)));

                // Reset properties for reverse animation
                pane.setOpacity(0.0);
                pane.setScaleX(0.8);
                pane.setScaleY(0.8);

                // Animate back to normal appearance
                Timeline reverseTransition = new Timeline(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(pane.opacityProperty(), 0.0),
                                new KeyValue(pane.scaleXProperty(), 0.8),
                                new KeyValue(pane.scaleYProperty(), 0.8)
                        ),
                        new KeyFrame(Duration.seconds(1.0),
                                new KeyValue(pane.opacityProperty(), 1.0),
                                new KeyValue(pane.scaleXProperty(), 1.0),
                                new KeyValue(pane.scaleYProperty(), 1.0)
                        )
                );
                reverseTransition.play();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        });

        // Play the morph transition
        morphTransition.play();
    }
}
