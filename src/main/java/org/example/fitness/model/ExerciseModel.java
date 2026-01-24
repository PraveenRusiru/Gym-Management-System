package org.example.fitness.model;

import org.example.fitness.utill.Crudutill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseModel {
    public ArrayList<String> getExercises(String muscle) throws SQLException {
        ResultSet rst = Crudutill.execute("select Exercise.description from Exercise where target_muscle=?",muscle);

        ArrayList<String> exercises = new ArrayList<>();
        while (rst.next()) {
            exercises.add(rst.getString(1));
        }
        return exercises;
    }
    public ArrayList<String> getMuscles() throws SQLException {
        ResultSet rst = Crudutill.execute("select distinct target_muscle from Exercise");

        ArrayList<String> muscles = new ArrayList<>();
        while (rst.next()) {
            muscles.add(rst.getString(1));
        }
        return muscles;
    }
}
