package org.example.fitness.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.fitness.dto.SheduleDto;
import org.example.fitness.model.ClientModel;
import org.example.fitness.model.ExerciseInSheduleModel;
import org.example.fitness.model.SheduleModel;

import java.sql.SQLException;
import java.util.Optional;

public class SheduleDataRowController {
    ExerciseInSheduleModel exerciseInSheduleModel;
    ClientModel clientModel;
    SheduleModel sheduleModel;
    public SheduleDataRowController(){
        exerciseInSheduleModel = new ExerciseInSheduleModel();
        clientModel = new ClientModel();
        sheduleModel = new SheduleModel();
    }


    @FXML
    private HBox hBoxcontainer;

    @FXML
    private Label clientIdLbl;

    @FXML
    private Label dayLbl;

    @FXML
    private Label expireDateLbl;

    @FXML
    private Label goalLbl;

    @FXML
    private Label issuedDateLbl;

    @FXML
    private Label sheduleIdLbl;

    private VBox tableVBox;
    private Pane mainPaneShedule;
    @FXML
    void deleteAction(MouseEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this Schedule?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.YES){
            if(sheduleModel.deleteShedule(sheduleIdLbl.getText())  && tableVBox!=null){
                tableVBox.getChildren().remove(hBoxcontainer);
            }else{
                new Alert(Alert.AlertType.ERROR,"Something went wrong on deleting Schedule!").show();
            }
        }


    }

    @FXML
    void detailUpdateAction(MouseEvent event) {

    }

    public void setDataRow(SheduleDto sheduleDto) throws SQLException {
        clientIdLbl.setText(sheduleDto.getClientId());
        sheduleIdLbl.setText(sheduleDto.getSheduleId());
        issuedDateLbl.setText(String.valueOf(sheduleDto.getIssuedDate()));
        expireDateLbl.setText(String.valueOf(sheduleDto.getExpiredDate()));
        String goal=clientModel.getGoal(sheduleDto.getClientId());
        goalLbl.setText(goal);
        int daycount=exerciseInSheduleModel.getDayCount(sheduleDto.getSheduleId());
        dayLbl.setText(String.valueOf(daycount));
    }

    public void setParentComponent(VBox tableVBox, Pane mainPaneSchedule) {
        this.tableVBox = tableVBox;
        this.mainPaneShedule = mainPaneSchedule;
    }
}
