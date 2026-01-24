package org.example.fitness.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.fitness.dto.ClientDto;
import org.example.fitness.dto.MembershipDto;
import org.example.fitness.model.ClientModel;
import org.example.fitness.model.MembershipModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class MembershipRowController {
    MembershipDto membershipDto;
    ClientDto clientDto;
    MembershipModel membershipModel=new MembershipModel();
    ClientModel clientModel=new ClientModel();
    CommonMethod commonMethod=new CommonMethod();
   static boolean isFromMembershipRowController=false;
    @FXML
    private Label clientIdLbl;

    @FXML
    private ImageView deleteBtn;

    @FXML
    private Label endDateLbl;

    @FXML
    private HBox hBoxContainer;

    @FXML
    private Label membershipIdLbl;

    @FXML
    private Label startdateIdLbl;

    @FXML
    private Label typeLbl;

    @FXML
    private ImageView updateBtn;

    @FXML
    private Label wholeSaleLbl;

    private VBox tableVBox;

    private Pane mainPane;

    @FXML
    void deleteAction(MouseEvent event) throws SQLException {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this row?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            if(membershipModel.deleteMembership(String.valueOf(membershipIdLbl.getText()))){
                tableVBox.getChildren().remove(hBoxContainer);
            }
        }
    }

    @FXML
    void updateAction(MouseEvent event) {
    isFromMembershipRowController=true;

    membershipDto=new MembershipDto();
    membershipDto.setMembershipId(membershipIdLbl.getText());
    membershipModel.setMembershipDto(membershipDto);

    clientDto=new ClientDto();
    clientDto.setClientId(clientIdLbl.getText());
    clientModel.setClientDto(clientDto);

        try {
            commonMethod.loadFrame(mainPane,"/view/Membership/AddNewMembership.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public void setData(String[] membershipData){
        membershipIdLbl.setText(membershipData[0]);
        clientIdLbl.setText(membershipData[1]);
        startdateIdLbl.setText(membershipData[2]);
        endDateLbl.setText(membershipData[3]);
        typeLbl.setText(membershipData[4]);
    }
    public void setComponent(VBox vBox, Pane pane){
        this.tableVBox = vBox;
        this.mainPane = pane;
    }
}
