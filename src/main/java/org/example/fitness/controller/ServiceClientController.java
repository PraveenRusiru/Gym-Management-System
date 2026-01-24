package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import org.example.fitness.dto.ClientDto;
import org.example.fitness.dto.ClientNoteDto;
import org.example.fitness.dto.MembershipDto;
import org.example.fitness.model.ClientContactModel;
import org.example.fitness.model.ClientModel;
import org.example.fitness.model.MembershipModel;
import org.example.fitness.model.NoteForClientModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ServiceClientController implements Initializable {

    CommonMethod commonMethod;
    static String txt="";
    static double packagePrice=0;
    static String shedules="";
    MembershipDto membershipDto;
    private MembershipModel membershipModel;
    private ClientNoteDto clientNoteDto;
    private NoteForClientModel noteForClientModel;
    public ServiceClientController(){
        commonMethod = new CommonMethod();
        membershipModel = new MembershipModel();
        clientNoteDto = new ClientNoteDto();
        noteForClientModel = new NoteForClientModel();
    }
    @FXML
    private Label memberIdLabel;
    @FXML
    private JFXButton createPrgrmBtn;
    @FXML
    private Pane servicePane;

    @FXML
    private JFXButton doneBtn;

    @FXML
    private RadioButton fitFocusRB;

    @FXML
    private RadioButton flexFitRB;

    @FXML
    private RadioButton introFitRB;

    @FXML
    private ToggleGroup membershipType;

    @FXML
    private RadioButton strrengthRB;

    @FXML
    private JFXButton paymentBtn;


    @FXML
    private JFXTextArea noteAre;

    @FXML
    void membershipOnAction(ActionEvent event) {
            int monthCount=0;
        if(introFitRB.isSelected()) {
            txt=introFitRB.getText();
            monthCount=3;
            packagePrice=3000;
        }else if(fitFocusRB.isSelected()) {
            txt=fitFocusRB.getText();
            monthCount=6;
            packagePrice=10000;
        }else if(strrengthRB.isSelected()) {
            txt=strrengthRB.getText();
            monthCount=12;
            packagePrice=20000;
        } else if (flexFitRB.isSelected()) {
            txt=flexFitRB.getText();
            monthCount=1;
            packagePrice=1000;
        }
       // System.out.println(clientContactModel.getClientContactDto().getClientId());
        System.out.println("Selected selection :"+txt);
        membershipDto=new MembershipDto(memberIdLabel.getText(), Date.valueOf(LocalDate.now()),Date.valueOf(LocalDate.now().plusMonths(monthCount)),txt);
    }
    @FXML
    void navigateToShedule(ActionEvent event) throws IOException, SQLException {
       // shedules="Workout & Nutration Plan";
        if(noteAre.getText().isEmpty()){
           new Alert(Alert.AlertType.ERROR,"Note Ares is Empty").show();
        }else{
            clientNoteDto=new ClientNoteDto(
                    NoteForClientModel.getNextNoteId(),noteAre.getText(),AddNewClientsController.clientId);
            if(clientNoteDto!=null) {
                noteForClientModel.setClientNoteDto(clientNoteDto);
            }
            if(membershipDto!=null){
                membershipModel.setMembershipDto(membershipDto);
            }
            shedules="Workout & Nutration Plan";//this is crucial
            commonMethod.loadFrame(servicePane,"/view/Shedule/Sheule.fxml");
        }

    }
    @FXML
    void paymentOnAction(ActionEvent event) throws IOException {
        //if(membershipType.){}

        membershipModel.setMembershipDto(membershipDto);
        commonMethod.loadFrame(servicePane,"/view/Payment/Payment.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            memberIdLabel.setText(MembershipModel.getNextMembershipId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
