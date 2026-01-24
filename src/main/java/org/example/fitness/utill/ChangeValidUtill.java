package org.example.fitness.utill;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.layout.Pane;

import javax.swing.*;

public class ChangeValidUtill {
    public static void changeColours(Pane pane, JFXTextField txt,boolean isValid) {
        pane.setStyle(pane.getStyle()+";-fx-border-color: #c9c9c9;");
        txt.setStyle(txt.getStyle()+";-jfx-focus-color: #c9c9c9;");
        if(!isValid){
            pane.setStyle(pane.getStyle()+";-fx-border-color: red;");
            txt.setStyle(txt.getStyle()+";-jfx-focus-color: red;");
        }
    }

}
