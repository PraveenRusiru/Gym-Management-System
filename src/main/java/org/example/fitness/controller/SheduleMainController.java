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
import org.example.fitness.dto.SheduleDto;
import org.example.fitness.model.SheduleModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SheduleMainController implements Initializable {

    CommonMethod commonMethod ;
    SheduleDto sheduleDto ;
    SheduleModel sheduleModel;
    int start=0;
    int end=0;
    ArrayList<SheduleDto> sheduleDtoList ;
    FXMLLoader fxmlLoader;
    private SheduleDataRowController sheduleDataRowController ;
    public SheduleMainController() {
        commonMethod = new CommonMethod();
        sheduleModel = new SheduleModel();
    }
    @FXML
    private ImageView FAProgress;

    @FXML
    private ImageView SADown;

    @FXML
    private ImageView TAEqual;

    @FXML
    private Label clientCountLbl;

    @FXML
    private Label clientCountPercentageLbl;

    @FXML
    private Pane mainPaneSchedule;

    @FXML
    private Label membershipCountLbl;

    @FXML
    private Label membershipCountPercentageLbl;

    @FXML
    private JFXButton newSheduleBtn;

    @FXML
    private ImageView nextBtn;

    @FXML
    private ImageView previousBtn;

    @FXML
    private Label scheduleCountLbl;

    @FXML
    private Label scheduleCountPercentageLbl;

    @FXML
    private VBox tableVBox;

    @FXML
    void loadNextRow(MouseEvent event) throws SQLException {
        previousBtn.setVisible(true);
        start=end;
        end=end+10>sheduleDtoList.size()?sheduleDtoList.size():end+10;
        loadTable();
    }

    @FXML
    void loadPreviousRows(MouseEvent event) throws SQLException {
        end=start;
        start=start-10<0?0:start-10;
        loadTable();
    }

    @FXML
    void navigateToNewShedule(ActionEvent event) throws IOException {

       commonMethod.loadFrame(mainPaneSchedule,"/view/Shedule/AddNewShedule.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            sheduleDtoList=sheduleModel.getAllShedules();
            end=sheduleDtoList.size()<10?sheduleDtoList.size():10;
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void loadTable() throws SQLException {
       sheduleDtoList=sheduleModel.getAllShedules();
       tableVBox.getChildren().clear();
        for (int i =start; i < end; i++) {
            fxmlLoader = new FXMLLoader(getClass().getResource("/view/Shedule/SheduleDataRow.fxml"));
            try {
                HBox hBox=(HBox) fxmlLoader.load();
                sheduleDataRowController=fxmlLoader.getController();
                sheduleDataRowController.setDataRow(sheduleDtoList.get(i));
                sheduleDataRowController.setParentComponent(tableVBox,mainPaneSchedule);
                tableVBox.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }



    }
}
