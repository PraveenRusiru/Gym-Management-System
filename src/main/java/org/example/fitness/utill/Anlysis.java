package org.example.fitness.utill;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.example.fitness.model.ClientModel;
import org.example.fitness.model.FirstAnalysis;
import org.example.fitness.model.MembershipModel;
import org.example.fitness.model.SheduleModel;

import java.awt.*;

public class Anlysis {

    private FirstAnalysis Model;
    public Anlysis(ClientModel clientModel) {
        Model = clientModel;
    }
    public Anlysis(MembershipModel membershipModel){
        Model = membershipModel;
    }
    public Anlysis(SheduleModel sheduleModel){
        Model = sheduleModel;
    }

    public void analyizeMethod(Label countLbl, Label percentageLbl, ImageView progress,ImageView down,ImageView equal){
        int thisMonthCount=Model.getCount();
        int lastMonthCount=Model.getThisMonthCount();
        int clientCount=Model.getLastMonthCount();

        double percentage=0;
        if(lastMonthCount!=0){
            percentage=(double)(thisMonthCount-lastMonthCount)/lastMonthCount*100;
        }else{
            percentage=(double)(thisMonthCount-lastMonthCount)/thisMonthCount*100;
        }
        countLbl.setText(String.valueOf(clientCount));
        System.out.println();
        if(percentage>0){
            percentageLbl.setText("+ "+String.valueOf(percentage)+" %");
            percentageLbl.setStyle("-fx-text-fill: #90d78d;");
            progress.setVisible(true);
            down.setVisible(false);
            equal.setVisible(false);
        } else if (percentage==0) {
            percentageLbl.setText(" "+String.valueOf(percentage)+" %");
            percentageLbl.setStyle("-fx-text-fill: #8a888a;");
            progress.setVisible(false);
            down.setVisible(false);
            equal.setVisible(true);
        }else{
            percentageLbl.setText("- "+String.valueOf(percentage)+" %");
            percentageLbl.setStyle("-fx-text-fill: #fb6868;");
            progress.setVisible(false);
            down.setVisible(true);
            equal.setVisible(false);
        }
    }



}
