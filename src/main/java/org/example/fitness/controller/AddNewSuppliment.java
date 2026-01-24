package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import org.example.fitness.dto.InventoryDto;
import org.example.fitness.dto.SuppliementDto;
import org.example.fitness.model.InventoryModel;
import org.example.fitness.model.SupplimentModel;
import org.example.fitness.utill.ChangeValidUtill;
import org.example.fitness.utill.ValidUtil;

import java.io.IOException;
import java.net.URL;
import javafx.util.StringConverter;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddNewSuppliment implements Initializable {
    private  InventoryDto inventoryDto;
    private  SuppliementDto suppliementDto;
    private SupplimentModel supplimentModel;
    private InventoryModel inventoryModel;
    private CommonMethod commonMethod;
    public AddNewSuppliment(){
        supplimentModel = new SupplimentModel();
        inventoryModel = new InventoryModel();
        commonMethod = new CommonMethod();
    }
    @FXML
    private JFXButton addBtn;

    @FXML
    private Pane applicationPane;

    @FXML
    private JFXButton backClientBtn;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private Pane categoryPane;

    @FXML
    private Pane clientDataPane;

    @FXML
    private Label clientLbl;


    @FXML
    private Pane datePane;


    @FXML
    private JFXTextArea descriptionArea;

    @FXML
    private JFXButton backSuplliemnttBtn;

    @FXML
    private Label dateLbl;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Pane inventoryIdPane;

    @FXML
    private JFXTextField inventoryIdTxt;

    @FXML
    private JFXTextField nameTxt;

    @FXML
    private Pane pricePane;

    @FXML
    private JFXTextField priceTxt;

    @FXML
    private JFXTextField qtyTxt;

    @FXML
    private Pane stockQtyPane;

    @FXML
    private Pane supplierPane;

    @FXML
    private Label suppIdLbl;

    @FXML
    private JFXTextField supplierTxt;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private Pane wholesalePricePane;

    @FXML
    private JFXTextField wholesalePriceTxt;

    @FXML
    void categorycomboBoxSelected(ActionEvent event) {

    }

    @FXML
    void backToSuppliemnt(ActionEvent event) throws SQLException {
    SupplimentRowController.isFromSupplimentRowController=false;
    suppliementDto=new SuppliementDto(suppIdLbl.getText(),nameTxt.getText(),descriptionArea.getText(),Double.parseDouble(priceTxt.getText()),categoryComboBox.getValue().toString());
    inventoryDto=new InventoryDto(inventoryIdTxt.getText(),suppIdLbl.getText(),Integer.parseInt(qtyTxt.getText()),Date.valueOf(datePicker.getValue()),supplierTxt.getText(),Double.parseDouble(wholesalePriceTxt.getText()));
        if(supplimentModel.isSupplimentUpdated(suppliementDto,suppIdLbl.getText())  &&  inventoryModel.updateInventory(inventoryDto,inventoryIdTxt.getText())){
            try {
                commonMethod.loadFrame(clientDataPane,"/view/Suppliment/Suppliment.fxml");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }


    @FXML
    void navigateToSuppList(ActionEvent event) {
        try {
            commonMethod.loadFrame(clientDataPane,"/view/Suppliment/Suppliment.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void navigateToAddNewSuppliment(ActionEvent event) {
        boolean isValidInventoryId= ValidUtil.isStringValid(inventoryIdTxt.getText());
        boolean isPriceValid=ValidUtil.isPriceValid(priceTxt.getText());
        boolean isQtyValid=ValidUtil.isIntegerValid(qtyTxt.getText());
        boolean isSupplierValid=ValidUtil.isStringValid(supplierTxt.getText());
        boolean isWholesalePriceValid=ValidUtil.isPriceValid(wholesalePriceTxt.getText());
        if(isValidInventoryId && isPriceValid && isQtyValid && isSupplierValid && isWholesalePriceValid){
         inventoryDto=new InventoryDto(inventoryIdTxt.getText(),suppIdLbl.getText(),Integer.parseInt(qtyTxt.getText()), Date.valueOf(datePicker.getValue()),supplierTxt.getText(),Double.parseDouble(wholesalePriceTxt.getText()));
        inventoryModel.setInventoryDto(inventoryDto);
        suppliementDto=new SuppliementDto(suppIdLbl.getText(),nameTxt.getText(),descriptionArea.getText(),Double.parseDouble(priceTxt.getText()),categoryComboBox.getValue().toString());
        supplimentModel.setSuppliementDto(suppliementDto);

            try {
                boolean isSaved=supplimentModel.saveSupplimentAndInventoyDetail();
                if(isSaved)clearTxt();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

        }
    }

    @FXML
    void pickdate(ActionEvent event) {
        System.out.println(datePicker.getValue().toString());
    }

    @FXML
    void validateInventoryId(KeyEvent event) {
        ChangeValidUtill.changeColours(inventoryIdPane,inventoryIdTxt, ValidUtil.isStringValid(inventoryIdTxt.getText()));
    }

    @FXML
    void validatePrice(KeyEvent event) {
        ChangeValidUtill.changeColours(pricePane,priceTxt, ValidUtil.isPriceValid(priceTxt.getText()));
    }

    @FXML
    void validateQty(KeyEvent event) {
        ChangeValidUtill.changeColours(stockQtyPane,qtyTxt,ValidUtil.isIntegerValid(qtyTxt.getText()));
    }

    @FXML
    void validateSupplier(KeyEvent event) {
        ChangeValidUtill.changeColours(supplierPane,supplierTxt, ValidUtil.isStringValid(supplierTxt.getText()));
    }

    @FXML
    void validateWholesalePrice(ActionEvent event) {
        ChangeValidUtill.changeColours(wholesalePricePane,wholesalePriceTxt, ValidUtil.isPriceValid(wholesalePriceTxt.getText()));
    }
    public void setDatePickerFormat(DatePicker datePicker, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        datePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return formatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, formatter);
                } else {
                    return null;
                }
            }
        });

        datePicker.setPromptText(format);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> categoryList= FXCollections.observableArrayList("Amino Acids","Creatine","Vitamins","Energy","Collagens","Fat Burners","Mass Gainers");
        if(SupplimentRowController.isFromSupplimentRowController){
            updateBtn.setVisible(true);
            addBtn.setVisible(false);
            setValueForText();
        }else{
            try {
                inventoryIdTxt.setText(InventoryModel.getNextInventoryId());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            try {
                suppIdLbl.setText(SupplimentModel.getNextSupplimentId());
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            updateBtn.setVisible(false);

        }

        dateLbl.setVisible(false);
        categoryComboBox.setItems(categoryList);
        setDatePickerFormat(datePicker,"dd/MM/yyyy");
    }

    public void clearTxt(){
        try {
            suppIdLbl.setText(SupplimentModel.getNextSupplimentId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        nameTxt.clear();
        descriptionArea.clear();
        priceTxt.clear();
        qtyTxt.clear();
        supplierTxt.clear();
        wholesalePriceTxt.clear();
        try {
            inventoryIdTxt.setText(InventoryModel.getNextInventoryId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        categoryComboBox.getSelectionModel().clearSelection();
        datePicker.getEditor().clear();
    }
    public void setValueForText(){
        SuppliementDto dto=supplimentModel.getSuppliementDto();
        InventoryDto dtoInventory=inventoryModel.getInventoryDto();
            suppIdLbl.setText(dto.getSuppliementId());
            nameTxt.setText(dto.getTitle());
            categoryComboBox.setValue(dto.getCategory());
            qtyTxt.setText(String.valueOf(dtoInventory.getStockQty()));
            inventoryIdTxt.setText(String.valueOf(dtoInventory.getInventoryId()));
            supplierTxt.setText(dtoInventory.getSupplierName());
            priceTxt.setText(String.valueOf(dto.getPrice()));
            wholesalePriceTxt.setText(String.valueOf(dtoInventory.getWholesalePrice()));
            datePicker.setValue(dtoInventory.getPurchaseDate().toLocalDate());
            descriptionArea.setText(dto.getDescription());

    }

}
