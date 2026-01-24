package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.example.fitness.dto.NutrationDataDto;
import org.example.fitness.dto.SheduleDataDto;
import org.example.fitness.model.NutritionProgrammeModel;
import org.example.fitness.model.SheduleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class NutrationScheduleController  implements Initializable {
    CommonMethod commonMethod;
    private NutrationDataDto nutrationDataDto;
    private final NutritionProgrammeModel nutritionProgrammeModel;
    public NutrationScheduleController() {
        commonMethod = new CommonMethod();
        nutritionProgrammeModel = new NutritionProgrammeModel();
    }
    @FXML
    private Label carbsLbl;

    @FXML
    private JFXTextField carbsTxt;

    @FXML
    private Label fatLbl;

    @FXML
    private JFXTextField fatTxt;

    @FXML
    private Label goalLbl;

    @FXML
    private Label maintainCaloriesLbl;

    @FXML
    private JFXTextField maintainaceTxt;

    @FXML
    private JFXButton nutrationNextBtn;

    @FXML
    private Pane nutrationSheduleMakingPane;

    @FXML
    private JFXButton programmeAddScheduleBtn1;

    @FXML
    private Label programmeId;

    @FXML
    private Label proteinLbl;

    @FXML
    private JFXTextField proteinTxt;

    @FXML
    private Label weightLossCaloriesLbl;

    @FXML
    private JFXTextField weightLossTxt;

    @FXML
    void nutationNextBtnAction(ActionEvent event) throws IOException {
        nutrationDataDto=new NutrationDataDto(programmeId.getText(),Integer.parseInt(maintainCaloriesLbl.getText()),Integer.parseInt(weightLossCaloriesLbl.getText()),Integer.parseInt(proteinLbl.getText()),Integer.parseInt(carbsLbl.getText()),Integer.parseInt(fatLbl.getText()));
        if(nutrationDataDto!=null){
            nutritionProgrammeModel.setNutrationDataDto(nutrationDataDto);
            commonMethod.loadFrame(nutrationSheduleMakingPane,"/view/Payment/Payment.fxml");
        }else{
            new Alert(Alert.AlertType.ERROR,"Enter data !");
        }

    }
    @FXML
    void addToCarbsTxt(MouseEvent event) {
      setText();
    }

    @FXML
    void addToFatTxt(MouseEvent event) {
        setText();
    }

    @FXML
    void addToMaintainanceTxt(MouseEvent event) {
        setText();
    }

    @FXML
    void addToProteinTxt(MouseEvent event) {
        setText();
    }

    @FXML
    void addToWeightlossTxt(MouseEvent event) {
        setText();
    }

    @FXML
    void programmeAddSchedule(ActionEvent event) {
        System.out.println("Goal "+AddNewClientsController.goal);
        goalLbl.setText(AddNewClientsController.goal);
        maintainCaloriesLbl.setText(maintainaceTxt.getText());
        weightLossCaloriesLbl.setText(weightLossTxt.getText());
        carbsLbl.setText(carbsTxt.getText());
        fatLbl.setText(fatTxt.getText());
        proteinLbl.setText(proteinTxt.getText());
        clearTxt();
    }

    public void clearTxt(){
        carbsTxt.setText("");
        maintainaceTxt.setText("");
        proteinTxt.setText("");
        weightLossTxt.setText("");
        fatTxt.setText("");
    }
    public void setText(){
        weightLossTxt.setText(weightLossCaloriesLbl.getText());
        proteinTxt.setText(proteinLbl.getText());
        maintainaceTxt.setText(maintainCaloriesLbl.getText());
        fatTxt.setText(fatLbl.getText());
        carbsTxt.setText(carbsLbl.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            programmeId.setText(NutritionProgrammeModel.getNextNutrationProgrammeId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
