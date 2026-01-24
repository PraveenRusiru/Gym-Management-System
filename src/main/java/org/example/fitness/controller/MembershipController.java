package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
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
import org.example.fitness.model.MembershipModel;
import org.example.fitness.utill.CountingTransition;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

    public class MembershipController implements Initializable {

    ArrayList<String[]> membershipArrays;
    private MembershipModel membershipModel;
        int start=0;
        int end=0;
        private FXMLLoader fxmlLoader;
        private MembershipRowController membershipRowController;
        private CommonMethod commonMethod;
    public MembershipController(){
        membershipModel = new MembershipModel();
        commonMethod = new CommonMethod();
    }
    @FXML
    private Pane mainPaneMembership;
        @FXML
        private Label lbl1;

        @FXML
        private Label lbl2;

        @FXML
        private Label lbl3;
    @FXML
    private JFXButton newMembershipBtn;

    @FXML
    private ImageView nextBtn;

    @FXML
    private ImageView previousBtn;

    @FXML
    private VBox tableVBox;

    @FXML
    void loadNextRow(MouseEvent event) {
        previousBtn.setVisible(true);
        start=end;
        end=end+10>membershipArrays.size()?membershipArrays.size():end+10;

    }

    @FXML
    void loadPreviousRows(MouseEvent event) {
        end=start;
        start=start-10<0?0:start-10;
    }

    @FXML
    void navigateToNewMembership(ActionEvent event) {
        try {
            commonMethod.loadFrame(mainPaneMembership,"/view/Membership/AddNewMembership.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        previousBtn.setVisible(false);

        CountingTransition countingTransition1=new CountingTransition(lbl1,12,1,2000);
        countingTransition1.start();

        CountingTransition countingTransition2=new CountingTransition(lbl2,2,1,2000);
        countingTransition2.start();

        CountingTransition countingTransition3=new CountingTransition(lbl3,230000,1,5000);
        countingTransition3.start();

        try {
            membershipArrays=membershipModel.getMembershipData();
            end=membershipArrays.size()<10?membershipArrays.size():10;
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }
    public void loadTable(){
            tableVBox.getChildren().clear();
        for (int i = start; i < end; i++) {
        fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/Membership/MembershipRow.fxml"));

            try {
                HBox hBox=(HBox)fxmlLoader.load();
                membershipRowController=fxmlLoader.getController();
                membershipRowController.setData(membershipArrays.get(i));
                membershipRowController.setComponent(tableVBox,mainPaneMembership);
                tableVBox.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
}
