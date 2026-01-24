package org.example.fitness.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.example.fitness.dto.ClientContactDto;
import org.example.fitness.dto.ClientDto;
import org.example.fitness.model.ClientContactModel;
import org.example.fitness.model.ClientModel;
import org.example.fitness.utill.ChangeValidUtill;
import org.example.fitness.utill.ValidUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddNewClientsController implements Initializable {

    private ClientDto clientDto;
    private ClientContactDto contactDto;
    private ClientModel clientModel;
    private ClientContactModel contactModel;
    static String clientId="";
    static String goal="";
    CommonMethod commonMethod = new CommonMethod();
    public AddNewClientsController() {
        clientModel = new ClientModel();
        contactModel = new ClientContactModel();
    }
                @FXML
                private Pane numberPane;

                @FXML
                private JFXTextField numberTxt;

                @FXML
                private ComboBox<String> comboBox;

                ObservableList<String> genderList= FXCollections.observableArrayList("Male","Female");

                @FXML
                private ComboBox<String> comboBoxGoal;

                ObservableList<String> goalList= FXCollections.observableArrayList("Muscle gain","Fat loss","Strength Training","Endurance Training");

                @FXML
                private Pane mailPane;

                @FXML
                private Pane fatPerPane;

                @FXML
                private JFXTextField fatPerTxt;

                @FXML
                private JFXTextField mailTxt;

                @FXML
                private JFXTextField firstNameTxt;

                @FXML
                private Label clientLbl;

                @FXML
                private JFXButton continueBtn;

                @FXML
                private JFXButton newClientBtn;

                @FXML
                private Pane lastNamePane;

                @FXML
                private JFXTextField lastNameTxt;

                @FXML
                private Pane nicPane;

                @FXML
                private Pane agePane;

                @FXML
                private JFXTextField ageTxt;

                @FXML
                private JFXTextField nicTxt;

                @FXML
                private Pane firstNamePane;

                @FXML
                private Pane applicationPane;

                @FXML
                private Pane heightPane;

                @FXML
                private JFXTextField heightTxt;

                @FXML
                private Pane weightPane;

                @FXML
                private JFXTextField weightTxt;

                @FXML
                private Label clientIdLbl;

                @FXML
                private JFXButton backClientBtn;

                @FXML
                private JFXButton updateBtn;

                @FXML
                private Pane clientDataPane;


                @FXML
                private Label dateLbl;

                @FXML
                void navigateToShowClient(ActionEvent event) throws SQLException, IOException {
                    ClientRowController.isFromClientRowController=false;
                   clientDto=new ClientDto(clientIdLbl.getText(),firstNameTxt.getText()+" "+lastNameTxt.getText(),Integer.parseInt(ageTxt.getText()),comboBox.getValue(),comboBoxGoal.getValue(),Integer.parseInt(heightTxt.getText()),Integer.parseInt(weightTxt.getText()),Double.parseDouble(fatPerTxt.getText()),Date.valueOf(dateLbl.getText()));
                   contactDto=new ClientContactDto(nicTxt.getText(),numberTxt.getText(),mailTxt.getText(),clientIdLbl.getText());
                    if(clientModel.updateClient(clientIdLbl.getText(),clientDto) && contactModel.updateClientContact(contactDto,clientIdLbl.getText())){
                    commonMethod.loadFrame(clientDataPane,"/view/Clients/Clients.fxml");
                    }
                }
                @FXML
                void navigateToClientList(ActionEvent event) throws IOException {
                    commonMethod.loadFrame(clientDataPane,"/view/Clients/Clients.fxml");
                }

                @FXML
                void navigateToServiceClient(ActionEvent event) {
                   boolean isValidFirstName= ValidUtil.isValidName(firstNameTxt.getText()) ;
                    boolean isValidLastName= ValidUtil.isValidName(lastNameTxt.getText());
                    boolean isValidPhone=ValidUtil.isValidPhone(numberTxt.getText());
                    boolean isValidEmail= ValidUtil.isValidEmail(mailTxt.getText());
                    boolean isValidAge=ValidUtil.isAgeValid(ageTxt.getText());
                    boolean isValidHeight=ValidUtil.isHeightValid(heightTxt.getText());
                    boolean isValidWeight=ValidUtil.isWeightValid(weightTxt.getText());
                    boolean isValidFatPercentage=ValidUtil.isFatPercentageValid(fatPerTxt.getText());

                    boolean isValidateNic=false;
                    System.out.println("fat per : "+isValidFatPercentage);
                    if(Integer.parseInt(ageTxt.getText())>=16){
                        if(ValidUtil.isNicValid(nicTxt.getText())){

                            isValidateNic=true;
                        }
                    }else{
                        isValidateNic=true;
                    }
//                    try {
                       // clientDto=new ClientDto(firstNameTxt.getText(),lastNameTxt.getText(),mailTxt.getText(),numberTxt.getText(),Integer.parseInt(ageTxt.getText()),comboBox.getValue(),Integer.parseInt(heightTxt.getText()),Integer.parseInt(weightTxt.getText()),comboBoxGoal.getValue(),nicTxt.getText(),Double.parseDouble(fatPerTxt.getText()));
//                           applicationPane.getChildren().clear();
//                           applicationPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Clients/ServiceClient.fxml")));
//                       }catch (IOException e){
//                           e.printStackTrace();
//                           System.out.println(e.getMessage());
//                       }

                    if(isValidFatPercentage && isValidFirstName && isValidLastName && isValidPhone && isValidAge && isValidateNic && isValidHeight && isValidWeight && isValidEmail) {
                        clientDto=new ClientDto(clientIdLbl.getText(),firstNameTxt.getText()+" "+lastNameTxt.getText(),Integer.parseInt(ageTxt.getText()),comboBox.getValue(),comboBoxGoal.getValue(),Integer.parseInt(heightTxt.getText()),Integer.parseInt(weightTxt.getText()),Double.parseDouble(fatPerTxt.getText()), Date.valueOf(LocalDate.now()));
                        clientModel.setClientDto(clientDto);
                        contactDto=new ClientContactDto(nicTxt.getText(),numberTxt.getText(),mailTxt.getText(),clientIdLbl.getText());
                        contactModel.setClientContactDto(contactDto);
                        //  String rst=clientModel.saveClientDetails(clientDto);
                       try {
                           applicationPane.getChildren().clear();
                           applicationPane.getChildren().add(FXMLLoader.load(getClass().getResource("/view/Clients/ServiceClient.fxml")));
                       }catch (IOException e){
                           e.printStackTrace();
                           System.out.println(e.getMessage());
                       }
                   }else{
                           new Alert(Alert.AlertType.ERROR,"Fail to update customer...!").show();

                   }

                }

    @FXML
    void comboBoxSelected(ActionEvent event) {
        System.out.println(comboBox.getValue());
    }
    @FXML
    void comboBoxGoalSelected(ActionEvent event) {
        goal=comboBoxGoal.getValue();
        System.out.println(comboBoxGoal.getValue());
    }
    @FXML
    void validationFirstName(KeyEvent event) {
        ChangeValidUtill.changeColours(firstNamePane, firstNameTxt,ValidUtil.isValidName(firstNameTxt.getText()));
    }
    @FXML
    void validateLastName(KeyEvent event) {
        ChangeValidUtill.changeColours(lastNamePane, lastNameTxt, ValidUtil.isValidName(lastNameTxt.getText()));
    }
    @FXML
    void validateMail(KeyEvent event) {
    ChangeValidUtill.changeColours(mailPane, mailTxt,ValidUtil.isValidEmail(mailTxt.getText()));
    }
    @FXML
    void validateNic(KeyEvent event) {
    ChangeValidUtill.changeColours(nicPane,nicTxt,ValidUtil.isNicValid(nicTxt.getText()));
    }
    @FXML
    void validateNumber(KeyEvent event) {
    ChangeValidUtill.changeColours(numberPane,numberTxt,ValidUtil.isValidPhone(numberTxt.getText()));
    }
    @FXML
    void validateAge(KeyEvent event) {
    ChangeValidUtill.changeColours(agePane,ageTxt,ValidUtil.isAgeValid(ageTxt.getText()));
    }
    @FXML
    void validateHeight(KeyEvent event) {
    ChangeValidUtill.changeColours(heightPane,heightTxt,ValidUtil.isHeightValid(heightTxt.getText()));
    }
    @FXML
    void validateWeight(KeyEvent event) {
     ChangeValidUtill.changeColours(weightPane,weightTxt,ValidUtil.isWeightValid(weightTxt.getText()));
    }
    @FXML
    void validateFatPer(KeyEvent event) {
     ChangeValidUtill.changeColours(fatPerPane,fatPerTxt,ValidUtil.isFatPercentageValid(fatPerTxt.getText()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       if(ClientRowController.isFromClientRowController){
           dateLbl.setVisible(true);
           setValueForTxt();
           updateBtn.setVisible(true);
           continueBtn.setVisible(false);

       }else{
           dateLbl.setVisible(false);
           updateBtn.setVisible(false);
           continueBtn.setVisible(true);

           try {
               clientIdLbl.setText(ClientModel.getNextClientId());
               clientId=clientIdLbl.getText();
           } catch (SQLException e) {
               e.printStackTrace();
               System.out.println(e.getMessage());
           }
       }
        comboBox.setItems(genderList);
        comboBoxGoal.setItems(goalList);
    }
    public void setValueForTxt(){

            dateLbl.setText(String.valueOf(clientModel.getClientDto().getJoinedDate()));

        clientIdLbl.setText(clientModel.getClientDto().getClientId());
        String name=clientModel.getClientDto().getClientName();
        if(name.contains(" ")){
            String[] namePart=clientModel.getClientDto().getClientName().split(" ");
            String firstName=namePart[0];
            String lastName=namePart[1];
            firstNameTxt.setText(firstName);
            lastNameTxt.setText(lastName);
        }else{
            firstNameTxt.setText(name);
        }
        ageTxt.setText(String.valueOf(clientModel.getClientDto().getAge()));
        nicTxt.setText(contactModel.getClientContactDto().getNic());
        numberTxt.setText(contactModel.getClientContactDto().getPhone());
        mailTxt.setText(contactModel.getClientContactDto().getEmail());
        comboBox.setValue(clientModel.getClientDto().getGender());
        heightTxt.setText(String.valueOf(clientModel.getClientDto().getHeight()));
        weightTxt.setText(String.valueOf(clientModel.getClientDto().getWeight()));
        fatPerTxt.setText(String.valueOf(clientModel.getClientDto().getFatPer()));
        comboBoxGoal.setValue(clientModel.getClientDto().getGoal());
    }
}
