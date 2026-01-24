package org.example.fitness.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.fitness.dto.ItemDto;
import org.example.fitness.model.InventoryModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CartRowController implements Initializable {

ItemDto itemDto;
    @FXML
    private HBox hBoxContainer;

    @FXML
    private ImageView deleteBtn;

    @FXML
    private ImageView detailUpdateBtn;

    @FXML
    private JFXTextField finalPriceTxt;

    @FXML
    private Label priceLbl;


    @FXML
    private ComboBox<Integer> qtyComboBox;

    @FXML
    private Label supIdLbl;

    @FXML
    private Label titleLbl;

    @FXML
    private Label wholeSaleLbl;

    private Pane mainPaneOrderDetils;
    private VBox tableVBox;

    @FXML
    void qtyOnAction(ActionEvent event) {
        finalPriceTxt.setText(String.valueOf(qtyComboBox.getValue()*Double.parseDouble(priceLbl.getText())));
    }
    @FXML
    void deleteAction(MouseEvent event) {
            SupplimentController.itemDtos.removeIf(itemDto -> itemDto.getSupplimentId().equals(supIdLbl.getText()));
            tableVBox.getChildren().remove(hBoxContainer);
    }

    @FXML
    void detailUpdateAction(MouseEvent event) {

    }

    public void setDataRow(ItemDto itemDto) {
        this.itemDto = itemDto;
        supIdLbl.setText(itemDto.getSupplimentId());
        titleLbl.setText(itemDto.getSupplimentName());
        priceLbl.setText(Double.toString(itemDto.getPrice()));
        finalPriceTxt.setText(Double.toString(itemDto.getPrice()));

        int qty=0;
        try {
            qty=new InventoryModel().howManyQtyRemained(itemDto.getSupplimentId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        ObservableList<Integer> qtyList= FXCollections.observableArrayList();
        for (int i = 0; i < qty; i++) {
            qtyList.add(i+1);
        }
        qtyComboBox.setItems(qtyList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setParentComponent(VBox tableVBox, Pane mainPaneOrderDetils) {
        this.mainPaneOrderDetils = mainPaneOrderDetils;
        this.tableVBox = tableVBox;
    }

//    double subtotal=0;
//    double finalPriceTotal=0;
//
//    public String calculateTotal(){
//         subtotal=Double.parseDouble(String.valueOf(qtyComboBox.getValue()))*Double.parseDouble(priceLbl.getText());
//         finalPriceTotal=
//        return String.valueOf(subtotal);
//    }
//    public String calculateDiscount(){
//        return subtotal-;
//    }
}
