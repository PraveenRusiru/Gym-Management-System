package org.example.fitness.model;

import org.example.fitness.dto.ClientNoteDto;
import org.example.fitness.utill.Crudutill;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteForClientModel {
   static ClientNoteDto clientNoteDto;
    public static String getNextNoteId() throws SQLException {
        ResultSet rst = Crudutill.execute("select note_id from Note_for_Client order by note_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(2); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("NC%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "NC001"; // Return the default customer ID if no data is found
    }
    public void setClientNoteDto(ClientNoteDto clientNoteDto) {
        NoteForClientModel.clientNoteDto = clientNoteDto;
    }
    public ClientNoteDto getClientNoteDto() {
        return clientNoteDto;
    }
    public boolean isClientNoteSaved() throws SQLException {
        if(clientNoteDto.getDescription().isEmpty()){
            return true;
        }else{
            return Crudutill.execute("insert into Note_for_Client values (?,?,?)",
                    getClientNoteDto().getNoteId(),
                    getClientNoteDto().getClientId(),
                    getClientNoteDto().getDescription()
            );
        }


    }
}
