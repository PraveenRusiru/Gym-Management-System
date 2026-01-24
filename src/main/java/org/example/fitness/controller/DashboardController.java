package org.example.fitness.controller;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.fitness.dto.ExpiringMemberDto;
import org.example.fitness.model.ExpiringMemberModel;
import org.example.fitness.utill.BottomToTopLayeredTransitionUtility;
import org.example.fitness.utill.BottomToTopTransitionUtility;
import org.example.fitness.utill.MorphTransitionUtility;
import org.example.fitness.utill.TopToBottomLayeredTransitionUtility;

import javax.naming.Binding;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {


    private final ExpiringMemberModel expiringMemberModel;
    CommonMethod commonMethod = new CommonMethod();
    public DashboardController() {
        expiringMemberModel = new ExpiringMemberModel();
    }

    @FXML
    private ImageView scheduleBtn;

    @FXML
    private ImageView membershipBtn;

    @FXML
    private ImageView clientBtn;

    @FXML
    private Pane mainPane;

    @FXML
    private ImageView homeBtn;

    @FXML
    private ImageView suppliementShopBtn;


    @FXML
    void navigateToClients(MouseEvent event) {
          try {
              TopToBottomLayeredTransitionUtility.loadFrameWithLayeredTransition(mainPane,"/view/Clients/Clients.fxml");
              //mainPane.getChildren().clear();
              //mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Clients/Clients.fxml")));
          } catch (Exception e) {
              e.printStackTrace();
              System.out.println(e.getMessage());
          }
    }
    @FXML
    void navigateToMembership(MouseEvent event) {
        try {
            TopToBottomLayeredTransitionUtility.loadFrameWithLayeredTransition(mainPane,"/view/Membership/Membership.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
//        try {
//            commonMethod.loadFrame(mainPane,"/view/Membership/Membership.fxml");
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
//        try {
//            // Create a morph transition for the mainPane
//            Timeline morphTransition = new Timeline(
//                    new KeyFrame(Duration.ZERO,
//                            new KeyValue(mainPane.opacityProperty(), 1.0),
//                            new KeyValue(mainPane.scaleXProperty(), 1.0),
//                            new KeyValue(mainPane.scaleYProperty(), 1.0)
//                    ),
//                    new KeyFrame(Duration.seconds(0.5),
//                            new KeyValue(mainPane.opacityProperty(), 0.0),
//                            new KeyValue(mainPane.scaleXProperty(), 0.8),
//                            new KeyValue(mainPane.scaleYProperty(), 0.8)
//                    )
//            );
//
//            // Set an event to load the new frame after the morph transition
//            morphTransition.setOnFinished(event1 -> {
//                try {
//                    commonMethod.loadFrame(mainPane, "/view/Membership/Membership.fxml");
//                    // Optional: Reset pane properties after loading new content
//                    mainPane.setOpacity(0.0);
//                    mainPane.setScaleX(0.8);
//                    mainPane.setScaleY(0.8);
//
//                    // Animate back to normal appearance
//                    Timeline reverseTransition = new Timeline(
//                            new KeyFrame(Duration.ZERO,
//                                    new KeyValue(mainPane.opacityProperty(), 0.0),
//                                    new KeyValue(mainPane.scaleXProperty(), 0.8),
//                                    new KeyValue(mainPane.scaleYProperty(), 0.8)
//                            ),
//                            new KeyFrame(Duration.seconds(0.5),
//                                    new KeyValue(mainPane.opacityProperty(), 1.0),
//                                    new KeyValue(mainPane.scaleXProperty(), 1.0),
//                                    new KeyValue(mainPane.scaleYProperty(), 1.0)
//                            )
//                    );
//                    reverseTransition.play();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    System.out.println(e.getMessage());
//                }
//            });
//
//            // Play the morph transition
//            morphTransition.play();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
    }
    @FXML
    void navigateHome(MouseEvent event) {
        try {
            //mainPane.getChildren().clear();
            //mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Dashboard/DashboardPane.fxml")));
            TopToBottomLayeredTransitionUtility.loadFrameWithLayeredTransition(mainPane,"/view/Dashboard/DashboardPane.fxml");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void supplimentOnAction(MouseEvent event) {
        try {
          // commonMethod.loadFrame(mainPane,"/view/Suppliment/Suppliment.fxml");
            TopToBottomLayeredTransitionUtility.loadFrameWithLayeredTransition(mainPane,"/view/Suppliment/Suppliment.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    @FXML
    void navigateToSchedule(MouseEvent event) {
        try {
            TopToBottomLayeredTransitionUtility.loadFrameWithLayeredTransition(mainPane,"/view/Shedule/SheduleMain.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            mainPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Dashboard/DashboardPane.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
