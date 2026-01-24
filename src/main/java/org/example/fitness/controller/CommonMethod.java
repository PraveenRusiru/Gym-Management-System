package org.example.fitness.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CommonMethod {
    public void loadFrame(Pane pane,String url) throws IOException {
       try {
           pane.getChildren().clear();
           pane.getChildren().add(FXMLLoader.load(getClass().getResource(url)));
       }catch (Exception e){
           e.printStackTrace();
           System.out.println(e.getMessage());
       }
    }
}
