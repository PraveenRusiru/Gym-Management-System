package org.example.fitness.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import org.example.fitness.dto.ExpiringMemberDto;
import org.example.fitness.model.ExpiringMemberModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ExpiringMemberController{
    private final ExpiringMemberModel expiringMemberModel;
    public ExpiringMemberController() {
        expiringMemberModel = new ExpiringMemberModel();
    }
    @FXML
    private Label enddateLbl;

    @FXML

    private Label memberIdLbl;

    @FXML
    private Label memberTypeLbl;

    @FXML
    private Label nameLbl;



    @FXML
    void selectionClick(MouseEvent event) {
        System.out.println("Member type :"+memberTypeLbl.getText() +" Name :"+nameLbl.getText()+"EndDate :"+enddateLbl.getText());
    }
    public void setData(ExpiringMemberDto expiringMemberDto) {
            memberTypeLbl.setText(expiringMemberDto.getMemberType());
            nameLbl.setText(expiringMemberDto.getName());
            enddateLbl.setText(expiringMemberDto.getExpiringDate());
        memberIdLbl.setText(expiringMemberDto.getId());
    }

}
