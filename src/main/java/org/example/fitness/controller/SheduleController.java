package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.example.fitness.dto.ClientDto;
import org.example.fitness.dto.SheduleDto;
import org.example.fitness.model.ClientModel;
import org.example.fitness.model.SheduleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SheduleController implements Initializable {
    public static int count=-1;
    CommonMethod commonMethod;
    private SheduleModel sheduleModel;
    SheduleDto sheduleDto;
    String clientId="";
    public SheduleController(){
        commonMethod = new CommonMethod();
        sheduleModel=new SheduleModel();
    }


    @FXML
    private Pane sheduleDetailsPane;

    @FXML
    private JFXButton continueBtn;

    @FXML
    private ComboBox<Integer> comboBoxCount;

    @FXML
    private Label scheduleIdLbl;


    @FXML
    private Label endDateLbl;

    @FXML
    private Label issueDateLbl;

    @FXML
    private JFXButton doneBtn;

    @FXML
    private JFXTextArea goalArea;

    @FXML
    void comboBoxSelected(ActionEvent event) {
        count=Integer.parseInt(String.valueOf(comboBoxCount.getValue()));
    }
    @FXML
    void doneBtnClick(ActionEvent event) throws IOException {
        doneButton();
    }

    @FXML
    void continueBtnClick(ActionEvent event) throws IOException {
        doneButton();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (AddNewSheduleController.isFromAddNewShedule){
            continueBtn.setVisible(true);
            doneBtn.setVisible(false);
            clientId=AddNewSheduleController.clientId;
        }else{
            continueBtn.setVisible(false);
            doneBtn.setVisible(true);
            clientId=AddNewClientsController.clientId;
        }

        getCustomerId();
        issueDateLbl.setText(String.valueOf(LocalDate.now()));
        endDateLbl.setText(String.valueOf(LocalDate.now().plusWeeks(6)));
        ObservableList<Integer> countList= FXCollections.observableArrayList(1,2,3,4,5,6,7);
        comboBoxCount.setItems(countList);

    }
    public void getCustomerId(){
        try {
            scheduleIdLbl.setText(sheduleModel.getNextSheduleId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
//            new Alert(Alert.AlertType.ERROR,"Fail to update customer...!").show();
        }
    }
    public void doneButton() throws IOException {
        if(!goalArea.getText().isEmpty()){
            sheduleDto=new SheduleDto(scheduleIdLbl.getText(),clientId,Date.valueOf(issueDateLbl.getText()), Date.valueOf(endDateLbl.getText()),goalArea.getText());
            sheduleModel.setSheduleDto(sheduleDto);
            commonMethod.loadFrame(sheduleDetailsPane,"/view/Shedule/SheduleMaking.fxml");
        }else{
            new Alert(Alert.AlertType.ERROR,"Goal Text are is Empty").show();
        }

    }
}
