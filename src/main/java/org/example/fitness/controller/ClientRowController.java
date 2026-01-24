package org.example.fitness.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.fitness.dto.ClientContactDto;
import org.example.fitness.dto.ClientDto;
import org.example.fitness.model.ClientContactModel;
import org.example.fitness.model.ClientModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientRowController implements Initializable {

    ClientModel clientModel=new ClientModel();
    ClientContactModel clientContactModel=new ClientContactModel();
    static boolean isFromClientRowController=false;
    ClientDto clientDto;
    ClientContactDto clientContactDto;
    @FXML
    private Label AgeLbl;

    @FXML
    private ImageView addServiceBtn;

    @FXML
    private Label clientIdLbl;

    @FXML
    private ImageView deleteBtn;

    @FXML
    private ImageView detailUpdateBtn;

    @FXML
    private Label genderLbl;

    @FXML
    private Label goalLbl;

    @FXML
    private Label joinedDateLbl;

    @FXML
    private Label mailLbl;

    @FXML
    private Label nameLbl;

    @FXML
    private Label numberLbl;

    @FXML
    private Label heightLbl;

    @FXML
    private Label fatperLbl;

    @FXML
    private Label nicLbl;

    @FXML
    private HBox hboxContainer;
    @FXML
    private Label weightLbl;

    private VBox tableVBox;
    private Pane mainPaneClients;
    @FXML
    void addServiceAction(MouseEvent event) {

    }

    @FXML
    void deleteAction(MouseEvent event) throws SQLException {

        if(clientModel.deleteClient(clientIdLbl.getText()) && tableVBox!=null){
            tableVBox.getChildren().remove(hboxContainer);
        }

    }

    @FXML
    void detailUpdateAction(MouseEvent event) throws IOException {
        isFromClientRowController=true;
        clientDto=new ClientDto(clientIdLbl.getText(),nameLbl.getText(),Integer.parseInt(AgeLbl.getText()),genderLbl.getText(),goalLbl.getText(),Integer.parseInt(heightLbl.getText()),Integer.parseInt(weightLbl.getText()),Double.parseDouble(fatperLbl.getText()), Date.valueOf(joinedDateLbl.getText()));
        clientContactDto=new ClientContactDto(nicLbl.getText(),numberLbl.getText(),mailLbl.getText(),clientIdLbl.getText());
        clientModel.setClientDto(clientDto);
        clientContactModel.setClientContactDto(clientContactDto);

        mainPaneClients.getChildren().clear();
        mainPaneClients.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Clients/AddNewClients.fxml")));
    }

    public void setDataRow(ClientDto clientDto, ClientContactDto clientContactDto) {
        clientIdLbl.setText(clientDto.getClientId());
        AgeLbl.setText(String.valueOf(clientDto.getAge()));
        nameLbl.setText(clientDto.getClientName());
        genderLbl.setText(clientDto.getGender());
        goalLbl.setText(clientDto.getGoal());
        weightLbl.setText(String.valueOf(clientDto.getWeight()));
        joinedDateLbl.setText(String.valueOf(clientDto.getJoinedDate()));
        numberLbl.setText(clientContactDto.getPhone());
        mailLbl.setText(clientContactDto.getEmail());
        heightLbl.setText(String.valueOf(clientDto.getHeight()));
        fatperLbl.setText(String.valueOf(clientDto.getFatPer()));
        nicLbl.setText(clientContactDto.getNic());
    }
    public void setParentComponent(VBox tableVBox,Pane mainPaneClients) {
        this.tableVBox = tableVBox;
        this.mainPaneClients = mainPaneClients;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        heightLbl.setVisible(false);
        fatperLbl.setVisible(false);
        nicLbl.setVisible(false);

    }
}
