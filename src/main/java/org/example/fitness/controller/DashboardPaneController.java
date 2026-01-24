package org.example.fitness.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.fitness.dto.ExpiringMemberDto;
import org.example.fitness.model.ExpiringMemberModel;
import org.example.fitness.utill.CountingTransition;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashboardPaneController implements Initializable {
    private final ExpiringMemberModel expiringMemberModel;;
   public DashboardPaneController (){
        expiringMemberModel = new ExpiringMemberModel();
   }


    @FXML
    private AreaChart<?, ?> areaChart;

    @FXML
    private Label enddateLbl;

    @FXML
    private Pane mainPane;

    @FXML
    private Label memberIdLbl;

    @FXML
    private Label memberIdLbl1;

    @FXML
    private Label memberTypeLbl;

    @FXML
    private PieChart pieChart;

    @FXML
    private VBox tableBox;

    @FXML
    private Label mealCountLbl;

    @FXML
    private Label memberCountLbl;

    @FXML
    private Label workoutCountLbl;

    @FXML
    private Label machineCountLbl;

    public void setDataAreachart(){
        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data("Jan",20));
        series.getData().add(new XYChart.Data("Feb",30));
        series.getData().add(new XYChart.Data("Mar",10));

        XYChart.Series series1 = new XYChart.Series();

        series1.getData().add(new XYChart.Data("Jan",23));
        series1.getData().add(new XYChart.Data("Feb",56));
        series1.getData().add(new XYChart.Data("Mar",14));

        areaChart.getData().addAll(series);
    }

    private void setDataPieChart(){
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Suppliements",47.3),
                new PieChart.Data("Memberships",28.2),
                new PieChart.Data("Shedules",25.5)
        );

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(),"Amount : ",data.pieValueProperty()
                        )));

        pieChart.getData().addAll(pieChartData);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDataPieChart();
        ArrayList<ExpiringMemberDto> al=expiringMemberModel.getExpiringMembers();

        for (int i = 0; i < al.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/Dashboard/ExpiringMembers.fxml"));
            try{
                HBox box = fxmlLoader.load();
                ExpiringMemberController controller=fxmlLoader.getController();
                controller.setData(al.get(i));
                tableBox.getChildren().add(box);

            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());

            }


        }
        setDataAreachart();
        loadCount();
    }

    public void loadCount(){
        CountingTransition countingTransition1=new CountingTransition(machineCountLbl,30,1,1500);
        CountingTransition countingTransition2=new CountingTransition(workoutCountLbl,20,1,1500);
        CountingTransition countingTransition3=new CountingTransition(mealCountLbl,20,1,1500);
        CountingTransition countingTransition4=new CountingTransition(memberCountLbl,30,1,1500);

        countingTransition1.start();
        countingTransition2.start();
        countingTransition3.start();
        countingTransition4.start();

    }

}
