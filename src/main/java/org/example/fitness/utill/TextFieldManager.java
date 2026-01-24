package org.example.fitness.utill;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.example.fitness.model.ClientContactModel;
import org.example.fitness.model.ClientModel;

import java.sql.SQLException;

public class TextFieldManager {
    public void setupFieldforJtoTTxt(JFXTextField currentField, TextField nextField) {
        currentField.setOnKeyPressed(event -> handleEnterKeyForJumptoAnotherTxt(event, currentField, nextField));
    }
    public void setupFieldforJtoJTxt(JFXTextField currentField, JFXTextField nextField) {
        currentField.setOnKeyPressed(event -> handleEnterKeyForJumptoAnotherTxt(event, currentField, nextField));
    }
    public void setLblAndTxt(TextField currentField,Label label) {
        currentField.setOnKeyPressed(event -> {handleEnterKey(event,currentField,label);});
    }
    public void setLblTxtAndBox(TextField currentField, Label label, ComboBox<String> comboBox) {
        currentField.setOnKeyPressed(event -> {handleEnterKeyForCombo(event,currentField,label,comboBox);});
    }

    private void handleEnterKeyForCombo(KeyEvent event, TextField currentField, Label label, ComboBox<String> comboBox) {
       String clientId="";
       String goal="";
        if (event.getCode() == KeyCode.ENTER) {
            // Set the entered text as the value in the text field
            String enteredText = currentField.getText();
            currentField.setText(enteredText);
            try {
                clientId=new ClientContactModel().getClientId(enteredText);
                label.setText(clientId);
                goal=new ClientModel().getClientGoal(clientId);
                comboBox.setValue(goal);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            System.out.println("Entered text: " + enteredText);
        }
    }
    //public void setupField

    public void jumpToButtonOnEnter(JFXTextField currentField, JFXButton button) {
        currentField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                button.requestFocus(); // Moves the focus to the button
                button.fire(); // Optionally "click" the button
            }
        });
    }

    private void handleEnterKeyForJumptoAnotherTxt(KeyEvent event, TextField currentField, TextField nextField) {
        if (event.getCode() == KeyCode.ENTER) {
            String value = currentField.getText();
//processValue(value); // Process the value (e.g., save to database)
            if (nextField != null) {
                nextField.requestFocus(); // Move cursor to the next TextField
            }
        }
    }

  //  private void processValue(String value) {
    //}
    private void handleEnterKey(KeyEvent event, TextField currentField, Label lbl) {
        if (event.getCode() == KeyCode.ENTER) {
            // Set the entered text as the value in the text field
            String enteredText = currentField.getText();
            currentField.setText(enteredText);
            try {
                lbl.setText(new ClientContactModel().getClientId(enteredText));
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            System.out.println("Entered text: " + enteredText);
        }
    }
}
