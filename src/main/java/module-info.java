module org.example.fitness {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires java.naming;
    requires lombok;
    requires java.sql;
    requires java.desktop;
    requires mysql.connector.j;
    requires java.mail;
    requires net.sf.jasperreports.core;


    opens org.example.fitness to javafx.fxml;
    exports org.example.fitness;
    exports org.example.fitness.controller;
    opens org.example.fitness.controller to javafx.fxml;
}