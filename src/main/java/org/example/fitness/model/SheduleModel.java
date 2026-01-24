package org.example.fitness.model;

import org.example.fitness.dto.SheduleDto;
import org.example.fitness.utill.Crudutill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SheduleModel implements FirstAnalysis{
    static SheduleDto sheduleDto;
    public String getNextSheduleId() throws SQLException {
        ResultSet rst = Crudutill.execute("select schedule_id from Schedule order by schedule_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(4); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("SHDL%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "SHDL001"; // Return the default customer ID if no data is found
    }
    public void setSheduleDto(SheduleDto sheduleDto) {
        SheduleModel.sheduleDto = sheduleDto;
    }
    public SheduleDto getSheduleDto() {
        return sheduleDto;
    }
    public boolean isSheduleDataSaved() throws SQLException {
        return Crudutill.execute("insert into Schedule values (?,?,?,?,?)",
                sheduleDto.getSheduleId(),
                sheduleDto.getClientId(),
                sheduleDto.getIssuedDate(),
                sheduleDto.getExpiredDate(),
                sheduleDto.getGoal()!=null?sheduleDto.getGoal():""
                );
    }

    @Override
    public int getThisMonthCount() {
        ResultSet rst = null;
        try {
            rst = Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Schedule WHERE issued_date BETWEEN CURDATE() - INTERVAL 30 DAY AND CURDATE()");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (rst.next()) {
                return Integer.parseInt(rst.getString(1));
            }else{
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getLastMonthCount() {
        ResultSet rst = null;
        try {
            rst = Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Schedule WHERE MONTH(issued_date) = MONTH(CURDATE() - INTERVAL 1 MONTH) AND YEAR(issued_date) = YEAR(CURDATE() - INTERVAL 1 MONTH)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (rst.next()) {
                return Integer.parseInt(rst.getString(1));
            }else{
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCount() {
        ResultSet rst= null;
        try {
            rst = Crudutill.execute("SELECT COUNT(*) AS new_client_count FROM Schedule");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (rst.next()) {
                return Integer.parseInt(rst.getString(1));
            }else{
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<SheduleDto> getAllShedules() throws SQLException{
        ArrayList<SheduleDto> sheduleDtos = new ArrayList<>();
        ResultSet rst= Crudutill.execute("select * from Schedule");
        while (rst.next()) {
            SheduleDto sheduleDto = new SheduleDto();
            sheduleDto.setSheduleId(rst.getString(1));
            sheduleDto.setClientId(rst.getString(2));
            sheduleDto.setIssuedDate(rst.getDate(3));
            sheduleDto.setExpiredDate(rst.getDate(4));
            sheduleDto.setGoal(rst.getString(5));
            sheduleDtos.add(sheduleDto);
        }
        return sheduleDtos;

    }
    public boolean deleteShedule(String sheduleId) throws SQLException{
       boolean isExerciseInShedule=new ExerciseInSheduleModel().deleteExerciseInShedule(sheduleId);
       if(isExerciseInShedule){
           return Crudutill.execute("delete from Schedule where schedule_id=?", sheduleId);
       }else{
           return false;
       }


    }
}
