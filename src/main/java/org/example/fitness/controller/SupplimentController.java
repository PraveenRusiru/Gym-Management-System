package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.example.fitness.DB.DBConnection;
import org.example.fitness.dto.InventoryDto;
import org.example.fitness.dto.ItemDto;
import org.example.fitness.dto.SuppliementDto;
import org.example.fitness.model.InventoryModel;
import org.example.fitness.model.SupplimentModel;
import org.example.fitness.utill.CountingTransition;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SupplimentController implements Initializable {
    private FXMLLoader fxmlLoader;
    private SupplimentRowController supplimentRowController;
    ArrayList<SuppliementDto> suppliementDtos;
    ArrayList<InventoryDto> inventoryDtos;
  static   ArrayList<ItemDto> itemDtos=new ArrayList<>();
    private SupplimentModel supplimentModel;
    private InventoryModel inventoryModel;
    CommonMethod commonMethod = new CommonMethod();
    int start=0;
    int end=0;
    public SupplimentController() {
        supplimentModel = new SupplimentModel();
        inventoryModel = new InventoryModel();
    }
    @FXML
    private Pane mainPaneSuppliments;

    @FXML
    private JFXButton newSupplimentBtn;

    @FXML
    private ImageView nextBtn;

    @FXML
    private ImageView previousBtn;

    @FXML
    private VBox tableVBox;

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;

    @FXML
    private Label lbl3;

    @FXML
    private JFXButton reportBtn;

    @FXML
    private JFXButton checkoutBtn;

    @FXML
    void getReport(ActionEvent event) {

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass()
                            .getResourceAsStream("/Report/SupplimentOrders.jrxml"
                            ));

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();

            parameters.put("Report_date", LocalDate.now().toString());
            parameters.put("Report_Clinet_id", CartController.cliId);
           // parameters.put("P_Date", LocalDate.now().toString());
           // parameters.put("P_Customer_Id", customerTM.getCustomerId());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.ERROR, "Fail to generate report...!").show();
//           e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "DB error...!").show();
        }
        reportBtn.setVisible(false);
        CartController.cliId="";
    }

    @FXML
    void loadNextRow(MouseEvent event) {
        previousBtn.setVisible(true);
        start=end;
        end=end+10>suppliementDtos.size()?suppliementDtos.size():end+10;
        loadTable();
    }

    @FXML
    void loadPreviousRows(MouseEvent event) {
        end=start;
        start=start-10<0?0:start-10;
        loadTable();
    }

    @FXML
    void navigateToNewSuppliment(ActionEvent event) {
        try {
            newSupplimentBtn.setVisible(false);
            commonMethod.loadFrame(mainPaneSuppliments,"/view/Suppliment/AddNewSuppliment.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void navigateToCart(ActionEvent event) {
        System.out.println("ItemDtos :"+itemDtos.size());
        try {
            commonMethod.loadFrame(mainPaneSuppliments,"/view/Suppliment/cart.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        checkoutBtn.setVisible(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CountingTransition countingTransition1=new CountingTransition(lbl1,12,1,1200);
        countingTransition1.start();

        CountingTransition countingTransition2=new CountingTransition(lbl2,3,1,1200);
        countingTransition2.start();

        CountingTransition countingTransition3=new CountingTransition(lbl3,350000,1,1200);
        countingTransition3.start();

       if (CartController.isFromCartController){
            reportBtn.setVisible(true);
       }else{
            reportBtn.setVisible(false);
       }
        previousBtn.setVisible(false);
        checkoutBtn.setVisible(false);
        try {
            suppliementDtos=supplimentModel.getAllSuppliement();
            inventoryDtos=inventoryModel.getAllInventory();
                end=suppliementDtos.size()<10?suppliementDtos.size():10;
            loadTable();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }
    public void loadTable(){

        try {
            suppliementDtos=supplimentModel.getAllSuppliement();
            inventoryDtos=inventoryModel.getAllInventory();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tableVBox.getChildren().clear();
        for (int i = start; i < end; i++) {
            fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/Suppliment/SupplimentRow.fxml"));

            try {
                HBox hBox=(HBox) fxmlLoader.load();
                supplimentRowController=fxmlLoader.getController();
                supplimentRowController.setDataRow(suppliementDtos.get(i),inventoryDtos.get(i));
                supplimentRowController.setParentComponent(tableVBox,mainPaneSuppliments,checkoutBtn);
                tableVBox.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

        }

    }
}
