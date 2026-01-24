package org.example.fitness.model;

import org.example.fitness.dto.NutrationDataDto;
import org.example.fitness.utill.Crudutill;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NutritionProgrammeModel {
    static NutrationDataDto nutrationDataDto;
    public void setNutrationDataDto(NutrationDataDto nutrationDataDto) {
        NutritionProgrammeModel.nutrationDataDto = nutrationDataDto;
    }
    public NutrationDataDto getNutrationDataDto() {
        return nutrationDataDto;
    }
    public static String getNextNutrationProgrammeId() throws SQLException {
        ResultSet rst = Crudutill.execute("select id from Nutrition_Programme order by id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(2); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("NP%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "NP001"; // Return the default customer ID if no data is found
    }
    public boolean isNutrationProgrammeSaved() throws SQLException {
        return Crudutill.execute("insert into Nutrition_Programme values (?,?,?,?,?,?,?)",
                nutrationDataDto.getId(),
                SheduleModel.sheduleDto.getSheduleId(),
                nutrationDataDto.getMaintainance_calories(),
                nutrationDataDto.getCalories(),
                nutrationDataDto.getProtein(),
                nutrationDataDto.getCarbs(),
                nutrationDataDto.getFat()
                );
    }
}
