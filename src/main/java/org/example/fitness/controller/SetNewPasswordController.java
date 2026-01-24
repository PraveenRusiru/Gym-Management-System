package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.example.fitness.model.AdminModel;
import org.example.fitness.utill.TextFieldManager;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SetNewPasswordController implements Initializable {


    String firstPw="";
   String secondPw="";
   AdminModel adminModel=new AdminModel();
   CommonMethod commonMethod=new CommonMethod();
    TextFieldManager textFieldManager=new TextFieldManager();
   @FXML
    private JFXButton backToLoginBtn;

    @FXML
    private Pane changePasswordPane;

    @FXML
    private JFXTextField enterAgainPassword;

    @FXML
    private JFXTextField newPasswordTxt;

    @FXML
    void enterAgainPasswordKeyPressed(KeyEvent event) throws SQLException, IOException {
            }

    @FXML
    void navigateToLogin(ActionEvent event) throws SQLException, IOException {
        secondPw=enterAgainPassword.getText();
        firstPw=newPasswordTxt.getText();
        System.out.println("firstPw "+firstPw+" secondPw "+secondPw);
        if(firstPw.equals(secondPw)) {
            boolean isUpdated=adminModel.isPasswordUpdated(firstPw);
            if(isUpdated) {

                commonMethod.loadFrame(changePasswordPane,"/view/Login/LoginPane.fxml");
            }
        }

    }

    @FXML
    void newPasswordKeyboardType(KeyEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textFieldManager.setupFieldforJtoJTxt(newPasswordTxt,enterAgainPassword);
    }
}
