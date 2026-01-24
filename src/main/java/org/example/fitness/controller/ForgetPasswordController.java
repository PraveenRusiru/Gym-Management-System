package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.example.fitness.utill.SendMailUtill;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

public class ForgetPasswordController implements Initializable {
    String to="";
    String from="";
    String subject="";
    String body="";
     String code="";
     String typedCode="";
    CommonMethod commonMethod = new CommonMethod();
    @FXML
    private JFXButton confirmBtn;

    @FXML
    private JFXTextField codeTxt;

    @FXML
    private ImageView backBtn;

    @FXML
    private Pane forgetPasswordPane;

    @FXML
    void codeKeyPressed(KeyEvent event) {

    }

    @FXML
    void navigateLogin(MouseEvent event) throws IOException {
        commonMethod.loadFrame(forgetPasswordPane,"/view/Login/LoginPane.fxml");
//        forgetPasswordPane.getChildren().clear();
//        forgetPasswordPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Login/LoginPane.fxml")));
    }
    @FXML
    void navigateToNewPassword(ActionEvent event) {
        typedCode = codeTxt.getText();
        System.out.println(typedCode);
          if(code.equals(typedCode)){
                new Alert(Alert.AlertType.INFORMATION,"Password Recovered !");
              try {
                  commonMethod.loadFrame(forgetPasswordPane,"/view/Login/SetNewPassword.fxml");
              } catch (IOException e) {
                  e.printStackTrace();
                  System.out.println(e.getMessage());
              }
          }else{
              new Alert(Alert.AlertType.ERROR,"Entered Code doesn't match !!");
          }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SendMailUtill sendMailUtill = new SendMailUtill();
        loadMailDetails();
        sendMailUtill.sendEmailWithGmail(from,to,subject,body);
    }
    private void loadMailDetails(){
        Random rand = new Random();
        code = String.valueOf(rand.nextInt(99999));
        from="praveenrusiru752@gmail.com";
        to="praveenrusiru031@gmail.com";
        subject="Recovering Password";
        body="Your standalone application password reset code : \n\n\n\n\t\t\t\t\t\t"+code;
    }

}
