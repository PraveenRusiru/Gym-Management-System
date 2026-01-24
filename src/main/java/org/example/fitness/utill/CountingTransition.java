package org.example.fitness.utill;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class CountingTransition {

    private final Label label;
    private final int targetValue;
    private final int stepValue;
    private final int durationInMillis;

    public CountingTransition(Label label, int targetValue, int stepValue, int durationInMillis) {
        this.label = label;
        this.targetValue = targetValue;
        this.stepValue = stepValue;
        this.durationInMillis = durationInMillis;
    }

    /**
     * Starts the counting animation on the label.
     */
    public void start() {
        int totalSteps = targetValue / stepValue; // Calculate the number of steps
        Timeline timeline = new Timeline();
        timeline.setCycleCount(totalSteps);

        // Define KeyFrame for incrementing the label's value
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(durationInMillis / totalSteps), event -> {
            int currentValue = Integer.parseInt(label.getText());
            label.setText(String.valueOf(currentValue + stepValue));
        }));

        timeline.setOnFinished(event -> {
            // Ensure the final value is exactly the target value (in case of rounding issues)
            label.setText(String.valueOf(targetValue));
        });

        timeline.play();
    }
}