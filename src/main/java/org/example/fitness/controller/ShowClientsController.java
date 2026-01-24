package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import com.mysql.cj.xdevapi.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import org.example.fitness.model.MembershipModel;
import org.example.fitness.model.SheduleModel;
import org.example.fitness.utill.Anlysis;
import org.example.fitness.utill.CountingTransition;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowClientsController implements Initializable {
        private  FXMLLoader fxmlLoader;
        private  ClientRowController clientRowController;
    ArrayList<ClientDto> clientDtos;
    ArrayList<ClientContactDto> clientContactDtos;
    private ClientModel clientModel;
    private ClientContactModel clientContactModel;
    int start=0;
    int end=0;
    SheduleModel sheduleModel;
    MembershipModel membershipModel;
    public ShowClientsController() {
        membershipModel = new MembershipModel();
        clientModel=new ClientModel();
        clientContactModel=new ClientContactModel();
        sheduleModel=new SheduleModel();
        //clientRowController = new ClientRowController();

    }
    @FXML
    private ImageView SADown;

    @FXML
    private ImageView SAEqual;

    @FXML
    private ImageView SAProgress;

    @FXML
    private ImageView TADown;

    @FXML
    private ImageView TAEqual;

    @FXML
    private ImageView TAProgress;

    @FXML
    private Label membershipCountLbl;

    @FXML
    private Label membershipCountPercentageLbl;

    @FXML
    private Label scheduleCountLbl;

    @FXML
    private Label scheduleCountPercentageLbl;


    @FXML
        private Pane mainPaneClients;

        @FXML
        private JFXButton newClientBtn;

        @FXML
        private VBox tableVBox;

      @FXML
    private ImageView nextBtn;

    @FXML
    private ImageView previousBtn;

    @FXML
    private ImageView FADown;

    @FXML
    private ImageView FAProgress;

    @FXML
    private ImageView FAEqual;

    @FXML
    private Label clientCountLbl;

    @FXML
    private Label clientCountPercentageLbl;

    @FXML
    void loadNextRow(MouseEvent event) throws SQLException {
       previousBtn.setVisible(true);
        start=end;
        end=end+10>clientDtos.size()?clientDtos.size():end+10;
        loadTable();
    }

    @FXML
    void loadPreviousRows(MouseEvent event) throws SQLException {
        end=start;
        start=start-10<0?0:start-10;
        loadTable();
    }
        @FXML
        void navigateToNewClient(ActionEvent event) {
            try {
                newClientBtn.setVisible(false);
                mainPaneClients.getChildren().clear();
                mainPaneClients.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Clients/AddNewClients.fxml")));
            }catch (IOException e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        previousBtn.setVisible(false);

//        Anlysis anlysis=new Anlysis(clientModel);
//        anlysis.analyizeMethod(clientCountLbl,clientCountPercentageLbl,FADown,FAEqual,FAProgress);
//
//        Anlysis anlysis1=new Anlysis(membershipModel);
//        anlysis1.analyizeMethod(membershipCountLbl,membershipCountPercentageLbl,SADown,SAEqual,SAProgress);
//
//        Anlysis anlysis2=new Anlysis(sheduleModel);
//        anlysis2.analyizeMethod(scheduleCountLbl,scheduleCountPercentageLbl,TADown,TAEqual,TAProgress);
//        System.out.println();
        CountingTransition countingTransition=new CountingTransition(clientCountLbl,30,1,1200);
        countingTransition.start();

        CountingTransition countingTransition1=new CountingTransition(membershipCountLbl,18,1,1200);
        countingTransition1.start();

        CountingTransition countingTransition2=new CountingTransition(scheduleCountLbl,12,1,1200);
        countingTransition2.start();




        try {
            loadFirstAnalysis();
            loadAnalysis();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        try {
            clientDtos=clientModel.getClientData();
            clientContactDtos=clientContactModel.getClientContactData();
            end=clientDtos.size()<10?clientDtos.size():10;
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public  void loadTable() throws SQLException {

        try {
            clientDtos=clientModel.getClientData();
            clientContactDtos=clientContactModel.getClientContactData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            tableVBox.getChildren().clear();
        for (int i = start; i < end; i++) {
            fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/Clients/ClientRow.fxml"));
            try {
                HBox hBox=(HBox) fxmlLoader.load();
                clientRowController=fxmlLoader.getController();
                clientRowController.setDataRow(clientDtos.get(i),clientContactDtos.get(i));
                clientRowController.setParentComponent(tableVBox,mainPaneClients);
                tableVBox.getChildren().add(hBox);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }


    }

    public void loadFirstAnalysis() throws SQLException {
//        int thisMonthCount=0;
//        int lastMonthCount=0;
//        int clientCount=0;
//        clientCount=clientModel.getClientCount();
//        thisMonthCount=clientModel.getThisMonthCount();
//        lastMonthCount=clientModel.getLastMonthCount();
//        double percentage=0;
//        if(lastMonthCount!=0){
//            percentage=(double)(thisMonthCount-lastMonthCount)/lastMonthCount*100;
//        }else{
//            percentage=(double)(thisMonthCount-lastMonthCount)/thisMonthCount*100;
//        }
//        clientCountLbl.setText(String.valueOf(clientCount));
//        System.out.println();
//        if(percentage>0){
//            clientCountPercentageLbl.setText("+ "+String.valueOf(percentage)+" %");
//            clientCountPercentageLbl.setStyle("-fx-text-fill: #90d78d;");
//            FAProgress.setVisible(true);
//            FADown.setVisible(false);
//            FAEqual.setVisible(false);
//        } else if (percentage==0) {
//            clientCountPercentageLbl.setText(" "+String.valueOf(percentage)+" %");
//            clientCountPercentageLbl.setStyle("-fx-text-fill: #8a888a;");
//            FAProgress.setVisible(false);
//            FADown.setVisible(false);
//            FAEqual.setVisible(true);
//        }else{
//            clientCountPercentageLbl.setText("- "+String.valueOf(percentage)+" %");
//            clientCountPercentageLbl.setStyle("-fx-text-fill: #fb6868;");
//            FAProgress.setVisible(false);
//            FADown.setVisible(true);
//            FAEqual.setVisible(false);
//        }

    }
    public void loadAnalysis() throws SQLException {

        int thisMonthCount= membershipModel.getThisMonthCount();
        System.out.println("thisMonthCount: "+thisMonthCount);
        int lastMonthCount=0;
        int Count=0;

    }
}
