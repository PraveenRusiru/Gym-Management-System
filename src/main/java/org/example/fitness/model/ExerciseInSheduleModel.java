package org.example.fitness.model;

import org.example.fitness.dto.SheduleDataDto;
import org.example.fitness.utill.Crudutill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseInSheduleModel {
    static ArrayList<SheduleDataDto> sheduleDataDtos = new ArrayList<>();

    public void setSheduleDataDtos(ArrayList<SheduleDataDto> sheduleDataDtos) {
        ExerciseInSheduleModel.sheduleDataDtos = sheduleDataDtos;
    }
    public ArrayList<SheduleDataDto> getSheduleDataDtos() {
        return sheduleDataDtos;
    }
    public boolean isSavedExerciseInShedule() throws SQLException {
        boolean isEveryRowSaved = true;
        for (SheduleDataDto sheduleDataDto : sheduleDataDtos) {
                if(!isSavedExerciseRowInShedule(sheduleDataDto)) {
                    isEveryRowSaved = false;
                }
        }
        return isEveryRowSaved;
    }
    public boolean isSavedExerciseRowInShedule(SheduleDataDto sheduleDataDto) throws SQLException {
        String exerciseId="";
        ResultSet rst=Crudutill.execute("select Exercise.exercise_id  from Exercise where description=?",sheduleDataDto.getWorkout());
        if(rst.next()){
          return  Crudutill.execute("insert into Exercises_in_Schedule values (?,?,?,?,?,?,?,?,?)",
                    SheduleModel.sheduleDto.getSheduleId(),
                    rst.getString(1),
                    sheduleDataDto.getDay(),
                    sheduleDataDto.getPriorityNum(),
                    sheduleDataDto.getWeight(),
                    sheduleDataDto.getSet(),
                    sheduleDataDto.getRep(),
                    sheduleDataDto.getTempo(),
                    sheduleDataDto.getRest()
                    );
        }else{
            return false;
        }

    }
    public int getDayCount(String sheduleId) throws SQLException {
        ResultSet resultSet=Crudutill.execute("select Exercises_in_Schedule.day from Exercises_in_Schedule where schedule_id=? order by day desc limit 1",sheduleId);
        int dayCount=0;
        if(resultSet.next()){
            dayCount=(int) resultSet.getInt(1);
        }
        return dayCount;

    }
    public boolean deleteExerciseInShedule(String scheduleId) throws SQLException {
        return Crudutill.execute("delete from Exercises_in_Schedule where schedule_id=?",scheduleId);
    }
}
