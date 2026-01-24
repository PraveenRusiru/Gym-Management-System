package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import org.example.fitness.dto.InventoryDto;
import org.example.fitness.dto.ItemDto;
import org.example.fitness.dto.SuppliementDto;
import org.example.fitness.model.InventoryModel;
import org.example.fitness.model.SupplimentModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SupplimentRowController  implements Initializable {


    SupplimentModel supplimentModel=new SupplimentModel();
    InventoryModel inventoryModel= new InventoryModel();
    SuppliementDto suppliementDto;

    static boolean isFromSupplimentRowController=false;
    InventoryDto inventoryDto;
    CommonMethod commonMethod=new CommonMethod();
    @FXML
    private Label dateLbl;

    @FXML
    private ImageView addtoCartBtn;

    @FXML
    private ImageView deleteBtn;

    @FXML
    private Label descriptionLbl;


    @FXML
    private HBox hBoxContainer;

    @FXML
    private ImageView detailUpdateBtn;

    @FXML
    private Label inventoryIdLbl;

    @FXML
    private Label priceLbl;

    @FXML
    private Label qtyLbl;

    @FXML
    private Label supIdLbl;

    @FXML
    private Label supTitleLbl;

    @FXML
    private Label supplierLbl;

    @FXML
    private Label typeLbl;

    @FXML
    private Rectangle addToCartRectangle;

    @FXML
    private Label wholeSaleLbl;

    private VBox tableVBox;

    private Pane mainPane;

    private JFXButton checkoutBtn;

    @FXML
    void deleteAction(MouseEvent event) throws SQLException {
        //System.out.println(inventoryIdLbl.getText()+"pancho");
        if(supplimentModel.deleteSuppliment(supIdLbl.getText())  && tableVBox!=null){
            tableVBox.getChildren().remove(hBoxContainer);
        }
    }

    @FXML
    void addtoCartOnAction(MouseEvent event) {
        ItemDto itemDto=new ItemDto(supIdLbl.getText(),supTitleLbl.getText(),Double.parseDouble(priceLbl.getText()));
        SupplimentController.itemDtos.add(itemDto);
        addToCartRectangle.setVisible(false);
        addtoCartBtn.setVisible(false);
        checkoutBtn.setVisible(true);
    }

    @FXML
    void detailUpdateAction(MouseEvent event) {
        isFromSupplimentRowController=true;

        suppliementDto=new SuppliementDto(supIdLbl.getText(),supTitleLbl.getText(),descriptionLbl.getText(),Double.parseDouble(priceLbl.getText()),typeLbl.getText());
        supplimentModel.setSuppliementDto(suppliementDto);

        inventoryDto=new InventoryDto(inventoryIdLbl.getText(),supIdLbl.getText(),Integer.parseInt(qtyLbl.getText()), Date.valueOf(dateLbl.getText()),supplierLbl.getText(),Double.parseDouble(wholeSaleLbl.getText()));
        inventoryModel.setInventoryDto(inventoryDto);

        try {
            commonMethod.loadFrame(mainPane,"/view/Suppliment/AddNewSuppliment.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }
    public void setDataRow(SuppliementDto suppliementDto, InventoryDto inventoryDto) {
        supIdLbl.setText(suppliementDto.getSuppliementId());
        supTitleLbl.setText(suppliementDto.getTitle());
        typeLbl.setText(suppliementDto.getCategory());
        qtyLbl.setText(String.valueOf(inventoryDto.getStockQty()));
        priceLbl.setText(String.valueOf(suppliementDto.getPrice()));
        dateLbl.setText(String.valueOf(inventoryDto.getPurchaseDate()));
        inventoryIdLbl.setText(String.valueOf(inventoryDto.getInventoryId()));
        supplierLbl.setText(inventoryDto.getSupplierName());

    }
    public void setParentComponent(VBox tableVBox, Pane mainPane, JFXButton checkoutBtn){
            this.mainPane=mainPane;
            this.tableVBox=tableVBox;
            this.checkoutBtn=checkoutBtn;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wholeSaleLbl.setVisible(false);
        descriptionLbl.setVisible(false);
    }
}
