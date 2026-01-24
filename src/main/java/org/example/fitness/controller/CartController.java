package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.fitness.dto.ItemDto;
import org.example.fitness.dto.OrderDetailDto;
import org.example.fitness.dto.OrderDto;
import org.example.fitness.model.ClientContactModel;
import org.example.fitness.model.OrderDetailModel;
import org.example.fitness.model.OrdersModel;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    private ArrayList<ItemDto> itemDtos=SupplimentController.itemDtos;
    private FXMLLoader fxmlLoader;
    private CartRowController cartRowController;
    private ArrayList<OrderDetailDto> orderDetailDtos=new ArrayList<>();
    private OrdersModel ordersModel=new OrdersModel();
    private OrderDetailModel orderDetailModel=new OrderDetailModel();
    private CommonMethod commonMethod=new CommonMethod();
    static String cliId="";
    static boolean isFromCartController=false;
    @FXML
    private Label clientIdLbl;

    @FXML
    private Label dateLbl;

    @FXML
    private Pane mainPaneOrderDetils;

    @FXML
    private JFXButton newSupplimentBtn;

    @FXML
    private ImageView nextBtn;

    @FXML
    private JFXTextField nicTxt;

    @FXML
    private Label orderIdLbl;

    @FXML
    private ImageView previousBtn;

    @FXML
    private VBox tableVBox;

    @FXML
    private JFXButton backBtn;

    @FXML
    private Label discountBoldLbl;

    @FXML
    private JFXButton placeOrderBtn;

    @FXML
    private Label totalBoldLbl;

    @FXML
    private Label discountLbl;

    @FXML
    private HBox totalHbox;

    @FXML
    private Label totalLbl;

    @FXML
    private JFXButton doneBtn;

    @FXML
    void doneOnAction(ActionEvent event) {
        double totalFinalPrice=0;
        double discount=0;
        System.out.println(clientIdLbl.getText());
        if(!clientIdLbl.getText().equals(" ") ){
            placeOrderBtn.setVisible(true);
            doneBtn.setVisible(false);
            for(var node:tableVBox.getChildren()){
                if(node instanceof HBox){

                    HBox hbox = (HBox) node;
                    Label supIdLbl = (Label)hbox.lookup("#supIdLbl");
                    Label titleLbl = (Label)hbox.lookup("#titleLbl");
                    Label priceLbl = (Label)hbox.lookup("#priceLbl");
                    ComboBox qtyComboBox = (ComboBox) hbox.lookup("#qtyComboBox");
                    JFXTextField finalPriceTxt=(JFXTextField)hbox.lookup("#finalPriceTxt");

                    totalFinalPrice+=Double.parseDouble(finalPriceTxt.getText());
                        discount+=(Double.parseDouble(priceLbl.getText())*Integer.parseInt(qtyComboBox.getValue().toString()))-Double.parseDouble(finalPriceTxt.getText());

                        OrderDetailDto orderDetailDto=new OrderDetailDto(supIdLbl.getText(),Integer.parseInt(qtyComboBox.getValue().toString()),Double.parseDouble(finalPriceTxt.getText()));
                    orderDetailDtos.add(orderDetailDto);
                }
            }
            totalLbl.setText(String.valueOf(totalFinalPrice));
            discountLbl.setText(String.valueOf(discount));



        }else{
            new Alert(Alert.AlertType.ERROR, "Please enter a valid Client ID").show();
        }

        //totalBoldLbl.setText();
    }

    @FXML
    void loadNextRow(MouseEvent event) {

    }

    @FXML
    void loadPreviousRows(MouseEvent event) {

    }
    @FXML
    void BackToSupplimentList(ActionEvent event) {
        try {
            commonMethod.loadFrame(mainPaneOrderDetils,"/view/Suppliment/Suppliment.fxml");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void navigateToSupplimentList(ActionEvent event) {
            if(orderDetailDtos.size()>0){
                cliId=clientIdLbl.getText();
                System.out.println("cliId "+cliId);
                OrderDto orderDto=new OrderDto(orderIdLbl.getText(),clientIdLbl.getText(), Date.valueOf(dateLbl.getText()),Double.parseDouble(totalLbl.getText()),Double.parseDouble(discountLbl.getText()),orderDetailDtos);
                try {
                    boolean isOrderSaved=ordersModel.isOrderSaved(orderDto);
                    if(isOrderSaved){
                        isFromCartController=true;
                        try {
                            commonMethod.loadFrame(mainPaneOrderDetils,"/view/Suppliment/Suppliment.fxml");
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }else{
                new Alert(Alert.AlertType.ERROR, "Add items before placing an order !").show();
            }
    }
//    public void setData(ArrayList<ItemDto> itemDtos){
//    this.itemDtos = itemDtos;
//    }
    private void handleEnterKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            // Set the entered text as the value in the text field
            String enteredText = nicTxt.getText();
            nicTxt.setText(enteredText);
            try {
                clientIdLbl.setText(new ClientContactModel().getClientId(enteredText));
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            System.out.println("Entered text: " + enteredText);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nicTxt.setOnKeyPressed(this::handleEnterKey);
        placeOrderBtn.setVisible(false);
        loadTable();
        try {
            orderIdLbl.setText(OrdersModel.getNextOrderId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        dateLbl.setText(LocalDate.now().toString());
    }

    public void loadTable(){
        tableVBox.getChildren().clear();
        for (int i = 0; i <itemDtos.size() ; i++) {
            fxmlLoader=new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/Suppliment/CartRow.fxml"));

            try {
                HBox hBox=(HBox) fxmlLoader.load();
                cartRowController=fxmlLoader.getController();
                cartRowController.setDataRow(itemDtos.get(i));
                cartRowController.setParentComponent(tableVBox,mainPaneOrderDetils);
                tableVBox.getChildren().add(hBox);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
}
