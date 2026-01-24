package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import org.example.fitness.dto.ClientDto;
import org.example.fitness.dto.MembershipDto;
import org.example.fitness.model.ClientModel;
import org.example.fitness.model.MembershipModel;
import org.example.fitness.utill.TextFieldManager;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddNewMembershipController implements Initializable {

    private MembershipModel membershipModel;
    private MembershipDto membershipDto;
    private TextFieldManager textFieldManager;
    private CommonMethod commonMethod;
    static boolean isFromAddNewMembership = false;
    private ClientDto clientDto;
    private ClientModel clientModel;
    public AddNewMembershipController(){
        membershipModel = new MembershipModel();
        textFieldManager = new TextFieldManager();
        commonMethod = new CommonMethod();
        clientDto = new ClientDto();
        clientModel = new ClientModel();
    }
    @FXML
    private JFXButton addBtn;

    @FXML
    private Pane addMembershipPane;

    @FXML
    private Pane applicationPane;

    @FXML
    private JFXButton backMembershipBtn;

    @FXML
    private Label clientIdLbl;

    @FXML
    private Label clientLbl;

    @FXML
    private JFXButton doneBtn;

    @FXML
    private RadioButton fitFocusRB;

    @FXML
    private RadioButton flexFitRB;

    @FXML
    private RadioButton introFitRB;

    @FXML
    private Label memberIdLabel;

    @FXML
    private ToggleGroup membershipType;

    @FXML
    private JFXTextField nicTxt;

    @FXML
    private RadioButton strrengthRB;

    @FXML
    private JFXButton updateBtn;


    @FXML
    void membershipOnAction(ActionEvent event) {
       if(!clientIdLbl.getText().equals(" ")){
           int monthCount=0;
           if(introFitRB.isSelected()) {
               ServiceClientController.txt=introFitRB.getText();
               monthCount=3;
               ServiceClientController.packagePrice=3000;
           }else if(fitFocusRB.isSelected()) {
               ServiceClientController.txt=fitFocusRB.getText();
               monthCount=6;
               ServiceClientController.packagePrice=10000;
           }else if(strrengthRB.isSelected()) {
               ServiceClientController.txt=strrengthRB.getText();
               monthCount=12;
               ServiceClientController.packagePrice=20000;
           } else if (flexFitRB.isSelected()) {
               ServiceClientController.txt=flexFitRB.getText();
               monthCount=1;
               ServiceClientController.packagePrice=1000;
           }
           // System.out.println(clientContactModel.getClientContactDto().getClientId());
           System.out.println("Selected selection :"+ServiceClientController.txt);
           membershipDto=new MembershipDto(memberIdLabel.getText(), Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now().plusMonths(monthCount)),ServiceClientController.txt);
           clientDto.setClientId(clientIdLbl.getText());
       }else{
           new Alert(Alert.AlertType.ERROR,"Please select a client ").show();
       }
    }

    @FXML
    void backToMembership(ActionEvent event) {
        if(!ServiceClientController.txt.equals("")){
            membershipModel.setMembershipDto(membershipDto);
            clientModel.setClientDto(clientDto);
            try {
                commonMethod.loadFrame(applicationPane,"/view/Payment/Payment.fxml");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Please select a membership type").show();
        }
    }



    @FXML
    void navigateToMembershipList(ActionEvent event) {

    }

    @FXML
    void navigateToPayment(ActionEvent event) {
        if(!clientIdLbl.getText().equals(" ") && !ServiceClientController.txt.equals("")) {
            membershipModel.setMembershipDto(membershipDto);
            clientModel.setClientDto(clientDto);
            try {
                commonMethod.loadFrame(applicationPane,"/view/Payment/Payment.fxml");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Please select a client & choose a membership type").show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isFromAddNewMembership = true;
        if(MembershipRowController.isFromMembershipRowController){
            nicTxt.setVisible(false);
            updateBtn.setVisible(true);
            addBtn.setVisible(false);

            memberIdLabel.setText(membershipModel.getMembershipDto().getMembershipId());
            clientIdLbl.setText(clientModel.getClientDto().getClientId());

        }else{
            textFieldManager.setLblAndTxt(nicTxt,clientIdLbl);
            updateBtn.setVisible(false);
            //nicTxt.setOnKeyPressed(this::);
            try {
                memberIdLabel.setText(MembershipModel.getNextMembershipId());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

    }
}
