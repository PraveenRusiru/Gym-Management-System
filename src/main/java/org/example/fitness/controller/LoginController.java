package org.example.fitness.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.example.fitness.utill.TopToBottomLayeredTransitionUtility;

import java.io.IOException;

public class LoginController {
    CommonMethod commonMethod = new CommonMethod();
    @FXML
    public  Pane BackgroundLoginPane;

    @FXML
    private Label ForgetPwLbl;

    @FXML
    public AnchorPane mainAnchorPane;

    public void initialize() throws IOException {
        commonMethod.loadFrame(BackgroundLoginPane,"/view/Login/LoginPane.fxml");

    }
    @FXML
    void navigateForgetPw(MouseEvent event) throws IOException {
        commonMethod.loadFrame(BackgroundLoginPane,"/view/Login/ForgetPasswordPane.fxml");
//        BackgroundLoginPane.getChildren().clear();
//        BackgroundLoginPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Login/ForgetPasswordPane.fxml")));
    }



}

