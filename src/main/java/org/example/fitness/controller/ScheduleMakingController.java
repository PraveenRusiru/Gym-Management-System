package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.fitness.dto.SheduleDataDto;
import org.example.fitness.dto.SheduleRowDto;
import org.example.fitness.model.ExerciseInSheduleModel;
import org.example.fitness.model.ExerciseModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

public class ScheduleMakingController implements Initializable {
    private ExerciseInSheduleModel exerciseInSheduleModel;
    private ExerciseModel exerciseModel;
   private ArrayList<SheduleDataDto> sheduleDataDtos;
    private SheduleDataDto selectedSheduleDataDto;
    boolean isShowSheduleData = false;
    int completedSheduleCount = 0;
    int currentShownSheduleCount = 0;
    String targetMuscle;
    CommonMethod commonMethod;
    FXMLLoader fxmlLoader;
    int day=-1;
    private SheduleRowDto sheduleRowDto;
    private SheduleRowController controller;
    public ScheduleMakingController() {
        exerciseModel = new ExerciseModel();
        sheduleDataDtos = new ArrayList<>();
        commonMethod = new CommonMethod();
        exerciseInSheduleModel = new ExerciseInSheduleModel();
    }
    @FXML
    private Label dayLbl;

    @FXML
    private Pane sheduleMakingPane;

    @FXML
    private JFXTextField weightTxt;

    @FXML
    private JFXTextField priorityNumTxt;

    @FXML
    private ComboBox<String> workoutComboBox;

    @FXML
    private ComboBox<Integer> dayComboBox;

    @FXML
    private JFXButton addScheduleBtn;

    @FXML
    private JFXButton dayCompletedBtn;

    @FXML
    private JFXButton showScheduleBtn;

    @FXML
    private ComboBox<Integer> repComboBox;

    @FXML
    private ComboBox<Integer> restComboBox;

    @FXML
    private ComboBox<Integer> setComboBox;

    @FXML
    private ComboBox<String> targetMuscleComboBox;

    @FXML
    private ComboBox<Integer> tempoComboBox;

    @FXML
    private VBox sheduleTable;

    @FXML
    private JFXButton resetBtn;

    @FXML
    private JFXButton nextBtn;

    @FXML
    private JFXButton updateBtn;

    @FXML
    void nextBtnAction(ActionEvent event) throws IOException {
        if(!(sheduleDataDtos.isEmpty())){
        exerciseInSheduleModel.setSheduleDataDtos(sheduleDataDtos);
            commonMethod.loadFrame(sheduleMakingPane,"/view/Shedule/NutrationSchedule.fxml");
       }else{
            new Alert(Alert.AlertType.ERROR,"Incomplete  Schedule !").show();
        }

    }

    @FXML
    void selectRepComboBox(ActionEvent event) {
        isShowSheduleData = false;
    }

    @FXML
    void selectRestComboBox(ActionEvent event) {
        isShowSheduleData = false;
    }

    @FXML
    void selectSetComboBox(ActionEvent event) {
        isShowSheduleData = false;
    }

    @FXML
    void selectTargetMuscleComboBox(ActionEvent event) {
        isShowSheduleData = false;
        targetMuscle=targetMuscleComboBox.getValue();
        System.out.println(targetMuscle);
//        ArrayList<String> workouts = new ArrayList<>();
        try {
            ArrayList<String> workouts=exerciseModel.getExercises(targetMuscleComboBox.getValue());
            System.out.println(workouts);
            ObservableList<String> workoutList= FXCollections.observableArrayList();
            for (String str:workouts){
                workoutList.add(str);
            }
            workoutComboBox.setItems(workoutList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL EROOR").show();
        }
    }
    @FXML
    void selectTempoComboBox(ActionEvent event) {
        isShowSheduleData = false;
    }

    @FXML
    void selectdayComboBox(ActionEvent event) {
        isShowSheduleData = false;
        day=dayComboBox.getValue();
        dayLbl.setText("Day - "+day);
    }
    @FXML
    void selectWorkoutComboBox(ActionEvent event) {
        isShowSheduleData = false;
    }
    @FXML
    void addSchedule(ActionEvent event) {
       // sheduleTable.getChildren().clear();
        isShowSheduleData = false;
        dayCompletedBtn.setDisable(false);
        selectedSheduleDataDto=new SheduleDataDto(dayComboBox.getValue(),Integer.parseInt(priorityNumTxt.getText()),workoutComboBox.getValue(),Integer.parseInt(weightTxt.getText()),setComboBox.getValue(),repComboBox.getValue(),restComboBox.getValue(),tempoComboBox.getValue());
       // sheduleDataDtos.add(selectedSheduleDataDto);

         fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/Shedule/Shedulerow.fxml"));
        try{
            HBox box = fxmlLoader.load();
            controller=fxmlLoader.getController();
            controller.setDataForRow(selectedSheduleDataDto);
            controller.setParentContainer(sheduleTable);
            controller.setParentComponents(dayCompletedBtn,dayComboBox,priorityNumTxt,targetMuscleComboBox,weightTxt,workoutComboBox,setComboBox,repComboBox,tempoComboBox,restComboBox);

            sheduleTable.getChildren().add(box);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());

        }

    }
    @FXML
    void dayCompleted(ActionEvent event) {//get all the data from shedule table and set them to DTO and add those into ArrayList
        completedSheduleCount++;
        isShowSheduleData = false;
        boolean isDuplicated=false;
        //  sheduleDataDtos.add(selectedSheduleDataDto);

        for (var node:sheduleTable.getChildren()) {
            if(node instanceof  HBox){
                HBox hbox= (HBox) node;
                Label priorityLabel = (Label)hbox.lookup("#priorityLbl");
                Label tempoLabel = (Label)hbox.lookup("#tempoLbl");
                Label weightLabel = (Label)hbox.lookup("#weightLbl");
                Label workoutLabel = (Label)hbox.lookup("#workoutLbl");
                Label setLabel = (Label)hbox.lookup("#setLbl");
                Label repLabel = (Label)hbox.lookup("#repLbl");
                Label restLabel = (Label)hbox.lookup("#restLbl");
                selectedSheduleDataDto=new SheduleDataDto(day,Integer.parseInt(priorityLabel.getText()),workoutLabel.getText(),Integer.parseInt(weightLabel.getText()),Integer.parseInt(setLabel.getText()),Integer.parseInt(repLabel.getText()),Integer.parseInt(restLabel.getText()),Integer.parseInt(tempoLabel.getText()));

               Optional<SheduleDataDto> exists=sheduleDataDtos.stream()
                       .filter(dto->dto.getDay()==day && dto.getPriorityNum()==selectedSheduleDataDto.getPriorityNum())
                       .findFirst();

                if(exists.isPresent()){
                    Alert addingAlert = new Alert(Alert.AlertType.CONFIRMATION, "This day already has data! Do you want to modify it?");
                    Optional<ButtonType> result = addingAlert.showAndWait();

                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        // Replace existing item with the updated one
                        isDuplicated=true;
                        sheduleDataDtos.set(sheduleDataDtos.indexOf(exists.get()), selectedSheduleDataDto);

                        for (SheduleDataDto sheduleDataDto:sheduleDataDtos){
                            System.out.println("shedule "+sheduleDataDto.getDay()+" "+sheduleDataDto.getPriorityNum()+" "+sheduleDataDto.getWorkout()+" "+sheduleDataDto.getSet()+" "+sheduleDataDto.getRep());
                        }

                    } else {
                        new Alert(Alert.AlertType.ERROR, "OK!").show();
                    }
                }else{

                    sheduleDataDtos.add(selectedSheduleDataDto);
                }

            }
        }
        if(isDuplicated){
            --completedSheduleCount;
        }
        sortArrayList();
        sheduleTable.getChildren().clear();
        clearAllComboBox();
        //dayComboBox.getSelectionModel().clearSelection();

    }

    @FXML
    void showSchedule(ActionEvent event) {
        isShowSheduleData = true;
        resetBtn.setDisable(false);
        resetBtn.setVisible(true);
        showScheduleBtn.setText("Next Shedule");
        sheduleTable.getChildren().clear();
        System.out.println("Completed shedule count "+completedSheduleCount);
        if(currentShownSheduleCount>=completedSheduleCount){
            currentShownSheduleCount=0;
        }
            currentShownSheduleCount++;
        System.out.println("Current shown count "+currentShownSheduleCount);
        for (int i = 0; i < sheduleDataDtos.size(); i++) {
           int day1=sheduleDataDtos.get(i).getDay();
            System.out.println("Day :"+day1);

            if(day1==currentShownSheduleCount){
                System.out.println("row adding process started ");
                dayLbl.setText("Day - "+day1);
                dayComboBox.setValue(day1);
                 fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/view/Shedule/Shedulerow.fxml"));
                try{
                    HBox box = fxmlLoader.load();
                     controller=fxmlLoader.getController();
                    controller.setDataForRow(sheduleDataDtos.get(i));
                    controller.setParentContainer(sheduleTable);
                    controller.setParentComponents(dayCompletedBtn,dayComboBox,priorityNumTxt,targetMuscleComboBox,weightTxt,workoutComboBox,setComboBox,repComboBox,tempoComboBox,restComboBox);
                    sheduleTable.getChildren().add(box);

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());

                }
            }
            System.out.println("row adding process Ended ");
        }
        //completedSheduleCount++;
    }
    @FXML
    void resetBtnClick(ActionEvent event) {
        sheduleTable.getChildren().clear();
        resetBtn.setVisible(false);
        addScheduleBtn.setVisible(true);
    }
    @FXML
    void updateRow(ActionEvent event) {
        //selectedSheduleDataDto=new SheduleDataDto(dayComboBox.getValue(),Integer.parseInt(priorityNumTxt.getText()),workoutComboBox.getValue(),Integer.parseInt(weightTxt.getText()),setComboBox.getValue(),repComboBox.getValue(),restComboBox.getValue(),tempoComboBox.getValue());
        //controller.setDataForRow(selectedSheduleDataDto);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dayCompletedBtn.setDisable(true);
        updateBtn.setVisible(false);
        resetBtn.setDisable(true);
        resetBtn.setVisible(false);
        ObservableList<Integer> dayList= FXCollections.observableArrayList();
        for (int i = 0; i < SheduleController.count; i++) {
            dayList.add(i+1);
        }
        dayComboBox.setItems(dayList);
        loadObservarablaLists();

    }
    public void clearAllComboBox() {
        priorityNumTxt.clear();
        priorityNumTxt.setPromptText("Priority");

        weightTxt.clear();
        weightTxt.setPromptText("Weight");

        // Set prompt text before clearing selection
        //dayComboBox.setPromptText("Day");
        //dayComboBox.getSelectionModel().clearSelection();

        setComboBox.setPromptText("Set");
        setComboBox.getSelectionModel().clearSelection();

        repComboBox.setPromptText("Rep");
        repComboBox.getSelectionModel().clearSelection();

        restComboBox.setPromptText("Rest");
        restComboBox.getSelectionModel().clearSelection();

        tempoComboBox.setPromptText("Tempo");
        tempoComboBox.getSelectionModel().clearSelection();

        targetMuscleComboBox.setPromptText("Target Muscle");
        targetMuscleComboBox.getSelectionModel().clearSelection();

    }
    private void sortArrayList() {
        sheduleDataDtos.sort(Comparator.comparing(SheduleDataDto::getPriorityNum));
    }
//    public void update(){
//        SheduleRowController sheduleRowController=fxmlLoader.getController();
//        this.sheduleRowDto=sheduleRowController.sheduleRowDto;
//        priorityNumTxt.setText(String.valueOf(sheduleRowDto.getPriorityNum()));
//        weightTxt.setText(String.valueOf(sheduleRowDto.getWeight()));
//
//    }
    public void loadObservarablaLists(){
        try {
            ArrayList<String> muslces=exerciseModel.getMuscles();
            System.out.println(muslces);
            ObservableList<String> muscleList=FXCollections.observableArrayList();
            for (String str:muslces){
                muscleList.add(str);
            }
            targetMuscleComboBox.setItems(muscleList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL EROOR").show();
        }



        ObservableList<Integer> setList=FXCollections.observableArrayList(1,2,3,4,5,6,7);
        setComboBox.setItems(setList);
        ObservableList<Integer> repList=FXCollections.observableArrayList(1,3,5,6,8,10,12,15,18,20,25,30);
        repComboBox.setItems(repList);
        ObservableList<Integer> restList=FXCollections.observableArrayList(30,60,90,120,150,300);
        restComboBox.setItems(restList);
        ObservableList<Integer> tempoList=FXCollections.observableArrayList(2010,2111,0010,0100,4020,3130,2121,3030);
        tempoComboBox.setItems(tempoList);
    }

}

