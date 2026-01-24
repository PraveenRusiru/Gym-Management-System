package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import org.example.fitness.dto.ClientNoteDto;
import org.example.fitness.model.ClientModel;
import org.example.fitness.model.NoteForClientModel;
import org.example.fitness.model.SheduleModel;
import org.example.fitness.utill.TextFieldManager;
import org.example.fitness.utill.TopToBottomLayeredTransitionUtility;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddNewSheduleController implements Initializable {
    private TextFieldManager textFieldManager;
    private SheduleModel sheduleModel;
    private CommonMethod commonMethod;
   static boolean isFromAddNewShedule = false;
   static String clientId="";
   private ClientNoteDto clientNoteDto;
   private NoteForClientModel noteForClientModel;
   ClientModel clientModel;
   ClientDto clientDto;
    public AddNewSheduleController(){
        isFromAddNewShedule = true;
        textFieldManager = new TextFieldManager();
         sheduleModel = new SheduleModel();
         commonMethod = new CommonMethod();
         noteForClientModel = new NoteForClientModel();
         clientModel = new ClientModel();
         clientDto = new ClientDto();
    }
    @FXML
    private JFXButton addBtn;

    @FXML
    private Pane addShedulePane;

    @FXML
    private Pane applicationPane;

    @FXML
    private JFXButton backScheduleBtn;


    @FXML
    private ComboBox<String> goalComboBox;

    @FXML
    private Label clientIdLbl;


    @FXML
    private JFXTextField nicTxt;

    @FXML
    private JFXTextArea noteAre;

    @FXML
    private Label sheduleId;

    @FXML
    void navigateToSheduleList(ActionEvent event) throws IOException {
        TopToBottomLayeredTransitionUtility.loadFrameWithLayeredTransition(addShedulePane,"/view/Shedule/SheduleMain.fxml");
    }

    @FXML
    void navigateToSheduleMaking(ActionEvent event) throws IOException, SQLException {
       if(!noteAre.getText().isEmpty()){
           clientId=clientIdLbl.getText();
           clientDto.setClientId(clientId);
           clientDto.setGoal(goalComboBox.getValue());
           clientModel.setClientDto(clientDto);
           AddNewClientsController.goal=goalComboBox.getValue();

           clientNoteDto=new ClientNoteDto(NoteForClientModel.getNextNoteId(),noteAre.getText(),clientIdLbl.getText());
           noteForClientModel.setClientNoteDto(clientNoteDto);

           ServiceClientController.shedules="Workout & Nutration Plan";//this is crucial;
           commonMethod.loadFrame(applicationPane,"/view/Shedule/Sheule.fxml");
       }else{
           new Alert(Alert.AlertType.ERROR,"Please enter a note").show();
       }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> goalList= FXCollections.observableArrayList("Muscle gain","Fat loss","Strength Training","Endurance Training");
        goalComboBox.setItems(goalList);
        textFieldManager.setLblTxtAndBox(nicTxt,clientIdLbl,goalComboBox);
        try {
            sheduleId.setText(sheduleModel.getNextSheduleId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
