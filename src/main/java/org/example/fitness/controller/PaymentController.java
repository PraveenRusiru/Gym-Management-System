package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.example.fitness.dto.PaymentDto;
import org.example.fitness.model.NoteForClientModel;
import org.example.fitness.model.PayementModel;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PaymentController  implements Initializable {
    PaymentDto paymentDto;
    private final PayementModel payementModel;
    private final NoteForClientModel noteForClientModel;
    private CommonMethod commonMethod = new CommonMethod();
    public PaymentController() {
        payementModel = new PayementModel();
        noteForClientModel = new NoteForClientModel();
    }
    @FXML
    private Label dateLbl;


    @FXML
    private Label paymentIdLbl;

    @FXML
    private Pane nutrationSheduleMakingPane;

    @FXML
    private JFXButton paymentBtn;

    @FXML
    private Label service1Lbl;

    @FXML
    private Label service1PriceLbl;

    @FXML
    private Label service2Lbl;

    @FXML
    private Label service2PriceLbl;

    @FXML
    private Label totalAmountLbl;


    @FXML
    private JFXButton finishMembershipAdd;

    @FXML
    private JFXButton finishNewShedule;


    @FXML
    void finishNewSheduleAction(ActionEvent event) {
        paymentDto = new PaymentDto(paymentIdLbl.getText(),"Paid", Date.valueOf(dateLbl.getText()),Double.parseDouble(totalAmountLbl.getText()));

        if(Double.parseDouble(totalAmountLbl.getText()) > 0){
            try {
                boolean isTransActionDone=payementModel.isNewSheduleSaved(paymentDto);
                if(isTransActionDone){
                    new Alert(Alert.AlertType.INFORMATION,"Payment successfully saved").show();
                }else{
                    new Alert(Alert.AlertType.ERROR,"Payment failed").show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Payment not saved").show();
        }

    }

    @FXML
    void finishMembershipAddAction(ActionEvent event) {
        if(MembershipRowController.isFromMembershipRowController){
            paymentDto=new PaymentDto(paymentIdLbl.getText(),"Paid", Date.valueOf(dateLbl.getText()),Double.parseDouble(totalAmountLbl.getText()));
            if(Double.parseDouble(totalAmountLbl.getText()) > 0){
                try {
                    boolean isTransActionDone=payementModel.IsMembershipUpdatedPayment(paymentDto);
                    if(isTransActionDone){
                        new Alert(Alert.AlertType.INFORMATION,"Payment successfully saved").show();
                        MembershipRowController.isFromMembershipRowController=false;
                        //commonMethod.loadFrame();
                    }else{
                        new Alert(Alert.AlertType.ERROR,"Payment failed").show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Payment not saved").show();
            }
        }else{
            paymentDto = new PaymentDto(paymentIdLbl.getText(),"Paid", Date.valueOf(dateLbl.getText()),Double.parseDouble(totalAmountLbl.getText()));
            if(Double.parseDouble(totalAmountLbl.getText()) > 0){
                try {
                    boolean isTransActionDone=payementModel.isNewMembershipSaved(paymentDto);
                    if(isTransActionDone){
                        new Alert(Alert.AlertType.INFORMATION,"Payment successfully saved").show();
                        AddNewMembershipController.isFromAddNewMembership=false;

                    }else{
                        new Alert(Alert.AlertType.ERROR,"Payment failed").show();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Payment not saved").show();
            }
        }

    }

    @FXML
    void paymentBtnAction(ActionEvent event) {

       paymentDto = new PaymentDto(paymentIdLbl.getText(),"Paid", Date.valueOf(dateLbl.getText()),Double.parseDouble(totalAmountLbl.getText()));
       if(Double.parseDouble(totalAmountLbl.getText()) > 0){
           try {
               boolean isTransActionDone=payementModel.savePayment(paymentDto);
               if(isTransActionDone){
                   new Alert(Alert.AlertType.INFORMATION,"Payment successfully saved").show();
               }else{
                   new Alert(Alert.AlertType.ERROR,"Payment failed").show();
               }
           } catch (SQLException e) {
               e.printStackTrace();
               System.out.println(e.getMessage());
           }
       }else{
           new Alert(Alert.AlertType.ERROR,"Payment not saved").show();
       }



    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateLbl.setText(LocalDate.now().toString());
        if(AddNewMembershipController.isFromAddNewMembership || MembershipRowController.isFromMembershipRowController){
            paymentBtn.setVisible(false);
            finishNewShedule.setVisible(false);
            finishMembershipAdd.setVisible(true);
        } else if (AddNewSheduleController.isFromAddNewShedule) {
            paymentBtn.setVisible(false);
            finishNewShedule.setVisible(true);
            finishMembershipAdd.setVisible(false);
        } else{
            finishMembershipAdd.setVisible(false);
            paymentBtn.setVisible(true);
            finishNewShedule.setVisible(false);
        }

        try {
            paymentIdLbl.setText(PayementModel.getNextPaymentId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        service1Lbl.setText(ServiceClientController.txt);

    service2Lbl.setText(ServiceClientController.shedules);

    service1PriceLbl.setText(String.valueOf(ServiceClientController.packagePrice));

    service2PriceLbl.setText(noteForClientModel.getClientNoteDto()==null?"0.00":"13000.00");
    if(ServiceClientController.shedules.equals("")) {
      totalAmountLbl.setText(String.valueOf(ServiceClientController.packagePrice));
    }else{
        totalAmountLbl.setText(String.valueOf(ServiceClientController.packagePrice+13000));
    }
    ServiceClientController.packagePrice=0;
    ServiceClientController.shedules="";
    ServiceClientController.txt="";
    //totalAmountLbl.setText(String.valueOf(ServiceClientController.packagePrice+(ServiceClientController.shedules.equals(""))?0:13_000));
    }

}
